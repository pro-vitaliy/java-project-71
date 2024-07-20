package hexlet.code;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class Comparator {
    public static List<Map<String, Object>> getDiffs(Set<String> keys, Map<String, Object> data1,
                                                     Map<String, Object> data2) {
        var diffs = new ArrayList<Map<String, Object>>();
        for (var key : keys) {
            diffs.add(compareValues(key, data1, data2));
        }
        return diffs;
    }

    public static Map<String, Object> compareValues(String key, Map<String, Object> data1, Map<String, Object> data2) {
        Map<String, Object> result = new HashMap<>();
        result.put("key", key);
        var val1 = data1.get(key);
        var val2 = data2.get(key);
        if (!data1.containsKey(key)) {
            result.put("info", "ADDED");
            result.put("value", val2);
        } else if (!data2.containsKey(key)) {
            result.put("info", "DELETED");
            result.put("value", val1);
        } else if (!Objects.equals(val1, val2)) {
            result.put("info", "CHANGED");
            result.put("value1", val1);
            result.put("value2", val2);
        } else {
            result.put("info", "UNCHANGED");
            result.put("value", val2);
        }
        return result;
    }
}
