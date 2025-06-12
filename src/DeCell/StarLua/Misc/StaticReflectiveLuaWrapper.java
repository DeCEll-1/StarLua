package DeCell.StarLua.Misc;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.util.*;

import org.luaj.vm2.*;
import org.luaj.vm2.lib.VarArgFunction;
import org.luaj.vm2.lib.jse.CoerceJavaToLua;

import static DeCell.StarLua.Misc.Reflections.*;

public class StaticReflectiveLuaWrapper extends LuaTable {
    public final Class<?> clazz;

    public StaticReflectiveLuaWrapper(Class<?> clazz) throws Throwable {
        this.clazz = clazz;

        // Group static methods by name for overload handling
        List<Object> methods = getStaticMethods(clazz);
        Map<String, List<Object>> methodsByName = new HashMap<>();
        for (Object method : methods) {
            String name = (String) getMethodNameHandle.invoke(method);
            if (getDeclaringClass.invoke(method) == Object.class) continue;
            methodsByName.computeIfAbsent(name, k -> new ArrayList<>()).add(method);
        }

        // Add each static method group as a Lua function
        for (Map.Entry<String, List<Object>> entry : methodsByName.entrySet()) {
            String name = entry.getKey();
            List<Object> overloads = entry.getValue();
            this.set(name, createLuaFunctionForStaticOverloads(overloads, clazz));
        }
    }

    private LuaFunction createLuaFunctionForStaticOverloads(List<Object> overloads, Class<?> clazz) {
        return new VarArgFunction() {
            @Override
            public Varargs invoke(Varargs args) {
                int luaArgCount = args.narg() - 1; // for ':' syntax
                for (Object method : overloads) {
                    try {
                        int paramCount = (int) getParameterCount.invoke(method);
                        if (paramCount != luaArgCount) continue;
                        Class<?>[] paramTypes = (Class<?>[]) getParameterTypesHandle.invoke(method);
                        Object[] javaArgs = new Object[paramCount];
                        boolean match = true;
                        for (int i = 1; i < paramCount + 1; i++) {
                            LuaValue arg = args.arg(i + 1);
                            Class<?> pt = paramTypes[i - 1];
                            try {
                                if (pt == String.class) {
                                    javaArgs[i - 1] = arg.checkjstring();
                                } else if (pt == int.class || pt == Integer.class) {
                                    javaArgs[i - 1] = arg.checkint();
                                } else if (pt == float.class || pt == Float.class) {
                                    javaArgs[i - 1] = (float) arg.checkdouble();
                                } else if (pt == double.class || pt == Double.class) {
                                    javaArgs[i - 1] = arg.checkdouble();
                                } else if (pt == boolean.class || pt == Boolean.class) {
                                    javaArgs[i - 1] = arg.checkboolean();
                                } else {
                                    javaArgs[i - 1] = arg.touserdata();
                                }
                            } catch (LuaError e) {
                                match = false;
                                break;
                            }
                        }
                        if (!match) continue;

                        // Make method accessible
                        setMethodAccessable.invoke(method, true);
                        MethodHandle mh = lookup.findStatic(clazz,
                                (String) getMethodNameHandle.invoke(method),
                                MethodType.methodType(
                                        (Class<?>) getReturnTypeHandle.invoke(method),
                                        (Class<?>[]) getParameterTypesHandle.invoke(method)
                                )
                        );

                        Object result = javaArgs.length == 0 ? mh.invoke() : mh.invokeWithArguments(javaArgs);

                        if (result == null) return LuaValue.NIL;
                        if (result instanceof String || result instanceof Number || result instanceof Boolean) {
                            return CoerceJavaToLua.coerce(result);
                        }
                        // If instance, wrap in ReflectiveLuaWrapper for Lua
                        return new ReflectiveLuaWrapper(result);

                    } catch (Throwable t) {
                        continue; // Try next overload
                    }
                }
                return LuaValue.error("No static overload matches provided arguments");
            }
        };
    }

    public static List<Object> getStaticMethods(Class<?> clazz) throws Throwable {
        List<Object> res = new ArrayList<>();
        for (Object method : clazz.getDeclaredMethods()) {
            int mods = (int) getModifiersHandle.invoke(method);
            if ((boolean) modifierIsPublic.invoke(mods) && (boolean) modifierIsStatic.invoke(mods)) {
                res.add(method);
            }
        }
        return res;
    }

    // Reuse your lookup (or import statically)
    private static final MethodHandles.Lookup lookup = MethodHandles.lookup();

}
