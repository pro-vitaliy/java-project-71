package hexlet.code.formatters;

import hexlet.code.Differ;
import hexlet.code.Formatter;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

public class StylishTest {

    @Test
    public void testFormatAdded() {
        Differ diff = new Differ("k1", null, "v2", "added");
        List<Differ> diffs = List.of(diff);
        Formatter formatter = new Stylish();

        String expected = "{\n  + k1: v2\n}";
        String actual = formatter.format(diffs);

        assertEquals(expected, actual);
    }

    @Test
    public void testFormatRemoved() {
        Differ diff = new Differ("k1", "v1", null, "removed");
        List<Differ> diffs = List.of(diff);
        Formatter formatter = new Stylish();

        String expected = "{\n  - k1: v1\n}";
        String actual = formatter.format(diffs);

        assertEquals(expected, actual);
    }

    @Test
    public void testFormatModified() {
        Differ diff = new Differ("k1", "v1", "v2", "modified");
        List<Differ> diffs = List.of(diff);
        Formatter formatter = new Stylish();

        String expected = "{\n  - k1: v1\n  + k1: v2\n}";
        String actual = formatter.format(diffs);

        assertEquals(expected, actual);
    }

    @Test
    public void testFormatNotChanged() {
        Differ diff = new Differ("k", "v", "v", "not changed");
        List<Differ> diffs = List.of(diff);
        Formatter formatter = new Stylish();

        String expected = "{\n    k: v\n}";
        String actual = formatter.format(diffs);

        assertEquals(expected, actual);
    }
}
