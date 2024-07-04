package hexlet.code;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Differ {
    public static String generate(String filePath1, String filePath2, String format) throws Exception {
        Map<String, String> map1 = Parser.getData(filePath1);
        Map<String, String> map2 = Parser.getData(filePath2);

        List<String> keys = Stream.concat(
                map1.keySet().stream(),
                map2.keySet().stream()
                )
                .distinct()
                .sorted()
                .toList();

        var diffList = new ArrayList<String>();
        for (var key : keys) {
            if (format.equals("stylish")) {
                diffList.add(Formatter.stylish(key, map1, map2));
            }
        }
        return diffList.stream().collect(Collectors.joining("\n", "{\n", "\n}"));
    }
}
