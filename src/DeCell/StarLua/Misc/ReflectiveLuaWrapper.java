package DeCell.StarLua.Misc;

import com.fs.starfarer.api.combat.MutableStat;
import com.fs.starfarer.api.combat.ShipEngineControllerAPI;
import org.luaj.vm2.*;
import org.luaj.vm2.lib.*;
import org.luaj.vm2.lib.jse.CoerceJavaToLua;

import java.awt.*;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.util.*;
import java.util.List;

import static DeCell.StarLua.Misc.Reflections.*;


public class ReflectiveLuaWrapper extends LuaTable {
    public Object javaObject;
    public Class<?> javaObjectClass;

    protected Set<String> usedFunctions = new HashSet<>();
    private static final Map<Class<?>, Map<String, LuaFunction>> METHOD_WRAPPERS_CACHE = new HashMap<>();

    // Unique metatable to identify wrappers (set once)
    public static final LuaTable WRAPPER_METATABLE = new LuaTable();

    static {
        WRAPPER_METATABLE.set("__name", LuaValue.valueOf("ReflectiveLuaWrapper")); // Optional, for debugging
    }

    public ReflectiveLuaWrapper(Object obj, Class<?> clazz) throws Throwable {
        super();
        this.setmetatable(WRAPPER_METATABLE); // Mark as wrapper
        this.javaObject = obj;
        this.javaObjectClass = clazz;
        this.usedFunctions = null;
        this.init(obj, clazz);
    }

    public ReflectiveLuaWrapper(Object obj) throws Throwable {
        super();
        this.setmetatable(WRAPPER_METATABLE); // Mark as wrapper
        this.javaObject = obj;
        this.javaObjectClass = obj.getClass();
        this.usedFunctions = null;
        this.init(obj, this.javaObjectClass);
    }

    public ReflectiveLuaWrapper(Object obj, Set<String> usedFunctions) throws Throwable {
        super();
        this.setmetatable(WRAPPER_METATABLE); // Mark as wrapper
        this.javaObject = obj;
        this.javaObjectClass = obj.getClass();
        this.usedFunctions = usedFunctions;
        this.init(obj, this.javaObjectClass);
    }

    private void init(Object obj, Class<?> clazz) throws Throwable {
        // Get or compute the shared method wrappers for this class
        Map<String, LuaFunction> cachedWrappers = METHOD_WRAPPERS_CACHE.computeIfAbsent(clazz, c -> {
            try {
                // Group methods by name for overload handling (your existing logic)
                List<Object> methods = getMethods(c);
                Map<String, List<Object>> methodsByName = new HashMap<>();
                for (Object method : methods) {
                    String name = (String) getMethodNameHandle.invoke(method);
                    if (getDeclaringClass.invoke(method) == Object.class) continue;
                    methodsByName.computeIfAbsent(name, k -> new ArrayList<>()).add(method);
                }

                // Create the map of name -> LuaFunction
                Map<String, LuaFunction> wrappers = new HashMap<>();
                for (Map.Entry<String, List<Object>> entry : methodsByName.entrySet()) {
                    String name = entry.getKey();
                    List<Object> overloads = entry.getValue();
                    wrappers.put(name, createLuaFunctionForOverloads(overloads));
                }
                return wrappers;
            } catch (Throwable t) {
                throw new RuntimeException("Failed to cache wrappers for " + c.getName(), t);
            }
        });

        // Now populate this instance's table with the cached wrappers
        for (Map.Entry<String, LuaFunction> entry : cachedWrappers.entrySet()) {
            if (usedFunctions == null) {
                this.set(entry.getKey(), entry.getValue());
                continue;
            }
            if (usedFunctions.contains(entry.getKey()))
                this.set(entry.getKey(), entry.getValue());
        }
    }

    private LuaFunction createLuaFunctionForOverloads(List<Object> overloads) {
        return new VarArgFunction() {
            @Override
            public Varargs invoke(Varargs args) {
                // Extract obj from self (arg1: the ReflectiveLuaWrapper table)
                LuaValue selfArg = args.arg1();
                if (!selfArg.istable()) {
                    return LuaValue.error("Method called without self (use : syntax, e.g., obj:method(args))");
                }
                ReflectiveLuaWrapper selfWrapper = luaObjectToWrapper(selfArg);
                if (selfWrapper == null) {
                    return LuaValue.error("Invalid self: not a ReflectiveLuaWrapper");
                }
                Object obj = selfWrapper.javaObject;
                if (obj == null) {
                    return LuaValue.error("Invalid self: no javaObject");
                }

                // Lua ':' passes self as arg1, so real args start at 2
                int luaArgCount = args.narg() - 1;

                // Rest of your overload resolution logic (unchanged from before)
                for (Object method : overloads) {
                    try {
                        int paramCount = (int) getParameterCount.invoke(method);
                        if (paramCount != luaArgCount) continue;

                        Class<?>[] paramTypes = (Class<?>[]) getParameterTypesHandle.invoke(method);
                        Object[] javaArgs = new Object[paramCount + 1];
                        boolean match = true;

                        // Your modifiers check (as-is; note: this logic seems to disable a method if any other overload has higher modifiers and same param count—review if intended)
                        for (Object methodToCheckModifiersOf : overloads) {
                            if ((int) getModifiersHandle.invoke(methodToCheckModifiersOf) > (int) getModifiersHandle.invoke(method) && luaArgCount == (int) getParameterCount.invoke(methodToCheckModifiersOf)) {
                                match = false;
                            }
                        }

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
                                    // Use your method here too for nested wrappers
                                    ReflectiveLuaWrapper wrappedObject = luaObjectToWrapper(arg);
                                    if (wrappedObject == null) {
                                        javaArgs[i] = arg.touserdata();
                                        continue;
                                    }
                                    javaArgs[i] = wrappedObject.javaObject;
                                }
                            } catch (LuaError e) {
                                match = false;
                                break;
                            }
                        }
                        if (!match) continue;

                        // Invoke the method (your existing logic)
                        setMethodAccessable.invoke(method, true);
                        MethodHandle mh = getHandle(method);

                        Object result;
                        if (paramCount == 0) {
                            result = mh.invoke(obj);
                        } else {
                            javaArgs[0] = obj;
                            result = mh.invokeWithArguments(javaArgs);
                        }

                        Class<?> returnClazz = (Class<?>) getReturnTypeHandle.invoke(method);
                        if (result == null) return LuaValue.NIL;
                        if (result instanceof String || result instanceof Number || result instanceof Boolean) {
                            return CoerceJavaToLua.coerce(result);
                        }
                        return new ReflectiveLuaWrapper(result, returnClazz);

                    } catch (Throwable t) {
                        // Try next overload
                        continue;
                    }
                }
                return LuaValue.error("No overload matches provided arguments");
            }
        };
    }

    public static List<Object> getMethods(Class<?> clazz) throws Throwable {
        List<Object> res = new ArrayList<>();

        Object[] methods = clazz.getDeclaredMethods();
        for (Object method : methods)
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

                        Class<?> clazz = null;
                        Object result = null;

                        if (paramTypes.length == 0) {
                            result = mh.invoke();
//                            clazz = (Class<?>) getReturnTypeHandle.invoke(constructor);
                        } else {
                            result = mh.invokeWithArguments(javaArgs);
//                            clazz = (Class<?>) getReturnTypeHandle.invoke(constructor);
                        }

//                        Object obj = null;
//                        if (paramTypes.length == 0) {
//                            obj = mh.invoke();
//                        } else {
//                            obj = mh.invokeWithArguments(javaArgs);
//                        }

                        return new ReflectiveLuaWrapper(result);

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
