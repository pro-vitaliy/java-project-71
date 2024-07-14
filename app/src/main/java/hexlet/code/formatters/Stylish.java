package hexlet.code.formatters;

import hexlet.code.Formatter;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public final class Stylish implements Formatter {
    @Override
    public String format(List<Map<String, Object>> diffs) {
        return diffs.stream()
                .map(Stylish::generate)
                .collect(Collectors.joining("\n  ", "{\n  ", "\n}"));
    }

    private static String generate(Map<String, Object> diff) {
        var key = diff.get("key").toString();
        var info = diff.get("info").toString();
        var value = diff.get("value") != null ? diff.get("value").toString() : "null";
        var oldValue = diff.get("oldValue") != null ? diff.get("oldValue").toString() : "null";
        var newValue = diff.get("newValue") != null ? diff.get("newValue").toString() : "null";
        return switch (info) {
            case "ADDED" -> "+ " + key + ": " + value;
            case "DELETED" -> "- " + key + ": " + value;
            case "CHANGED" -> "- " + key + ": " + oldValue + "\n  + " + key + ": " + newValue;
            case "UNCHANGED" -> "  " + key + ": " + value;
            default -> throw new IllegalStateException("Unexpected value: " + info);
        };
    }
}
