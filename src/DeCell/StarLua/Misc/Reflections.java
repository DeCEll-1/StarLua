package DeCell.StarLua.Misc;

import org.luaj.vm2.LuaValue;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;

import static DeCell.StarLua.Misc.ReflectiveLuaWrapper.WRAPPER_METATABLE;


public class Reflections {

    public static ReflectiveLuaWrapper luaObjectToWrapper(LuaValue val) {
        if (val instanceof ReflectiveLuaWrapper) {
            return (ReflectiveLuaWrapper) val;
        }
        if (val.istable() && val.getmetatable() == WRAPPER_METATABLE) {
            // Assuming the table holds the instance itself (since ReflectiveLuaWrapper *is* the LuaTable)
            return (ReflectiveLuaWrapper) val;
        }
        return null;
    }


    //region reflection
    public static final MethodHandles.Lookup lookup = MethodHandles.lookup();
    public static final Class<?> fieldClass;
    public static final Class<?> fieldArrayClass;
    public static final Class<?> methodClass;
    public static final Class<?> typeClass;
    public static final Class<?> typeArrayClass;
    public static final Class<?> parameterizedTypeClass;
    public static final Class<?> constructorClass;
    public static final Class<?> constructorArrayClass;
    public static final MethodHandle getFieldTypeHandle;
    public static final MethodHandle setFieldHandle;
    public static final MethodHandle getFieldHandle;
    public static final MethodHandle getFieldNameHandle;
    public static final MethodHandle setFieldAccessibleHandle;
    public static final MethodHandle getParameterCount;
    public static final MethodHandle getMethodNameHandle;
    public static final MethodHandle invokeMethodHandle;
    public static final MethodHandle setMethodAccessable;
    public static final MethodHandle getModifiersHandle;
    public static final MethodHandle getParameterTypesHandle;
    public static final MethodHandle getReturnTypeHandle;
    public static final MethodHandle getDeclaringClass;
    public static final MethodHandle getGenericTypeHandle;
    public static final MethodHandle getTypeNameHandle;
    public static final MethodHandle getActualTypeArgumentsHandle;
    public static final MethodHandle setConstructorAccessibleHandle;
    public static final MethodHandle getDeclaredConstructorsHandle;
    public static final MethodHandle getConstructorParameterTypesHandle;
    public static final MethodHandle constructorGetDeclaringClass;
    public static final MethodHandle constructorNewInstanceHandle;
    public static final Class<?> modifierClass;
    public static final MethodHandle modifierIsPublic;
    public static final MethodHandle modifierIsStatic;
    public static final MethodHandle methodIsSynthetic;

    public static final MethodHandle methodIsBridge;

    public static final Class<?> filesClass;
    public static final Class<?> pathClass;
    public static final MethodHandle filesReadStringHandle;
    public static final Class<?> uriClass;
    public static final MethodHandle pathOfUriHandle;

    public static final MethodHandle uriCreateHandle;
    public static final MethodHandle filesDeleteHandle;
    public static final MethodHandle filesDeleteIfExistsHandle;

    static {
        try {
            fieldClass = Class.forName("java.lang.reflect.Field", false, Class.class.getClassLoader());
            fieldArrayClass = Class.forName("[Ljava.lang.reflect.Field;", false, Class.class.getClassLoader());
            methodClass = Class.forName("java.lang.reflect.Method", false, Class.class.getClassLoader());
            typeClass = Class.forName("java.lang.reflect.Type", false, Class.class.getClassLoader());
            typeArrayClass = Class.forName("[Ljava.lang.reflect.Type;", false, Class.class.getClassLoader());
            parameterizedTypeClass = Class.forName("java.lang.reflect.ParameterizedType", false, Class.class.getClassLoader());
            constructorClass = Class.forName("java.lang.reflect.Constructor", false, Class.class.getClassLoader());
            constructorArrayClass = Class.forName("[Ljava.lang.reflect.Constructor;", false, Class.class.getClassLoader());

            setFieldHandle = lookup.findVirtual(fieldClass, "set", MethodType.methodType(void.class, Object.class, Object.class));
            getFieldHandle = lookup.findVirtual(fieldClass, "get", MethodType.methodType(Object.class, Object.class));
            getFieldNameHandle = lookup.findVirtual(fieldClass, "getName", MethodType.methodType(String.class));
            getFieldTypeHandle = lookup.findVirtual(fieldClass, "getType", MethodType.methodType(Class.class));
            setFieldAccessibleHandle = lookup.findVirtual(fieldClass, "setAccessible", MethodType.methodType(void.class, boolean.class));


            getMethodNameHandle = lookup.findVirtual(methodClass, "getName", MethodType.methodType(String.class));
            invokeMethodHandle = lookup.findVirtual(methodClass, "invoke", MethodType.methodType(Object.class, Object.class, Object[].class));
            setMethodAccessable = lookup.findVirtual(methodClass, "setAccessible", MethodType.methodType(void.class, boolean.class));
            getModifiersHandle = lookup.findVirtual(methodClass, "getModifiers", MethodType.methodType(int.class));
            getParameterTypesHandle = lookup.findVirtual(methodClass, "getParameterTypes", MethodType.methodType(Class[].class));
            getReturnTypeHandle = lookup.findVirtual(methodClass, "getReturnType", MethodType.methodType(Class.class));
            getParameterCount = lookup.findVirtual(methodClass, "getParameterCount", MethodType.methodType(int.class));

            getDeclaringClass = lookup.findVirtual(methodClass, "getDeclaringClass", MethodType.methodType(Class.class));

            getGenericTypeHandle = lookup.findVirtual(fieldClass, "getGenericType", MethodType.methodType(typeClass));
            getTypeNameHandle = lookup.findVirtual(typeClass, "getTypeName", MethodType.methodType(String.class));
            getActualTypeArgumentsHandle = lookup.findVirtual(parameterizedTypeClass, "getActualTypeArguments", MethodType.methodType(typeArrayClass));

            setConstructorAccessibleHandle = lookup.findVirtual(constructorClass, "setAccessible", MethodType.methodType(void.class, boolean.class));
            getConstructorParameterTypesHandle = lookup.findVirtual(constructorClass, "getParameterTypes", MethodType.methodType(Class[].class));
            constructorNewInstanceHandle = lookup.findVirtual(constructorClass, "newInstance", MethodType.methodType(Object.class, Object[].class));

            constructorGetDeclaringClass = lookup.findVirtual(constructorClass, "getDeclaringClass", MethodType.methodType(Class.class));

            getDeclaredConstructorsHandle = lookup.findVirtual(Class.class, "getDeclaredConstructors", MethodType.methodType(constructorArrayClass));

            modifierClass = Class.forName("java.lang.reflect.Modifier", false, Class.class.getClassLoader());

            modifierIsPublic = lookup.findStatic(modifierClass, "isPublic", MethodType.methodType(boolean.class, int.class));
            modifierIsStatic = lookup.findStatic(modifierClass, "isStatic", MethodType.methodType(boolean.class, int.class));

            methodIsSynthetic = lookup.findVirtual(methodClass, "isSynthetic", MethodType.methodType(boolean.class));
            methodIsBridge = lookup.findVirtual(methodClass, "isBridge", MethodType.methodType(boolean.class));

            filesClass = Class.forName("java.nio.file.Files", false, Class.class.getClassLoader());
            pathClass = Class.forName("java.nio.file.Path", false, Class.class.getClassLoader());
            uriClass = Class.forName("java.net.URI", false, Class.class.getClassLoader());

            pathOfUriHandle = lookup.findStatic(pathClass, "of", MethodType.methodType(pathClass, uriClass));

            // Files.readString(Path) -> String
            filesReadStringHandle = lookup.findStatic(filesClass, "readString", MethodType.methodType(String.class, pathClass));

            uriCreateHandle = lookup.findStatic(
                    uriClass,
                    "create",
                    MethodType.methodType(uriClass, String.class)
            );

            filesDeleteHandle = lookup.findStatic(
                    filesClass,
                    "delete",
                    MethodType.methodType(void.class, pathClass)
            );

            // Files.deleteIfExists(Path) -> boolean
            filesDeleteIfExistsHandle = lookup.findStatic(
                    filesClass,
                    "deleteIfExists",
                    MethodType.methodType(boolean.class, pathClass)
            );

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
//endregion

}
