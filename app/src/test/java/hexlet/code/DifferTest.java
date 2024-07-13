package hexlet.code;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DifferTest {
    private static String testFile1Path;
    private static String testFile2Path;
    private static String testFile3Path;
    private static String testFile4Path;
    private static String testFile5Path;
    private static String testFile6Path;

    @BeforeAll
    public static void beforeAll() {
        Path resourceDir = Paths.get("src", "test", "resources");

        testFile1Path = resourceDir.resolve("testfile1.json").toAbsolutePath().toString();
        testFile2Path = resourceDir.resolve("testfile2.json").toAbsolutePath().toString();
        testFile3Path = resourceDir.resolve("testfile3.yaml").toAbsolutePath().toString();
        testFile4Path = resourceDir.resolve("testfile4.yaml").toAbsolutePath().toString();
        testFile5Path = resourceDir.resolve("testfile5.yml").toString();
        testFile6Path = resourceDir.resolve("testfile6.yml").toString();
    }

    @Test
    public void testGenerateStylish() throws Exception {
        var actual = Differ.generate(testFile1Path, testFile2Path, "stylish");
        String expected = """
                {
                  + arr: []
                  + chars1: [a, b, c]
                  + chars2: false
                  - default:\s
                  + default: null
                    nums1: [1, 2, 3, 4]
                    nums2: [22, 33, 44, 55]
                  - obj1: {nestedKey=value, isNested=true}
                }""";
        assertEquals(expected, actual);
    }

    @Test
    public void testGeneratePlain() throws Exception {
        var actual = Differ.generate(testFile5Path, testFile6Path, "plain");
        String expected = """
                \nProperty 'arr' was added with value: [complex value]
                Property 'chars1' was added with value: [complex value]
                Property 'chars2' was added with value: false
                Property 'default' was updated. From '' to null
                Property 'obj1' was removed
                """;
        assertEquals(expected, actual);
    }

    @Test
    public void testGenerateJson() throws Exception {
        var actual = Differ.generate(testFile3Path, testFile4Path, "json");
        var expected = "{\"value\":[\"value1\",\"value2\"],\"key\":\"default\",\"info\":\"DELETED\"}, "
                + "{\"newValue\":45,\"oldValue\":null,\"key\":\"id\",\"info\":\"CHANGED\"}, "
                + "{\"value\":[1,2],\"key\":\"numbers\",\"info\":\"UNCHANGED\"}, "
                + "{\"value\":{\"k1\":\"v1\",\"k2\":true},\"key\":\"obj\",\"info\":\"ADDED\"}";
        assertEquals(expected, actual);
    }
}
