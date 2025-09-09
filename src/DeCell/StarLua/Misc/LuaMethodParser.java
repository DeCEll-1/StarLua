package DeCell.StarLua.Misc;

import java.io.BufferedReader;
import java.io.StringReader;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LuaMethodParser {
    // Matches obj:method or obj:method() (ignores comments and strings)
    private static final Pattern METHOD_CALL_PATTERN = Pattern.compile("\\b[\\w_]+:([\\w_]+)(?:\\(|\\s|$)");

    public static Set<String> extractMethodNames(String luaScript) {
        Set<String> methods = new HashSet<>();
        try (BufferedReader reader = new BufferedReader(new StringReader(luaScript))) {
            String line;
            boolean inCommentBlock = false;
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                // Skip empty lines and single-line comments
                if (line.isEmpty() || line.startsWith("--")) continue;
                // Handle block comments
                if (line.startsWith("--[[")) {
                    inCommentBlock = true;
                    if (line.contains("]]")) inCommentBlock = false; // Single-line block
                    continue;
                }
                if (inCommentBlock) {
                    if (line.contains("]]")) inCommentBlock = false;
                    continue;
                }
                // Skip string literals (basic handling)
                line = line.replaceAll("\"[^\"]*\"", "").replaceAll("'[^']*'", "");
                // Find all :method calls
                Matcher matcher = METHOD_CALL_PATTERN.matcher(line);
                while (matcher.find()) {
                    methods.add(matcher.group(1)); // Capture method name
                }
            }
        } catch (Exception e) {
            System.out.println("Failed to parse Lua script for methods: " + e.getMessage());
        }
//        System.out.println("Parsed methods: " + methods); // Debug
        return methods;
    }
}