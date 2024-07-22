package hexlet.code.formatters;

import hexlet.code.Formatter;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public final class Plain implements Formatter {
    @Override
    public String format(List<Map<String, Object>> diffs) {
        return diffs.stream()
                .filter(diff -> !diff.get("info").equals("UNCHANGED"))
                .map(Plain::generate)
                .collect(Collectors.joining("\n"));
    }

    private static String generate(Map<String, Object> diff) {
        var key = diff.get("key").toString();
        var info = diff.get("info").toString();
        return switch (info) {
            case "CHANGED" -> {
                var value1 = convertValueToString(diff.get("value1"));
                var value2 = convertValueToString(diff.get("value2"));
                yield "Property '%s' was updated. From %s to %s".formatted(key, value1, value2);
            }
            case "ADDED" -> {
                var value = convertValueToString(diff.get("value"));
                yield "Property '%s' was added with value: %s".formatted(key, value);
            }
            case "DELETED" -> "Property '%s' was removed".formatted(key);
            default -> throw new IllegalArgumentException("Unexpected value: " + info);
        };
    }

    private static String convertValueToString(Object value) {
        if (value instanceof String) {
            return "'%s'".formatted(value);
        } else if (value instanceof Collection || value instanceof Map) {
            return "[complex value]";
        } else {
            return String.valueOf(value);
        }
    }
}
