package hexlet.code.formatters;

import hexlet.code.Formatter;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PlainTest {
    private static final Formatter FORMATTER = new Plain();

    @Test
    public void testFormatDeleted() {
        var diffs = TestUtils.getDataDeleted();
        String expected = "Property 'k' was removed";
        String actual = FORMATTER.format(diffs);
        assertEquals(expected, actual);
    }

    @Test
    public void testFormatChanged() {
        var diffs = TestUtils.getDataChanged();
        String expected = "Property 'k' was updated. From 2 to 1";
        String actual = FORMATTER.format(diffs);
        assertEquals(expected, actual);
    }

    @Test
    public void testFormatUnchanged() {
        var diffs = TestUtils.getDataUnchanged();
        var expected = "";
        String actual = FORMATTER.format(diffs);
        assertEquals(expected, actual);
    }

    @Test
    public void testFormatAddedWithComplexValue() {
        var diffs = TestUtils.getDataAdded();
        String expected = "Property 'k' was added with value: [complex value]";
        String actual = FORMATTER.format(diffs);
        assertEquals(expected, actual);
    }
}
