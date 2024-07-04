package hexlet.code;

import java.util.Map;

public class Formatter {
    public static String stylish(String key, Map<String, String> oldMap, Map<String, String> newMap) {
        if (!oldMap.containsKey(key)) {
            return "  + " + key + ": " + newMap.get(key);
        }
        if (!newMap.containsKey(key)) {
            return "  - " + key + ": " + oldMap.get(key);
        }
        if (oldMap.get(key).equals(newMap.get(key))) {
            return " ".repeat(4) + key + ": " + oldMap.get(key);
        }
        return  "  - " + key + ": " + oldMap.get(key) + "\n  + " + key + ": " + newMap.get(key);
    }
}
