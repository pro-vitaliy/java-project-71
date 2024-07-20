package hexlet.code;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

import org.apache.commons.io.FilenameUtils;

public class Differ {
    public static String generate(String filePath1, String filePath2, String formatName) throws Exception {
        Path path1 = Paths.get(filePath1).toAbsolutePath().normalize();
        Path path2 = Paths.get(filePath2).toAbsolutePath().normalize();

        if (!Files.exists(path1)) {
            throw new Exception("File " + filePath1 + " not found");
        }
        if (!Files.exists(path2)) {
            throw new Exception("File " + filePath2 + " not found");
        }
        String fileAsString1 = Files.readString(path1);
        String fileAsString2 = Files.readString(path2);

        String fileExtension1 = FilenameUtils.getExtension(filePath1);
        String fileExtension2 = FilenameUtils.getExtension(filePath2);

        Map<String, Object> fileData1 = Parser.getData(fileAsString1, fileExtension1);
        Map<String, Object> fileData2 = Parser.getData(fileAsString2, fileExtension2);

        var keys = new TreeSet<String>();
        keys.addAll(fileData1.keySet());
        keys.addAll(fileData2.keySet());

        List<Map<String, Object>> diffs = Comparator.getDiffs(keys, fileData1, fileData2);
        return FormatterFacade.formatData(diffs, formatName);
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
