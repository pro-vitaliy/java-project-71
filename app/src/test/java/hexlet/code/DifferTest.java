package hexlet.code;

import org.junit.jupiter.api.Test;

import java.net.URL;
import java.nio.file.Paths;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DifferTest {
    @Test
    public void testGenerate() throws Exception {
        URL resourceUrl1 = getClass().getResource("/testfile1.json");
        Objects.requireNonNull(resourceUrl1, "Resource not found: testfile1.json");
        String filePath1 = Paths.get(resourceUrl1.getPath()).toString();

        URL resourceUrl2 = getClass().getResource("/testfile2.json");
        Objects.requireNonNull(resourceUrl2, "Resource not found: testfile2.json");
        String filePath2 = Paths.get(resourceUrl2.getPath()).toString();

        var actual = Differ.generate(filePath1, filePath2);
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
