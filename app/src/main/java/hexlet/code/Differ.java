package hexlet.code;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Differ {
    public static String generate(String filePath1, String filePath2) throws Exception {

        Map<String, String> map1 = getData(filePath1);
        Map<String, String> map2 = getData(filePath2);

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

    private static Map<String, String> getData(String filePath) throws Exception {
        Path path = Paths.get(filePath).toAbsolutePath().normalize();
        if (!Files.exists(path)) {
            throw new Exception("File " + filePath + " not found");
        }
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(path.toFile(), new TypeReference<>() { });
    }

}
