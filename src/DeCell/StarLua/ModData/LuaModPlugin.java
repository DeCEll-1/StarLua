package DeCell.StarLua.ModData;

import com.thoughtworks.xstream.XStream;
import org.luaj.vm2.Globals;
import org.luaj.vm2.LuaValue;
import org.luaj.vm2.lib.jse.CoerceJavaToLua;
import org.luaj.vm2.lib.jse.JsePlatform;

public class LuaModPlugin {

    private final Globals luaInstance = JsePlatform.standardGlobals();
    public LuaFileInfo fileInfo;

    public LuaModPlugin(LuaFileInfo fileInfo) {
        this.fileInfo = fileInfo;
        // load the lua file
        LuaValue file = luaInstance.load(fileInfo.code, fileInfo.path);
        // execute the script so the functions register
        file.call();

        //#region initalise stuff
        LuaValue fn;

        fn = luaInstance.get("onApplicationLoad");
        this.onApplicationLoad = fn.isfunction() ? fn : null;

        fn = luaInstance.get("afterGameSave");
        this.afterGameSave = fn.isfunction() ? fn : null;

        fn = luaInstance.get("beforeGameSave");
        this.beforeGameSave = fn.isfunction() ? fn : null;

        fn = luaInstance.get("onGameSaveFailed");
        this.onGameSaveFailed = fn.isfunction() ? fn : null;

        fn = luaInstance.get("onEnabled");
        this.onEnabled = fn.isfunction() ? fn : null;

        fn = luaInstance.get("onGameLoad");
        this.onGameLoad = fn.isfunction() ? fn : null;

        fn = luaInstance.get("onNewGame");
        this.onNewGame = fn.isfunction() ? fn : null;

        fn = luaInstance.get("onNewGameAfterEconomyLoad");
        this.onNewGameAfterEconomyLoad = fn.isfunction() ? fn : null;

        fn = luaInstance.get("onNewGameAfterTimePass");
        this.onNewGameAfterTimePass = fn.isfunction() ? fn : null;

        fn = luaInstance.get("configureXStream");
        this.configureXStream = fn.isfunction() ? fn : null;

        fn = luaInstance.get("onNewGameAfterProcGen");
        this.onNewGameAfterProcGen = fn.isfunction() ? fn : null;

        fn = luaInstance.get("onDevModeF8Reload");
        this.onDevModeF8Reload = fn.isfunction() ? fn : null;

        fn = luaInstance.get("onAboutToStartGeneratingCodex");
        this.onAboutToStartGeneratingCodex = fn.isfunction() ? fn : null;

        fn = luaInstance.get("onAboutToLinkCodexEntries");
        this.onAboutToLinkCodexEntries = fn.isfunction() ? fn : null;

        fn = luaInstance.get("onCodexDataGenerated");
        this.onCodexDataGenerated = fn.isfunction() ? fn : null;
//endregion

    }

    //region LuaValue hooks
    public LuaValue afterGameSave = null;
    public LuaValue beforeGameSave = null;
    public LuaValue onGameSaveFailed = null;
    public LuaValue onApplicationLoad = null;
    public LuaValue onEnabled = null;
    public LuaValue onGameLoad = null;
    public LuaValue onNewGame = null;
    public LuaValue onNewGameAfterEconomyLoad = null;
    public LuaValue onNewGameAfterTimePass = null;
    public LuaValue configureXStream = null;
    public LuaValue onNewGameAfterProcGen = null;
    public LuaValue onDevModeF8Reload = null;
    public LuaValue onAboutToStartGeneratingCodex = null;
    public LuaValue onAboutToLinkCodexEntries = null;
    public LuaValue onCodexDataGenerated = null;

    //endregion

    //region Method wrappers
    public void afterGameSave() {
        if (afterGameSave != null) afterGameSave.call();
    }

    public void beforeGameSave() {
        if (beforeGameSave != null) beforeGameSave.call();
    }

    public void onGameSaveFailed() {
        if (onGameSaveFailed != null) onGameSaveFailed.call();
    }

    public void onApplicationLoad() throws Exception {
        if (onApplicationLoad != null) onApplicationLoad.call();
    }

    public void onEnabled(boolean wasEnabledBefore) {
        if (onEnabled != null) onEnabled.call(LuaValue.valueOf(wasEnabledBefore));
    }

    public void onGameLoad(boolean newGame) {
        if (onGameLoad != null) onGameLoad.call(LuaValue.valueOf(newGame));
    }

    public void onNewGame() {
        if (onNewGame != null) onNewGame.call();
    }

    public void onNewGameAfterEconomyLoad() {
        if (onNewGameAfterEconomyLoad != null) onNewGameAfterEconomyLoad.call();
    }

    public void onNewGameAfterTimePass() {
        if (onNewGameAfterTimePass != null) onNewGameAfterTimePass.call();
    }

    public void configureXStream(XStream x) {
        if (configureXStream != null) configureXStream.call(CoerceJavaToLua.coerce(x));
    }

    public void onNewGameAfterProcGen() {
        if (onNewGameAfterProcGen != null) onNewGameAfterProcGen.call();
    }

    public void onDevModeF8Reload() {
        if (onDevModeF8Reload != null) onDevModeF8Reload.call();
    }

    public void onAboutToStartGeneratingCodex() {
        if (onAboutToStartGeneratingCodex != null) onAboutToStartGeneratingCodex.call();
    }

    public void onAboutToLinkCodexEntries() {
        if (onAboutToLinkCodexEntries != null) onAboutToLinkCodexEntries.call();
    }

    public void onCodexDataGenerated() {
        if (onCodexDataGenerated != null) onCodexDataGenerated.call();
    }
    //endregion

}
