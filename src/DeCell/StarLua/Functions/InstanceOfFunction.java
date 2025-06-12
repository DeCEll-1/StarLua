package DeCell.StarLua.Functions;

import DeCell.StarLua.Misc.ReflectiveLuaWrapper;
import DeCell.StarLua.Misc.StaticReflectiveLuaWrapper;
import org.luaj.vm2.*;
import org.luaj.vm2.lib.*;

public class InstanceOfFunction extends VarArgFunction {

    public static void addToGlobal(Globals globals) {
        globals.set("instanceof", new InstanceOfFunction());
    }


    @Override
    public Varargs invoke(Varargs args) {
        LuaValue instance = args.arg(1);
        LuaValue of = args.arg(2);

        if (instance instanceof ReflectiveLuaWrapper) {
            // get the java object from our wrapper
            Object javaObject = ((ReflectiveLuaWrapper) instance).javaObject;
            if (of instanceof StaticReflectiveLuaWrapper) {
                // get the class from our static wrapper (we send classes as static objects, ish ig idk lol)
                Class<?> clazz = ((StaticReflectiveLuaWrapper) of).clazz;
                if (clazz.isInstance(javaObject)) {
                    return LuaValue.TRUE;
                }
            }
        }

        return LuaValue.FALSE;
    }
}
