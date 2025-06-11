package DeCell.StarLua.Plugins;

import DeCell.StarLua.ModData.ModInfo;
import com.fs.starfarer.api.BaseModPlugin;
import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.ModSpecAPI;
import com.thoughtworks.xstream.XStream;
import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;


public class ModPlugin extends BaseModPlugin {

    public static List<ModInfo> luaMods = new ArrayList<>();

    @Override
    public void onApplicationLoad() throws Exception {
        for (ModSpecAPI k : Global.getSettings().getModManager().getEnabledModsCopy()) {
            String modId = k.getId();
            var modInfoJson = Global.getSettings().loadJSON("mod_Info.json", modId);
            if (!modInfoJson.has("LuaList"))
                continue;

            String listPath = modInfoJson.getString("LuaList");

            String luasToLoad = Global.getSettings().loadText(listPath, modId);

            String[] paths = luasToLoad.split(Pattern.quote("\n"));
            luaMods.add(new ModInfo(Arrays.asList(paths), modId));
        }

        for (ModInfo luaMod : luaMods)
            if (luaMod.modPlugin != null)
                luaMod.modPlugin.onApplicationLoad();
    }

    //region lua calls
    @Override
    public void afterGameSave() {
        for (ModInfo luaMod : luaMods)
            if (luaMod.modPlugin != null)
                luaMod.modPlugin.afterGameSave();
    }

    @Override
    public void beforeGameSave() {
        for (ModInfo luaMod : luaMods)
            if (luaMod.modPlugin != null)
                luaMod.modPlugin.beforeGameSave();
    }

    @Override
    public void onGameSaveFailed() {
        for (ModInfo luaMod : luaMods)
            if (luaMod.modPlugin != null)
                luaMod.modPlugin.onGameSaveFailed();
    }

    @Override
    public void onEnabled(boolean wasEnabledBefore) {
        for (ModInfo luaMod : luaMods)
            if (luaMod.modPlugin != null)
                luaMod.modPlugin.onEnabled(wasEnabledBefore);
    }

    @Override
    public void onGameLoad(boolean newGame) {
        for (ModInfo luaMod : luaMods)
            if (luaMod.modPlugin != null)
                luaMod.modPlugin.onGameLoad(newGame);
    }

    @Override
    public void onNewGame() {
        for (ModInfo luaMod : luaMods)
            if (luaMod.modPlugin != null)
                luaMod.modPlugin.onNewGame();
    }

    @Override
    public void onNewGameAfterEconomyLoad() {
        for (ModInfo luaMod : luaMods)
            if (luaMod.modPlugin != null)
                luaMod.modPlugin.onNewGameAfterEconomyLoad();
    }

    @Override
    public void onNewGameAfterTimePass() {
        for (ModInfo luaMod : luaMods)
            if (luaMod.modPlugin != null)
                luaMod.modPlugin.onNewGameAfterTimePass();
    }

    @Override
    public void configureXStream(XStream x) {
        for (ModInfo luaMod : luaMods)
            if (luaMod.modPlugin != null)
                luaMod.modPlugin.configureXStream(x);
    }

    @Override
    public void onNewGameAfterProcGen() {
        for (ModInfo luaMod : luaMods)
            if (luaMod.modPlugin != null)
                luaMod.modPlugin.onNewGameAfterProcGen();
    }

    @Override
    public void onDevModeF8Reload() {
        for (ModInfo luaMod : luaMods)
            if (luaMod.modPlugin != null)
                luaMod.modPlugin.onDevModeF8Reload();
    }

    @Override
    public void onAboutToStartGeneratingCodex() {
        for (ModInfo luaMod : luaMods)
            if (luaMod.modPlugin != null)
                luaMod.modPlugin.onAboutToStartGeneratingCodex();
    }

    @Override
    public void onAboutToLinkCodexEntries() {
        for (ModInfo luaMod : luaMods)
            if (luaMod.modPlugin != null)
                luaMod.modPlugin.onAboutToLinkCodexEntries();
    }

    @Override
    public void onCodexDataGenerated() {
        for (ModInfo luaMod : luaMods)
            if (luaMod.modPlugin != null)
                luaMod.modPlugin.onCodexDataGenerated();

    }
    //endregion
}
