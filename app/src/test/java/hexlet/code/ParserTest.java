package hexlet.code;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class ParserTest {
    private static Path testfile1Path;
    private static Path testfile3Path;
    private static Path testfile6Path;

    @BeforeAll
    public static void beforeAll() {
        Path resourceDir = Paths.get("src", "test", "resources");

        testfile1Path = resourceDir.resolve("testfile1.json").toAbsolutePath();
        testfile3Path = resourceDir.resolve("testfile3.yaml").toAbsolutePath();
        testfile6Path = resourceDir.resolve("testfile6.yml").toAbsolutePath();
    }

    @Test
    public void testGetDataJson() throws IOException {
        Map<String, Object> testContent = Parser.getData(testfile1Path.toString());

        var actual = testContent.get("obj1");
        var expected = Map.of("nestedKey", "value", "isNested", true);
        assertEquals(expected, actual);
    }

    @Test
    public void testGetDataYml() throws IOException {
        Map<String, Object> testContent = Parser.getData(testfile6Path.toString());

        var actual = testContent.get("nums2");
        var expected = List.of(22, 33, 44, 55);
        assertEquals(expected, actual);
    }

    @Test
    public void testGetDataYaml() throws IOException {
        Map<String, Object> testContent = Parser.getData(testfile3Path.toString());

        var actual = testContent.get("id");
        assertNull(actual);
    }
}
