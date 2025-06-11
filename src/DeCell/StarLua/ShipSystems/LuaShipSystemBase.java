package DeCell.StarLua.ShipSystems;

import DeCell.StarLua.Misc.ReflectiveLuaWrapper;
import DeCell.StarLua.Misc.StaticReflectiveLuaWrapper;
import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.combat.MutableShipStatsAPI;
import com.fs.starfarer.api.combat.ShipAPI;
import com.fs.starfarer.api.combat.ShipSystemAPI;
import com.fs.starfarer.api.impl.combat.BaseShipSystemScript;
import org.json.JSONException;
import org.luaj.vm2.Globals;
import org.luaj.vm2.LuaValue;
import org.luaj.vm2.Varargs;
import org.luaj.vm2.lib.jse.JsePlatform;

import java.awt.*;
import java.io.IOException;

public abstract class LuaShipSystemBase extends BaseShipSystemScript {
    protected LuaValue lua;

    public LuaShipSystemBase() {
    }

    protected void setLuaPath(String path, String modID) throws JSONException, IOException {
        Globals globals = JsePlatform.standardGlobals();

        LuaValue globalAPI = null;
        LuaValue color = null;


        try {
            globalAPI = new StaticReflectiveLuaWrapper(Global.class);
            globals.set("_Global", globalAPI);

            color = new StaticReflectiveLuaWrapper(Color.class);
            ReflectiveLuaWrapper.createConstructor(Color.class, color);
            globals.set("Color", color);
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }

        LuaValue chunk = globals.load(Global.getSettings().loadText(path, modID));
        lua = chunk.call(); // Lua file must return a table
    }

    @Override
    public void apply(MutableShipStatsAPI stats, String id, State state, float effectLevel) {
        LuaValue func = lua.get("apply");
        if (func.isfunction()) {
            LuaValue wrappedStats = null;
            try {
                wrappedStats = new ReflectiveLuaWrapper(stats);
            } catch (Throwable e) {
                throw new RuntimeException(e);
            }

            // Now call Lua function with the wrappedStats instead of the raw stats object
            Varargs args = LuaValue.varargsOf(new LuaValue[]{
                    wrappedStats,
                    LuaValue.valueOf(id),
                    LuaValue.valueOf(state.toString()),
                    LuaValue.valueOf(effectLevel)
            });
            func.invoke(args);
        }
    }

    @Override
    public StatusData getStatusData(int index, State state, float effectLevel) {
        LuaValue func = lua.get("getStatusData");

        if (func.isfunction()) {

            // Now call Lua function with the wrappedStats instead of the raw stats object
            Varargs args = LuaValue.varargsOf(new LuaValue[]{
                    LuaValue.valueOf(index),
                    LuaValue.valueOf(state.toString()),
                    LuaValue.valueOf(effectLevel)
            });

            LuaValue result = func.invoke(args).arg(1);
            if (result.istable()) {
                String text = result.get("text").tojstring();
                boolean active = result.get("active").toboolean();
                return new StatusData(text, active);
            }
        }
        return null;
    }

    @Override
    public void unapply(MutableShipStatsAPI stats, String id) {
        LuaValue func = lua.get("unapply");
        if (func.isfunction()) {
            LuaValue wrappedStats = null;
            try {
                wrappedStats = new ReflectiveLuaWrapper(stats);
            } catch (Throwable e) {
                throw new RuntimeException(e);
            }

            // Now call Lua function with the wrappedStats instead of the raw stats object
            Varargs args = LuaValue.varargsOf(new LuaValue[]{
                    wrappedStats,
                    LuaValue.valueOf(id)
            });
            func.invoke(args);
        }
    }

    @Override
    public String getInfoText(ShipSystemAPI system, ShipAPI ship) {
        LuaValue func = lua.get("getInfoText");
        if (func.isfunction()) {

            LuaValue wrappedSystem = null;
            LuaValue wrappedShip = null;
            try {
                wrappedSystem = new ReflectiveLuaWrapper(system);
                wrappedShip = new ReflectiveLuaWrapper(ship);
            } catch (Throwable e) {
                throw new RuntimeException(e);
            }

            Varargs args = LuaValue.varargsOf(new LuaValue[]{
                    wrappedSystem,
                    wrappedShip
            });

            var res = func.invoke(args).tojstring();
            if (!isNil(res)) {
                return res;
            }
            return null;

        }
        return null;
    }

    @Override
    public boolean isUsable(ShipSystemAPI system, ShipAPI ship) {
        LuaValue func = lua.get("isUsable");
        if (func.isfunction()) {
            LuaValue wrappedSystem = null;
            LuaValue wrappedShip = null;
            try {
                wrappedSystem = new ReflectiveLuaWrapper(system);
                wrappedShip = new ReflectiveLuaWrapper(ship);
            } catch (Throwable e) {
                throw new RuntimeException(e);
            }

            Varargs args = LuaValue.varargsOf(new LuaValue[]{
                    wrappedSystem,
                    wrappedShip
            });

            return func.invoke(args).toboolean(1);
        }
        return true;
    }

    @Override
    public float getActiveOverride(ShipAPI ship) {
        LuaValue func = lua.get("getActiveOverride");
        if (func.isfunction()) {
            LuaValue wrappedShip;
            try {
                wrappedShip = new ReflectiveLuaWrapper(ship);
            } catch (Throwable e) {
                throw new RuntimeException(e);
            }
            Varargs args = LuaValue.varargsOf(new LuaValue[]{wrappedShip});
            return (float) func.invoke(args).todouble(1);
        }
        return -1;
    }

    @Override
    public float getInOverride(ShipAPI ship) {
        LuaValue func = lua.get("getInOverride");
        if (func.isfunction()) {
            LuaValue wrappedShip;
            try {
                wrappedShip = new ReflectiveLuaWrapper(ship);
            } catch (Throwable e) {
                throw new RuntimeException(e);
            }
            Varargs args = LuaValue.varargsOf(new LuaValue[]{wrappedShip});
            return (float) func.invoke(args).todouble(1);
        }
        return -1;
    }

    @Override
    public float getOutOverride(ShipAPI ship) {
        LuaValue func = lua.get("getOutOverride");
        if (func.isfunction()) {
            LuaValue wrappedShip;
            try {
                wrappedShip = new ReflectiveLuaWrapper(ship);
            } catch (Throwable e) {
                throw new RuntimeException(e);
            }
            Varargs args = LuaValue.varargsOf(new LuaValue[]{wrappedShip});
            return (float) func.invoke(args).todouble(1);
        }
        return -1;
    }

    @Override
    public float getRegenOverride(ShipAPI ship) {
        LuaValue func = lua.get("getRegenOverride");
        if (func.isfunction()) {
            LuaValue wrappedShip;
            try {
                wrappedShip = new ReflectiveLuaWrapper(ship);
            } catch (Throwable e) {
                throw new RuntimeException(e);
            }
            Varargs args = LuaValue.varargsOf(new LuaValue[]{wrappedShip});
            return (float) func.invoke(args).todouble(1);
        }
        return -1;
    }

    @Override
    public int getUsesOverride(ShipAPI ship) {
        LuaValue func = lua.get("getUsesOverride");
        if (func.isfunction()) {
            LuaValue wrappedShip;
            try {
                wrappedShip = new ReflectiveLuaWrapper(ship);
            } catch (Throwable e) {
                throw new RuntimeException(e);
            }
            Varargs args = LuaValue.varargsOf(new LuaValue[]{wrappedShip});
            return func.invoke(args).toint(1);
        }
        return -1;
    }

    @Override
    public String getDisplayNameOverride(State state, float effectLevel) {
        LuaValue func = lua.get("getDisplayNameOverride");
        if (func.isfunction()) {

            Varargs args = LuaValue.varargsOf(new LuaValue[]{
                    LuaValue.valueOf(state.toString()),
                    LuaValue.valueOf(effectLevel)
            });


            var res = func.invoke(args).tojstring();
            if (!isNil(res)) {
                return res;
            }
            return null;
        }
        return null;
    }

    private static boolean isNil(String str) {
        if (str == null || str.isEmpty() || str.equals("nil")) {
            return true;
        }
        return false;
    }
}
