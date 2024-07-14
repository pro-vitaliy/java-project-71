package hexlet.code.formatters;

import hexlet.code.Formatter;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class StylishTest {
    private static final Formatter FORMATTER = new Stylish();

    @Test
    public void testFormatAdded() {
        var diff = TestUtils.getDataAdded();
        String actual = FORMATTER.format(diff);
        String expected = "{\n  + k: [1, 2]\n}";
        assertEquals(expected, actual);
    }

    @Test
    public void testFormatDeleted() {
        var diff = TestUtils.getDataDeleted();
        String actual = FORMATTER.format(diff);
        String expected = "{\n  - k: null\n}";
        assertEquals(expected, actual);
    }

    @Test
    public void testFormatChanged() {
        var diff = TestUtils.getDataChanged();
        String actual = FORMATTER.format(diff);
        String expected = "{\n  - k: 2\n  + k: 1\n}";
        assertEquals(expected, actual);
    }

    @Test
    public void testFormatUnchanged() {
        var diff = TestUtils.getDataUnchanged();
        String actual = FORMATTER.format(diff);
        String expected = "{\n    k: v\n}";
        assertEquals(expected, actual);
    }
}
