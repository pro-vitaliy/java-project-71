package hexlet.code;

import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DifferTest {
    @Test
    public void testGenerateJson() throws Exception {
        Path resourceDir = Paths.get("src", "test", "resources");
        String testFilePath1 = resourceDir.resolve("testfile1.json").toAbsolutePath().toString();
        String testFilePath2 = resourceDir.resolve("testfile2.json").toAbsolutePath().toString();

        var actual = Differ.generate(testFilePath1, testFilePath2, "stylish");
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
    public void testGenerateYaml() throws Exception {
        Path resourceDir = Paths.get("src", "test", "resources");
        String testFilePath5 = resourceDir.resolve("testfile5.yml").toString();
        String testFilePath6 = resourceDir.resolve("testfile6.yml").toString();

        var actual = Differ.generate(testFilePath5, testFilePath6, "stylish");
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
}
