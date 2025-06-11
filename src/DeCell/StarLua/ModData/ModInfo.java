package DeCell.StarLua.ModData;

import com.fs.starfarer.api.Global;
import org.json.JSONException;
import org.luaj.vm2.Globals;
import org.luaj.vm2.lib.jse.JsePlatform;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;

public class ModInfo {
    // lua code source path & lua code
    public List<LuaFileInfo> Files = new ArrayList<>();

    public LuaModPlugin modPlugin;

    public String modID;

    public ModInfo(List<String> LuaSrcLines, String modID) throws JSONException, IOException {
        this.modID = modID;

        for (String line : LuaSrcLines) {
            // we are seperating the lines values with %s
            String[] parts = line.split(Pattern.quote(","));
            String path = parts[0];
            String type = parts[1];
            String packageName = parts[2];
            String name = parts[3];

            String code = Global.getSettings().loadText(path, modID);
            LuaFileInfo file = new LuaFileInfo(code, path, type, packageName, name);
            Files.add(file);
            if (Objects.equals(type, Headers.ModPlugin)) {
                this.modPlugin = new LuaModPlugin(file);
            }
        }


    }
}
