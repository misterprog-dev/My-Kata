package mower.models;

import mower.exception.InvalidGardenSizeException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GardenTest {
    @Test
    void should_return_exception_for_invalid_garden() {
        // Given
        String[] line = {"5"};

        // when
        Exception exception = assertThrows(InvalidGardenSizeException.class, () -> new Garden(line));

        // then
        String expectedMessage = "Garden size is invalid";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void should_return_exception_for_no_numeric_garden_size() {
        // Given
        String[] line = {"A 5"};

        // when
        Exception exception = assertThrows(InvalidGardenSizeException.class, () -> new Garden(line));

        // then
        String expectedMessage = "Garden size is invalid";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void should_return_limit_garden_for_correct_garden() throws InvalidGardenSizeException {
        // Given
        String[] fileLines = {"5","3"};

        // When
        Garden garden = new Garden(fileLines);

        // Then
        assertEquals(garden.getX(), 5);
        assertEquals(garden.getY(), 3);
    }
}


