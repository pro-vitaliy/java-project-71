package hexlet.code;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class ComparatorTest {
    private Map<String, Object> data1;
    private Map<String, Object> data2;

    @BeforeEach
    public void beforeEach() {
        data1 = new HashMap<>();
        data2 = new HashMap<>();
    }

    @Test
    public void testAdded() {
        data2.put("nums", List.of(1, 2, 3));
        Map<String, Object> actual = Comparator.compareValues("nums", data1, data2);

        assertEquals("nums", actual.get("key"));
        assertEquals("ADDED", actual.get("info"));
        assertEquals(List.of(1, 2, 3), actual.get("value"));
    }

    @Test
    public void testDeleted() {
        data1.put("default", null);
        Map<String, Object> actual = Comparator.compareValues("default", data1, data2);

        assertEquals("default", actual.get("key"));
        assertEquals("DELETED", actual.get("info"));
        assertNull(actual.get("value"));
    }

    @Test
    public void testUnchanged() {
        data1.put("checked", false);
        data2.put("checked", false);
        Map<String, Object> actual = Comparator.compareValues("checked", data1, data2);

        assertEquals("checked", actual.get("key"));
        assertEquals("UNCHANGED", actual.get("info"));
        assertEquals(false, actual.get("value"));
    }

    @Test
    public void testChanged() {
        data1.put("settings", "some value");
        data2.put("settings", Map.of("key", 1));
        Map<String, Object> actual = Comparator.compareValues("settings", data1, data2);

        assertEquals("settings", actual.get("key"));
        assertEquals("CHANGED", actual.get("info"));
        assertEquals("some value", actual.get("oldValue"));
        assertEquals(Map.of("key", 1), actual.get("newValue"));
    }
}
