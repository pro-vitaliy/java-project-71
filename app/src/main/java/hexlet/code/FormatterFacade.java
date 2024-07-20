package hexlet.code;

import hexlet.code.formatters.Json;
import hexlet.code.formatters.Plain;
import hexlet.code.formatters.Stylish;

import java.util.List;
import java.util.Map;


public class FormatterFacade {
    public static String formatData(List<Map<String, Object>> diffs, String formatName) throws Exception {
        Formatter formatter = switch (formatName) {
            case "stylish" -> new Stylish();
            case "plain" -> new Plain();
            case "json" -> new Json();
            default -> throw new Exception("Unexpected name of formatter: " + formatName);
        };
        return formatter.format(diffs);
    }
}
