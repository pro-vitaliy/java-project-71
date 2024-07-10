package hexlet.code.formatters;

import com.fasterxml.jackson.databind.ObjectMapper;
import hexlet.code.Formatter;
import lombok.SneakyThrows;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Json implements Formatter {
    @Override
    public String format(List<Map<String, Object>> diffs) {
        return diffs.stream()
                .map(Json::toJsonString)
                .collect(Collectors.joining(", "));
    }

    @SneakyThrows
    private static String toJsonString(Map<String, Object> diff) {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(diff);
    }
}
