package mower.helpers;

import mower.exception.FileFormatInvalidException;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import static java.util.Arrays.asList;
import static mower.Constant.baseDirForTestData;
import static mower.helpers.FileReaderHelper.readFile;
import static org.junit.jupiter.api.Assertions.*;

public class FileReaderHelperTest {
    @Test
    void should_return_exception_when_file_not_found() {
        // when
        Exception exception = assertThrows(FileNotFoundException.class, () -> readFile("///"));

        // then
        String expectedMessage = "File not found";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void should_return_file_content_as_string() throws IOException, FileFormatInvalidException {
        // When
        List<String> result = readFile(baseDirForTestData + "DisplayFinalResult.txt");

        // Then
        assertEquals(result, asList("5 5", "1 2 N", "GAGAGAGAA", "3 3 E", "AADAADADDA"));
    }

    @Test
    void should_return_exception_when_file_format_is_not_correct() {
        // when
        Exception exception = assertThrows(FileFormatInvalidException.class, () -> FileReaderHelper.readFile(baseDirForTestData + "IncorrectFormat.txt"));

        // then
        String expectedMessage = "File format invalid";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }
}
