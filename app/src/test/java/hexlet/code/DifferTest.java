package hexlet.code;

import org.junit.jupiter.api.Test;

import java.nio.file.Paths;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DifferTest {
    @Test
    public void testGenerate() throws Exception {
        String filepath1 = String.valueOf(Paths.get(Objects.requireNonNull(getClass().getResource("/testfile1.json")).getPath()));
        String filepath2 = String.valueOf(Paths.get(Objects.requireNonNull(getClass().getResource("/testfile2.json")).getPath()));

        var actual = Differ.generate(filepath1, filepath2);
        String expected = """
                {
                - follow: false
                  host: hexlet.io
                - proxy: 123.234.53.22
                - timeout: 50
                + timeout: 20
                + verbose: true
                }""";
        assertEquals(expected, actual);
    }
}
