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
        var value = diff.get("value");
        var value1 = diff.get("value1");
        var value2 = diff.get("value2");
        return switch (info) {
            case "ADDED" -> "+ " + key + ": " + value;
            case "DELETED" -> "- " + key + ": " + value;
            case "CHANGED" -> "- " + key + ": " + value1 + "\n  + " + key + ": " + value2;
            case "UNCHANGED" -> "  " + key + ": " + value;
            default -> throw new IllegalArgumentException("Unexpected diff info value: " + info);
        };
    }
}
