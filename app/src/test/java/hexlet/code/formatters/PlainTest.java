package hexlet.code.formatters;

import hexlet.code.Formatter;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PlainTest {
    private static final Formatter FORMATTER = new Plain();

    @Test
    public void testFormatDeleted() {
        var diffs = TestUtils.getDataDeleted();
        String expected = "\nProperty 'k' was removed\n";
        String actual = FORMATTER.format(diffs);
        assertEquals(expected, actual);
    }

    @Test
    public void testFormatChanged() {
        var diffs = TestUtils.getDataChanged();
        String expected = "\nProperty 'k' was updated. From 2.5 to 0.1\n";
        String actual = FORMATTER.format(diffs);
        assertEquals(expected, actual);
    }

    @Test
    public void testFormatUnchanged() {
        var diffs = TestUtils.getDataUnchanged();
        var expected = "\n\n";
        String actual = FORMATTER.format(diffs);
        assertEquals(expected, actual);
    }

    @Test
    public void testFormatAddedWithComplexValue() {
        var diffs = TestUtils.getDataAdded();
        String expected = "\nProperty 'k' was added with value: [complex value]\n";
        String actual = FORMATTER.format(diffs);
        assertEquals(expected, actual);
    }
}
