package hexlet.code;

import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.assertj.core.api.Assertions.catchThrowable;

public class ParserTest {
    @Test
    public void testGetDataJson() throws Exception {
        Path resourceDir = Paths.get("src", "test", "resources");
        Path testFilePath = resourceDir.resolve("testfile1.json").toAbsolutePath();

        Map<String, String> actual = Parser.getData(testFilePath.toString());
        Map<String, String> expected = Map.of(
                "nums1", "[1, 2, 3, 4]",
                "obj1", "{nestedKey=value, isNested=true}",
                "nums2", "[22, 33, 44, 55]",
                "default", ""
        );
        assertEquals(expected, actual);
    }

    @Test
    public void testGetDataYaml() throws Exception {
        Path resourceDir = Paths.get("src", "test", "resources");
        Path testFilePath = resourceDir.resolve("testfile6.yml").toAbsolutePath();

        Map<String, String> actual = Parser.getData(testFilePath.toString());
        Map<String, String> expected = Map.of(
                "nums2", "[22, 33, 44, 55]",
                "chars1", "[a, b, c]",
                "nums1", "[1, 2, 3, 4]",
                "chars2", "false",
                "default", "null",
                "arr", "[]"
        );
        assertEquals(expected, actual);
    }

    @Test
    public void testGetDataNonExistent() {
        Path resourceDir = Paths.get("src", "test", "resources");
        Path testFilePath = resourceDir.resolve("nonExistentFile.json").toAbsolutePath();

        var thrown = catchThrowable(
                () -> Parser.getData(testFilePath.toString())
        );
        assertThat(thrown).isInstanceOf(Exception.class);
    }
}
