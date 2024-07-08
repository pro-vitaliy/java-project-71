package hexlet.code;

import hexlet.code.formatters.Plain;
import hexlet.code.formatters.Stylish;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Map;

@AllArgsConstructor
@EqualsAndHashCode(of = "key")
@Getter
public class Differ {
    private String key;
    private String value1;
    private String value2;
    private String diffInfo;

    public static String generate(String filePath1, String filePath2, String formatName) throws Exception {
        Map<String, String> fileData1 = Parser.getData(filePath1);
        Map<String, String> fileData2 = Parser.getData(filePath2);

        var keys = new HashSet<>(fileData1.keySet());
        keys.addAll(fileData2.keySet());

        var diffs = new ArrayList<Differ>();
        for (var key : keys) {
            var info = describeValueChange(key, fileData1, fileData2);
            var v1 = fileData1.get(key);
            var v2 = fileData2.get(key);
            diffs.add(new Differ(key, v1, v2, info));
        }
        diffs.sort(Comparator.comparing(Differ::getKey));
        Formatter formatter = getFormatter(formatName);
        return formatter.format(diffs);
    }

    private static String describeValueChange(String key, Map<String, String> firstMap, Map<String, String> secondMap) {
        if (!firstMap.containsKey(key)) {
            return "added";
        } else if (!secondMap.containsKey(key)) {
            return "removed";
        } else if (!firstMap.get(key).equals(secondMap.get(key))) {
            return "modified";
        } else {
            return "not changed";
        }
    }

    private static Formatter getFormatter(String formatName) {
        return switch (formatName) {
            case "stylish" -> new Stylish();
            case "plain" -> new Plain();
            default -> throw new IllegalArgumentException("Unexpected name of formatter: " + formatName);
        };
    }
}
