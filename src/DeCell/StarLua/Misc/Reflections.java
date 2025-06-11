package DeCell.StarLua.Misc;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;

public class Reflections {

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

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
//endregion

}
