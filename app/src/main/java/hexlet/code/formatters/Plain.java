package hexlet.code.formatters;

import hexlet.code.Differ;
import hexlet.code.Formatter;

import java.util.List;
import java.util.stream.Collectors;

public class Plain implements Formatter {
    @Override
    public String format(List<Differ> diffs) {
        return diffs.stream()
                .filter(diff -> !diff.getDiffInfo().equals("not changed"))
                .map(Plain::generate)
                .collect(Collectors.joining("\n    ", "{\n    ", "\n}"));
    }

    private static String generate(Differ diff) {
        var key = diff.getKey();
        var info = diff.getDiffInfo();
        return switch (info) {
            case "modified" -> {
                var value1 = isNested(diff.getValue1()) ? "[complex value]" : diff.getValue1();
                var value2 = isNested(diff.getValue2()) ? "[complex value]" : diff.getValue2();
                yield "Property '%s' was updated. From %s to %s".formatted(key, value1, value2);
            }
            case "added" -> {
                var added = isNested(diff.getValue2()) ? "[complex value]" : diff.getValue2();
                yield "Property '%s' was added with value: %s".formatted(key, added);
            }
            case "removed" -> "Property '%s' was removed".formatted(key);
            default -> throw new IllegalStateException("Unexpected value: " + info);
        };
    }

    private static boolean isNested(String value) {
        return (value.startsWith("[") && value.endsWith("]")) ||
                (value.startsWith("{") && value.endsWith("}"));
    }
}
