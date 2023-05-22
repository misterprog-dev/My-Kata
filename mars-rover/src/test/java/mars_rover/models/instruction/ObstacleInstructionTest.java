package mars_rover.models.instruction;

import mars_rover.exception.InvalidObstaclesException;
import mars_rover.models.Obstacle;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Set;

import static java.util.List.of;
import static org.junit.jupiter.api.Assertions.*;

public class ObstacleInstructionTest {
    @ParameterizedTest
    @ValueSource(strings = {"1", "a b 1", "1 2 3 4 5"})
    void should_return_exception_for_obstacles_input_bad_size(String coordinates) {
        // when
        Exception exception = assertThrows(InvalidObstaclesException.class,
                () -> new ObstacleInstruction(coordinates).getObstaclesFromInput());

        // then
        String expectedMessage = "Invalid obstacles given";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @ParameterizedTest
    @ValueSource(strings = {"1 a", "a b", "1 2 3 4 5 a"})
    void should_return_exception_for_obstacles_invalid_type_entered(String coordinates) {
        // when
        Exception exception = assertThrows(InvalidObstaclesException.class,
                () -> new ObstacleInstruction(coordinates).getObstaclesFromInput());

        // then
        String expectedMessage = "Invalid obstacles given";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void should_return_obstacles_for_valid_input() {
        // when
        Set<Obstacle> result = new ObstacleInstruction("1 2 3 4 5 3").getObstaclesFromInput();

        // then
        assertEquals(result.size(), 3);
        assertTrue(result.containsAll(of(new Obstacle(3, 4), new Obstacle(1, 2))));
    }
}
