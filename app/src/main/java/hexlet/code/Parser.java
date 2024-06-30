package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import org.apache.commons.io.FilenameUtils;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class Parser {
    public static Map<String, String> getData(String filePath) throws Exception {
        Path path = Paths.get(filePath).toAbsolutePath().normalize();
        if (!Files.exists(path)) {
            throw new Exception("File " + filePath + " not found");
        }
        String fileExtension = FilenameUtils.getExtension(filePath);
        var result = new HashMap<String, String>();

        ObjectMapper mapper;
        if (fileExtension.equals("json")) {
            mapper = new ObjectMapper();
            result = mapper.readValue(path.toFile(), new TypeReference<>() { });
        }
        if (fileExtension.equals("yml")) {
            mapper = new YAMLMapper();
            result = mapper.readValue(path.toFile(), new TypeReference<>() { });
        }
        return result;
    }
}
