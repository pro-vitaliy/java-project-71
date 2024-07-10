package hexlet.code;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class ParserTest {
    @Test
    public void testGetDataJson() throws IOException {
        Path resourceDir = Paths.get("src", "test", "resources");
        Path testFilePath = resourceDir.resolve("testfile1.json").toAbsolutePath();
        Map<String, Object> testContent = Parser.getData(testFilePath.toString());

        var actual = testContent.get("obj1");
        var expected = Map.of("nestedKey", "value", "isNested", true);
        assertEquals(expected, actual);
    }

    @Test
    public void testGetDataYml() throws IOException {
        Path resourceDir = Paths.get("src", "test", "resources");
        Path testFilePath = resourceDir.resolve("testfile6.yml").toAbsolutePath();
        Map<String, Object> testContent = Parser.getData(testFilePath.toString());

        var actual = testContent.get("nums2");
        var expected = List.of(22, 33, 44, 55);
        assertEquals(expected, actual);
    }

    @Test
    public void testGetDataYaml() throws IOException {
        Path resourceDir = Paths.get("src", "test", "resources");
        Path testFilePath = resourceDir.resolve("testfile3.yaml").toAbsolutePath();
        Map<String, Object> testContent = Parser.getData(testFilePath.toString());

        var actual = testContent.get("id");
        assertNull(actual);
    }
}
