package hexlet.code.formatters;

import hexlet.code.Differ;
import hexlet.code.Formatter;

import java.util.List;
import java.util.stream.Collectors;

public class Stylish implements Formatter {
    @Override
    public String format(List<Differ> diffs) {
        return diffs.stream()
                .map(Stylish::generate)
                .collect(Collectors.joining("\n  ", "{\n  ", "\n}"));
    }

    private static String generate(Differ diff) {
        var key = diff.getKey();
        var info = diff.getDiffInfo();
        return switch (info) {
            case "added" -> "+ " + key + ": " + diff.getValue2();
            case "removed" -> "- " + key + ": " + diff.getValue1();
            case "modified" -> "- " + key + ": " + diff.getValue1() + "\n  + " + key + ": " + diff.getValue2();
            case "not changed" -> "  " + key + ": " + diff.getValue1();
            default -> throw new IllegalStateException("Unexpected value: " + info);
        };
    }
}
