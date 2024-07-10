package hexlet.code;

import hexlet.code.formatters.Json;
import hexlet.code.formatters.Plain;
import hexlet.code.formatters.Stylish;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashSet;
import java.util.List;
import java.util.Collections;

public class Differ {

    public static String generate(String filePath1, String filePath2, String formatName) throws Exception {
        Map<String, Object> fileData1 = Parser.getData(filePath1);
        Map<String, Object> fileData2 = Parser.getData(filePath2);

        var keys = new HashSet<>(fileData1.keySet());
        keys.addAll(fileData2.keySet());
        List<String> sortedKeys = new ArrayList<>(keys);
        Collections.sort(sortedKeys);

        var diffs = new ArrayList<Map<String, Object>>();
        for (var key : sortedKeys) {
            diffs.add(Comparator.compareValues(key, fileData1, fileData2));
        }
        Formatter formatter = getFormatter(formatName);
        return formatter.format(diffs);
    }

    private static Formatter getFormatter(String formatName) {
        return switch (formatName) {
            case "stylish" -> new Stylish();
            case "plain" -> new Plain();
            case "json" -> new Json();
            default -> throw new IllegalArgumentException("Unexpected name of formatter: " + formatName);
        };
    }
}
