package hexlet.code.formatters;

import hexlet.code.Formatter;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    private static final Formatter FORMATTER = new Json();

    @Test
    public void testFormatAdded() {

        var diff = TestUtils.getDataAdded();
        var actual = FORMATTER.format(diff);
        var expected = "{\"value\":[1,2,3],\"key\":\"k\",\"info\":\"ADDED\"}";
        assertEquals(expected, actual);
    }

    @Test
    public void testFormatDeleted() {

        var diff = TestUtils.getDataDeleted();
        var actual = FORMATTER.format(diff);
        var expected = "{\"value\":null,\"key\":\"k\",\"info\":\"DELETED\"}";
        assertEquals(expected, actual);
    }

    @Test
    public void testFormatUnchanged() {

        var diff = TestUtils.getDataUnchanged();
        var actual = FORMATTER.format(diff);
        var expected = "{\"value\":\"v\",\"key\":\"k\",\"info\":\"UNCHANGED\"}";
        assertEquals(expected, actual);
    }

    @Test
    public void testFormatChanged() {

        var diff = TestUtils.getDataChanged();
        var actual = FORMATTER.format(diff);
        var expected = "{\"newValue\":0.1,\"oldValue\":2.5,\"key\":\"k\",\"info\":\"CHANGED\"}";
        assertEquals(expected, actual);
    }
}
