package hexlet.code.formatters;

import com.fasterxml.jackson.databind.ObjectMapper;
import hexlet.code.Formatter;

import java.util.List;
import java.util.Map;

public final class Json implements Formatter {
    @Override
    public String format(List<Map<String, Object>> diffs) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(diffs);
    }
}
