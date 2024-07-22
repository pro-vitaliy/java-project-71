package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

import java.util.HashMap;
import java.util.Map;

public class Parser {
    public static Map<String, Object> getData(String fileContent, String fileExtension) throws Exception {
        var fileData = new HashMap<String, Object>();

        ObjectMapper mapper;
        switch (fileExtension) {
            case "json" -> {
                mapper = new ObjectMapper();
                fileData = mapper.readValue(fileContent, new TypeReference<>() {
                });
            }
            case "yml", "yaml" -> {
                mapper = new YAMLMapper();
                fileData = mapper.readValue(fileContent, new TypeReference<>() {
                });
            }
            default -> throw new Exception("Unexpected file extension: " + fileExtension);
        }
        return fileData;
    }
}
