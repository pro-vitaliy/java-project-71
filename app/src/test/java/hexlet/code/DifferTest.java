package hexlet.code;

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
        Path resourceDir = Paths.get("src", "test", "resources");

        testFile1Path = resourceDir.resolve("testfile1.json").toAbsolutePath().toString();
        testFile2Path = resourceDir.resolve("testfile2.json").toString();
        testFile3Path = resourceDir.resolve("testfile3.yaml").toAbsolutePath().toString();
        testFile4Path = resourceDir.resolve("testfile4.yaml").toString();

        Path expectedResultStylishPath = resourceDir.resolve("expectedResultStylish.txt");
        expectedResultStylish = Files.readString(expectedResultStylishPath);

        Path expectedResultPlainPath = resourceDir.resolve("expectedResultPlain.txt");
        expectedResultPlain = Files.readString(expectedResultPlainPath);

        Path expectedResultJsonPath = resourceDir.resolve("expectedResultJson.txt");
        expectedResultJson = Files.readString(expectedResultJsonPath);
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
        assertEquals(expectedResultJson, actual);
    }

    @Test
    public void generateTestYamlToJson() throws Exception {
        var actual = Differ.generate(testFile3Path, testFile4Path, "json");
        assertEquals(expectedResultJson, actual);
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
