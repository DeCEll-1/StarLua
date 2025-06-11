package DeCell.StarLua.ModData;

public class LuaFileInfo {

    public String code;
    public String path;
    public String type;
    public String packageName;
    public String name;

    public LuaFileInfo(String code, String path, String type, String packageName, String name) {
        this.code = code;
        this.path = path;
        this.type = type;
        this.packageName = packageName;
        this.name = name;
    }
}
