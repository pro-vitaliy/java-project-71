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
                "host", "hexlet.io",
                "timeout", "50",
                "proxy", "123.234.53.22",
                "follow", "false"
        );
        assertEquals(expected, actual);
    }

    @Test
    public void testGetDataYaml() throws Exception {
        Path resourceDir = Paths.get("src", "test", "resources");
        Path testFilePath = resourceDir.resolve("testfile6.yml").toAbsolutePath();

        Map<String, String> actual = Parser.getData(testFilePath.toString());
        Map<String, String> expected = Map.of(
                "timeout", "20",
                "verbose", "true",
                "host", "hexlet.io"
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
