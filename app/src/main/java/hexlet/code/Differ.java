package hexlet.code;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Differ {
    public static String generate(String filePath1, String filePath2) throws Exception {
        Map<String, String> map1 = Parser.getData(filePath1);
        Map<String, String> map2 = Parser.getData(filePath2);
        List<String> keys = Stream.concat(
                map1.keySet().stream(),
                map2.keySet().stream()
                )
                .distinct()
                .sorted()
                .toList();

        var result = new ArrayList<String>();
        for (var key : keys) {
            if (map1.containsKey(key) && map2.containsKey(key)) {
                if (map1.get(key).equals(map2.get(key))) {
                    result.add("  " + key + ": " + map1.get(key));
                } else {
                    result.add("- " + key + ": " + map1.get(key));
                    result.add("+ " + key + ": " + map2.get(key));
                }
            } else if (map1.containsKey(key)) {
                result.add("- " + key + ": " + map1.get(key));
            } else {
                result.add("+ " + key + ": " + map2.get(key));
            }
        }
        return result.stream().collect(Collectors.joining("\n", "{\n", "\n}"));
    }
}
