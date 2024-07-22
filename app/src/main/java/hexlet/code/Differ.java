package hexlet.code;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FilenameUtils;

public class Differ {
    public static String generate(String filePath1, String filePath2, String formatName) throws Exception {
        String fileAsString1 = Files.readString(getPath(filePath1));
        String fileAsString2 = Files.readString(getPath(filePath2));

        String fileExtension1 = FilenameUtils.getExtension(filePath1);
        String fileExtension2 = FilenameUtils.getExtension(filePath2);

        Map<String, Object> fileData1 = Parser.getData(fileAsString1, fileExtension1);
        Map<String, Object> fileData2 = Parser.getData(fileAsString2, fileExtension2);

        List<Map<String, Object>> diffs = Comparator.getDiffs(fileData1, fileData2);
        return FormatterFacade.formatData(diffs, formatName);
    }

    private static Path getPath(String filePath) {
        return Paths.get(filePath).toAbsolutePath().normalize();
    }

    /**
     * This method is intended to be used as part of a library.
     *
     * @param filePath1 the string path to the first file
     * @param filePath2 the string path to the second file
     * @return a formatted string representing the differences between the two files
    */
    public static String generate(String filePath1, String filePath2) throws Exception {
        return generate(filePath1, filePath2, "stylish");
    }
}
