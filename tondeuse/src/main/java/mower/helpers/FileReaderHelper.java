package mower.helpers;

import mower.exception.FileFormatInvalidException;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileReaderHelper {
    public static final int MIN_NUMBER_LINE_FOR_GARDEN_AND_MOWER = 3; // % 2 : each mower have 2 lines that concern it + first line to garden size.
    public static final int NUMBER_LINE_OF_MOWER = 2;

    public static List<String> readFile(String filePath) throws IOException, FileFormatInvalidException {
        List<String> result = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            reader.lines().forEach(result::add);
            reader.close();
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException("File not found");
        }

        ValidateFileContent(result);

        return result;
    }

    static void ValidateFileContent(List<String> fileLines) throws FileNotFoundException, FileFormatInvalidException {
        if (fileLines == null) {
            throw new FileNotFoundException("File not exists");
        }
        if (fileLines.size() < MIN_NUMBER_LINE_FOR_GARDEN_AND_MOWER || fileLines.size() % NUMBER_LINE_OF_MOWER == 0) {
            throw new FileFormatInvalidException("File format invalid");
        }
    }
}
