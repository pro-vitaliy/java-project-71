package hexlet.code;

import java.util.HashMap;
import java.util.Map;

public class Comparator {
    public static Map<String, Object> compareValues(String key, Map<String, Object> data1, Map<String, Object> data2) {
        Map<String, Object> result = new HashMap<>();
        result.put("key", key);
        var val1 = data1.get(key) != null ? data1.get(key).toString() : "null";
        var val2 = data2.get(key) != null ? data2.get(key).toString() : "null";
        if (!data1.containsKey(key)) {
            result.put("info", "ADDED");
            result.put("value", data2.get(key));
        } else if (!data2.containsKey(key)) {
            result.put("info", "DELETED");
            result.put("value", data1.get(key));
        } else if (!val1.equals(val2)) {
            result.put("info", "CHANGED");
            result.put("oldValue", data1.get(key));
            result.put("newValue", data2.get(key));
        } else {
            result.put("info", "UNCHANGED");
            result.put("value", data2.get(key));
        }
        return result;
    }
}
