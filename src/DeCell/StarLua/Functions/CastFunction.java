package DeCell.StarLua.Functions;

import DeCell.StarLua.Misc.ReflectiveLuaWrapper;
import DeCell.StarLua.Misc.StaticReflectiveLuaWrapper;
import org.luaj.vm2.Globals;
import org.luaj.vm2.LuaValue;
import org.luaj.vm2.Varargs;
import org.luaj.vm2.lib.VarArgFunction;

public class CastFunction extends VarArgFunction {

    public static void addToGlobal(Globals globals) {
        globals.set("cast", new CastFunction());
    }


    @Override
    public Varargs invoke(Varargs args) {
        LuaValue cast = args.arg(1);
        LuaValue to = args.arg(2);

        if (cast instanceof ReflectiveLuaWrapper) {
            // get the java object from our wrapper
            Object javaObject = ((ReflectiveLuaWrapper) cast).javaObject;
            if (to instanceof StaticReflectiveLuaWrapper) {
                // get the class from our static wrapper (we send classes as static objects, ish ig idk lol)
                Class<?> clazz = ((StaticReflectiveLuaWrapper) to).clazz;
                try {
                    // if its the same instance, we *shouldnt* need to cast, i think
//                    if (clazz.isInstance(javaObject)){
//                        return cast;
//                    }
                    return new ReflectiveLuaWrapper(clazz.cast(javaObject));
                } catch (Throwable e) {
                    return LuaValue.NIL;
                }
            }
        }

        return LuaValue.FALSE;
    }
}
