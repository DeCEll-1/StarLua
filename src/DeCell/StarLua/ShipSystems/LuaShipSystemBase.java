package DeCell.StarLua.ShipSystems;

import DeCell.StarLua.Functions.CastFunction;
import DeCell.StarLua.Functions.InstanceOfFunction;
import DeCell.StarLua.Misc.LuaMethodParser;
import DeCell.StarLua.Misc.Reflections;
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
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public abstract class LuaShipSystemBase extends BaseShipSystemScript {
    protected LuaValue lua;
    protected Set<String> usedFunctions = new HashSet<>();

    public LuaShipSystemBase() {
    }

    protected void setLuaPath(String path, String modID) throws JSONException, IOException {
        String script = Global.getSettings().loadText(path, modID);

        usedFunctions = LuaMethodParser.extractMethodNames(script);

        Globals globals = JsePlatform.standardGlobals();

        LuaValue globalAPI = null;
        LuaValue shipAPI = null;
        LuaValue color = null;

        LuaValue statusData = null;

        try {

            InstanceOfFunction.addToGlobal(globals);
            CastFunction.addToGlobal(globals);

            globalAPI = new StaticReflectiveLuaWrapper(Global.class);
            globals.set("Global", globalAPI);

            color = new StaticReflectiveLuaWrapper(Color.class);
            ReflectiveLuaWrapper.createConstructor(Color.class, color);

            globals.set("Color", color);

            shipAPI = new StaticReflectiveLuaWrapper(ShipAPI.class);

            globals.set("ShipAPI", shipAPI);

            statusData = new StaticReflectiveLuaWrapper(StatusData.class);
            ReflectiveLuaWrapper.createConstructor(StatusData.class, statusData);

            globals.set("StatusData", statusData);

            // hope this does die and set itself in itself in itself in itself in itself in itself in itself in itself in itself in itself in itself in itself in itself in itself in itself in itself in itself in itself in itself in itself in itself in itself in itself in itself in itself
//            globals.set("this", new ReflectiveLuaWrapper(this));

        } catch (Throwable e) {
            throw new RuntimeException(e);
        }


        LuaValue chunk = globals.load(script);
        lua = chunk.call(); // Lua file must return a table
    }

    @Override
    public void apply(MutableShipStatsAPI stats, String id, State state, float effectLevel) {
        LuaValue func = lua.get("apply");
        if (!func.isfunction()) return;

        // Use cached wrapper
        LuaValue wrappedStats = null;
        try {
            wrappedStats = new ReflectiveLuaWrapper(stats, usedFunctions);
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }

        // Precompute state LuaValues if called every frame
        LuaValue luaState;
        switch (state) {
            case IN -> luaState = LuaValue.valueOf("IN");
            case ACTIVE -> luaState = LuaValue.valueOf("ACTIVE");
            case OUT -> luaState = LuaValue.valueOf("OUT");
            default -> luaState = LuaValue.NIL;
        }

        func.invoke(LuaValue.varargsOf(new LuaValue[]{
                wrappedStats,
                LuaValue.valueOf(id),
                luaState,
                LuaValue.valueOf(effectLevel)
        }));
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
            if (result == LuaValue.NIL)
                return null;

            StatusData res = (StatusData) Reflections.luaObjectToWrapper(result).javaObject;
            return res;
        }
        return null;
    }

    @Override
    public void unapply(MutableShipStatsAPI stats, String id) {
        LuaValue func = lua.get("unapply");
        if (func.isfunction()) {
            LuaValue wrappedStats = null;
            try {
                wrappedStats = new ReflectiveLuaWrapper(stats, usedFunctions);
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
                wrappedSystem = new ReflectiveLuaWrapper(system, usedFunctions);
                wrappedShip = new ReflectiveLuaWrapper(ship, usedFunctions);
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
                wrappedSystem = new ReflectiveLuaWrapper(system, usedFunctions);
                wrappedShip = new ReflectiveLuaWrapper(ship, usedFunctions);
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
                wrappedShip = new ReflectiveLuaWrapper(ship, usedFunctions);
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
                wrappedShip = new ReflectiveLuaWrapper(ship, usedFunctions);
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
                wrappedShip = new ReflectiveLuaWrapper(ship, usedFunctions);
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
                wrappedShip = new ReflectiveLuaWrapper(ship, usedFunctions);
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
                wrappedShip = new ReflectiveLuaWrapper(ship, usedFunctions);
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
