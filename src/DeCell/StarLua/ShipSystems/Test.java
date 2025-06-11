package DeCell.StarLua.ShipSystems;

import DeCell.StarLua.ShipSystems.LuaShipSystemBase;
import org.json.JSONException;

import java.io.IOException;

public class Test extends LuaShipSystemBase {
    public Test() throws JSONException, IOException {
        super.setLuaPath("lua/mymod/MyLuaShipSystem.lua", "id");
    }
}