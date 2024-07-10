package hexlet.code.formatters;

import hexlet.code.Formatter;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.assertj.core.api.Assertions.catchThrowable;

public class JsonTest {
    @Test
    public void testFormatAdded() {
        var diff = new ArrayList<Map<String, Object>>();
        diff.add(Map.of("key", "k1", "value", 10, "info", "ADDED"));

        Formatter formatter = new Json();
        var actual = formatter.format(diff);
        var expected = "{\"value\":10,\"key\":\"k1\",\"info\":\"ADDED\"}";
        assertEquals(expected, actual);
    }
}
