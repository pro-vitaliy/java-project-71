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
        if (fileExtension.equals("json")) {
            mapper = new ObjectMapper();
            fileData = mapper.readValue(fileContent, new TypeReference<>() { });
        } else if (fileExtension.equals("yml") || fileExtension.equals("yaml")) {
            mapper = new YAMLMapper();
            fileData = mapper.readValue(fileContent, new TypeReference<>() { });
        } else {
            throw new Exception("Unexpected file extension: " + fileExtension);
        }
        return fileData;
    }
}
