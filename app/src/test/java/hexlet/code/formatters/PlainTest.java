package hexlet.code.formatters;

import hexlet.code.Differ;
import hexlet.code.Formatter;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PlainTest {

    @Test
    public void testFormatRemoved() {
        Differ diff = new Differ("k1", "v1", null, "removed");
        List<Differ> diffs = List.of(diff);
        Formatter formatter = new Plain();

        String expected = "{\n    Property 'k1' was removed\n}";
        String actual = formatter.format(diffs);

        assertEquals(expected, actual);
    }

    @Test
    public void testFormatModified() {
        Differ diff = new Differ("k1", "v1", "v2", "modified");
        List<Differ> diffs = List.of(diff);
        Formatter formatter = new Plain();

        String expected = "{\n    Property 'k1' was updated. From v1 to v2\n}";
        String actual = formatter.format(diffs);

        assertEquals(expected, actual);
    }

    @Test
    public void testFormatNotChanged() {
        Differ diff = new Differ("k1", "v1", "v1", "not changed");
        List<Differ> diffs = List.of(diff);
        Formatter formatter = new Plain();

        String expected = """
                {
                   \s
                }""";
        String actual = formatter.format(diffs);

        assertEquals(expected, actual);
    }

    @Test
    public void testFormatModifiedComplexValue() {
        Differ diff = new Differ("k1", "[complex]", "{k: v}", "modified");
        List<Differ> diffs = List.of(diff);
        Formatter formatter = new Plain();

        String expected = "{\n    Property 'k1' was updated. From [complex value] to [complex value]\n}";
        String actual = formatter.format(diffs);

        assertEquals(expected, actual);
    }
}
