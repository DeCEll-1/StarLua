package DeCell.StarLua.Misc;

import org.luaj.vm2.*;
import org.luaj.vm2.lib.*;
import org.luaj.vm2.lib.jse.CoerceJavaToLua;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.util.*;

import static DeCell.StarLua.Misc.Reflections.*;


public class ReflectiveLuaWrapper extends LuaTable {
    public Object javaObject;
    public Class<?> javaObjectClass;

    public ReflectiveLuaWrapper(Object obj) throws Throwable {

        this.javaObject = obj;
        this.javaObjectClass = obj.getClass();


        // Group methods by name for overload handling
        List<Object> methods = getMethods(obj.getClass());
        Map<String, List<Object>> methodsByName = new HashMap<>();
        for (Object method : methods) {
            String name = (String) getMethodNameHandle.invoke(method);
            if (getDeclaringClass.invoke(method) == Object.class) continue;
            methodsByName.computeIfAbsent(name, k -> new ArrayList<>()).add(method);
        }

        for (Map.Entry<String, List<Object>> entry : methodsByName.entrySet()) {
            String name = entry.getKey();
            List<Object> overloads = entry.getValue();
            this.set(name, createLuaFunctionForOverloads(overloads, obj));
        }
    }

    private LuaFunction createLuaFunctionForOverloads(List<Object> overloads, Object obj) {
        return new VarArgFunction() {
            @Override
            public Varargs invoke(Varargs args) {
                // Lua ':' method call passes self as arg1, so real args start at 2
                int luaArgCount = args.narg() - 1;
                // try to find a method that matches
                for (Object method : overloads) {
                    try {
                        int paramCount = (int) getParameterCount.invoke(method);
                        if (paramCount != luaArgCount) continue;
                        Class<?>[] paramTypes = (Class<?>[]) getParameterTypesHandle.invoke(method);
                        Object[] javaArgs = new Object[paramCount + 1];
                        boolean match = true;
                        for (int i = 1; i < paramCount + 1; i++) {
                            LuaValue arg = args.arg(i + 1);
                            Class<?> pt = paramTypes[i - 1];
                            try {
                                if (pt == String.class) {
                                    javaArgs[i] = arg.checkjstring();
                                } else if (pt == int.class || pt == Integer.class) {
                                    javaArgs[i] = arg.checkint();
                                } else if (pt == float.class || pt == Float.class) {
                                    javaArgs[i] = (float) arg.checkdouble();
                                } else if (pt == double.class || pt == Double.class) {
                                    javaArgs[i] = arg.checkdouble();
                                } else if (pt == boolean.class || pt == Boolean.class) {
                                    javaArgs[i] = arg.checkboolean();
                                } else {
                                    javaArgs[i] = arg.touserdata();
                                }
                            } catch (LuaError e) {
                                match = false;
                                break;
                            }
                        }
                        if (!match) continue;

                        // got overload
                        // make method accessible
                        setMethodAccessable.invoke(method, true);
                        MethodHandle mh = getHandle(method);

                        Object result = null;
                        if (paramCount == 0) {
                            result = mh.invoke(obj);
                        } else {
                            javaArgs[0] = obj;
                            result = mh.invokeWithArguments(javaArgs);
                        }

                        if (result == null) return LuaValue.NIL;
                        if (result instanceof String || result instanceof Number || result instanceof Boolean) {
                            return CoerceJavaToLua.coerce(result);
                        }
                        return new ReflectiveLuaWrapper(result);

                    } catch (Throwable t) {
                        // Try next overload
                        continue;
                    }
                }
                // If we get here, no overload matched.
                return LuaValue.error("No overload matches provided arguments");
            }
        };
    }

    public static List<Object> getMethods(Class<?> clazz) throws Throwable {
        List<Object> res = new ArrayList<>();


        for (Object method : clazz.getDeclaredMethods())
            if ((boolean) modifierIsPublic.invoke((int) getModifiersHandle.invoke(method)))
                res.add(method);


        return res;
    }

    public static MethodHandle getHandle(Object method) throws Throwable {
        String methodName = (String) getMethodNameHandle.invoke(method);
        Class<?> returnType = (Class<?>) getReturnTypeHandle.invoke(method);
        Class<?>[] paramTypes = (Class<?>[]) getParameterTypesHandle.invoke(method);
        Class clazz = (Class) getDeclaringClass.invoke(method);
        MethodHandle methodHandle = null;
        try {
            methodHandle = lookup.findVirtual(clazz, methodName, MethodType.methodType(returnType, paramTypes));

        } catch (Exception ex) {
            MethodHandles.Lookup privateLookup = MethodHandles.privateLookupIn(clazz, MethodHandles.lookup());
            methodHandle = privateLookup.findVirtual(clazz, methodName, MethodType.methodType(returnType, paramTypes));
        }
        return methodHandle;
    }


    public static LuaFunction createConstructorWrapper(Class<?> clazz) {
        return new VarArgFunction() {
            @Override
            public Varargs invoke(Varargs args) {
                int luaArgCount = args.narg();

                for (Object constructor : clazz.getDeclaredConstructors()) {
                    try {

                        Class<?>[] paramTypes = (Class<?>[]) getConstructorParameterTypesHandle.invoke(constructor);
                        if (paramTypes.length != luaArgCount) continue;

                        Object[] javaArgs = new Object[paramTypes.length];
                        boolean match = true;
                        for (int i = 0; i < paramTypes.length; i++) {
                            LuaValue arg = args.arg(i + 1); // constructors doesnt have this so we dont skip the first paramater
                            Class<?> pt = paramTypes[i];
                            try {
                                if (pt == String.class) {
                                    javaArgs[i] = arg.checkjstring();
                                } else if (pt == int.class || pt == Integer.class) {
                                    javaArgs[i] = arg.checkint();
                                } else if (pt == float.class || pt == Float.class) {
                                    javaArgs[i] = (float) arg.checkdouble();
                                } else if (pt == double.class || pt == Double.class) {
                                    javaArgs[i] = arg.checkdouble();
                                } else if (pt == boolean.class || pt == Boolean.class) {
                                    javaArgs[i] = arg.checkboolean();
                                } else {
                                    javaArgs[i] = arg.touserdata();
                                }
                            } catch (LuaError e) {
                                match = false;
                                break;
                            }
                        }

                        if (!match) continue;


                        // Match found, try to invoke
                        setConstructorAccessibleHandle.invoke(constructor, true);
                        MethodHandle mh = getConstructorHandle(constructor);

                        Object obj = null;
                        if (paramTypes.length == 0) {
                            obj = mh.invoke();
                        } else {
                            obj = mh.invokeWithArguments(javaArgs);
                        }

                        return new ReflectiveLuaWrapper(obj);

                    } catch (Throwable t) {
                        // try next constructor
                        continue;
                    }
                }

                return LuaValue.error("No constructor matches provided arguments");
            }
        };
    }

    public static MethodHandle getConstructorHandle(Object method) throws Throwable {
        Class<?> declaringClass = (Class<?>) constructorGetDeclaringClass.invoke(method);
        Class<?>[] paramTypes = (Class<?>[]) getConstructorParameterTypesHandle.invoke(method);

        MethodHandles.Lookup lookup = MethodHandles.lookup();

        // Constructor returns void, so MethodType is void with paramTypes
        MethodType methodType = MethodType.methodType(void.class, paramTypes);

        try {
            // Use public lookup or private lookup if needed
            return lookup.findConstructor(declaringClass, methodType);
        } catch (IllegalAccessException e) {
            // For private constructors:
            MethodHandles.Lookup privateLookup = MethodHandles.privateLookupIn(declaringClass, lookup);
            return privateLookup.findConstructor(declaringClass, methodType);
        }
    }

    public static void createConstructor(Class<?> clazz, LuaValue table) {
        table.set("new", ReflectiveLuaWrapper.createConstructorWrapper(clazz));
    }
}
