package hexlet.code;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DifferTest {
    private static String testFile1Path;
    private static String testFile2Path;
    private static String testFile3Path;
    private static String testFile4Path;
    private static String expectedResultStylish;
    private static String expectedResultPlain;
    private static String expectedResultJson;

    @BeforeAll
    public static void beforeAll() throws Exception {
        testFile1Path = getFilePath("testfile1.json").toString();
        testFile2Path = getFilePath("testfile2.json").toString();
        testFile3Path = getFilePath("testfile3.yaml").toString();
        testFile4Path = getFilePath("testfile4.yaml").toString();

        expectedResultStylish = Files.readString(getFilePath("expectedResultStylish.txt"));
        expectedResultPlain = Files.readString(getFilePath("expectedResultPlain.txt"));
        expectedResultJson = Files.readString(getFilePath("expectedResultJson.txt"));
    }

    private static Path getFilePath(String fileName) {
        Path resourceDir = Paths.get("src", "test", "resources");
        return resourceDir.resolve(fileName).toAbsolutePath();
    }

    @Test
    public void generateTestJsonToStylish() throws Exception {
        var actual = Differ.generate(testFile1Path, testFile2Path, "stylish");
        assertEquals(expectedResultStylish, actual);
    }

    @Test
    public void generateTestYamlToStylish() throws Exception {
        var actual = Differ.generate(testFile3Path, testFile4Path, "stylish");
        assertEquals(expectedResultStylish, actual);
    }

    @Test
    public void generateTestJsonToPlain() throws Exception {
        var actual = Differ.generate(testFile1Path, testFile2Path, "plain");
        assertEquals(expectedResultPlain, actual);
    }

    @Test
    public void generateTestYamlToPlain() throws Exception {
        var actual = Differ.generate(testFile3Path, testFile4Path, "plain");
        assertEquals(expectedResultPlain, actual);
    }

    @Test
    public void generateTestJsonToJson() throws Exception {
        var actual = Differ.generate(testFile1Path, testFile2Path, "json");

        ObjectMapper mapper = new ObjectMapper();
        assertEquals(mapper.readTree(expectedResultJson), mapper.readTree(actual));
    }

    @Test
    public void generateTestYamlToJson() throws Exception {
        var actual = Differ.generate(testFile3Path, testFile4Path, "json");

        ObjectMapper mapper = new ObjectMapper();
        assertEquals(mapper.readTree(expectedResultJson), mapper.readTree(actual));
    }

    @Test
    public void generateTestJsonWithTwoArgs() throws Exception {
        var actual = Differ.generate(testFile1Path, testFile2Path);
        assertEquals(expectedResultStylish, actual);
    }

    @Test
    public void generateYamlWithTwoArgs() throws Exception {
        var actual = Differ.generate(testFile3Path, testFile4Path);
        assertEquals(expectedResultStylish, actual);
    }
}
