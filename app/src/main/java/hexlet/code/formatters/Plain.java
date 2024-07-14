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
                var oldValue = convertValueToString(diff.get("oldValue"));
                var newValue = convertValueToString(diff.get("newValue"));
                yield "Property '%s' was updated. From %s to %s".formatted(key, oldValue, newValue);
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
        if (value == null) {
            return "null";
        }
        String stringValue = value.toString();
        if (value instanceof String) {
            return "'%s'".formatted(stringValue);
        } else if (value.getClass().isArray() || value instanceof Collection || value instanceof Map) {
            return "[complex value]";
        } else {
            return stringValue;
        }
    }
}
