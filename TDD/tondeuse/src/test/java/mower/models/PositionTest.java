package mower.models;

import mower.exception.FileFormatInvalidException;
import mower.exception.MowerInitialPositionException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class PositionTest {
    @Test
    void should_return_exception_for_invalid_mower_inital_position() {
        // when
        Exception exception = assertThrows(MowerInitialPositionException.class, () -> new Position("1 2"));

        // then
        String expectedMessage = "Invalid position for Mower";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void should_return_position_for_mower() throws MowerInitialPositionException, FileFormatInvalidException {
        // When
        Position result = new Position("3 2 E");

        // Then
        assertEquals(result, new Position(3, 2, Direction.EAST));
    }

    @Test
    void should_except_unknown_position() {
        // when
        Exception exception = assertThrows(FileFormatInvalidException.class, () -> new Position("1 2 Z"));

        // then
        String expectedMessage = "Invalid direction for Initial position";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }
}
