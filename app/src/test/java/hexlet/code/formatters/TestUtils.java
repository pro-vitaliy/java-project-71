package hexlet.code.formatters;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestUtils {
    public static List<Map<String, Object>> getDataAdded() {
        var diffAdded = new ArrayList<Map<String, Object>>();
        var contentAdded = new HashMap<String, Object>();
        contentAdded.put("key", "k");
        contentAdded.put("value", List.of(1, 2, 3));
        contentAdded.put("info", "ADDED");
        diffAdded.add(contentAdded);
        return diffAdded;
    }

    public static List<Map<String, Object>> getDataDeleted() {
        var diffDeleted = new ArrayList<Map<String, Object>>();
        var contentDeleted = new HashMap<String, Object>();
        contentDeleted.put("key", "k");
        contentDeleted.put("value", null);
        contentDeleted.put("info", "DELETED");
        diffDeleted.add(contentDeleted);
        return diffDeleted;
    }

    public static List<Map<String, Object>> getDataUnchanged() {
        var diffUnchanged = new ArrayList<Map<String, Object>>();
        var contentUnchanged = new HashMap<String, Object>();
        contentUnchanged.put("key", "k");
        contentUnchanged.put("value", "v");
        contentUnchanged.put("info", "UNCHANGED");
        diffUnchanged.add(contentUnchanged);
        return diffUnchanged;
    }

    public static List<Map<String, Object>> getDataChanged() {
        var diffChanged = new ArrayList<Map<String, Object>>();
        var contentChanged = new HashMap<String, Object>();
        contentChanged.put("key", "k");
        contentChanged.put("oldValue", 2.5);
        contentChanged.put("newValue", 0.1);
        contentChanged.put("info", "CHANGED");
        diffChanged.add(contentChanged);
        return diffChanged;
    }
}
