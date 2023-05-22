package mars_rover.models;

import mars_rover.exception.InvalidObstaclesException;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PlanetTest {
    @Test
    void should_return_exception_for_obstacles_invalid() {
        // when
        Exception exception = assertThrows(InvalidObstaclesException.class,
                () -> new Planet(2, 2, Set.of(new Obstacle(1, 2), new Obstacle(1, 6))));

        // then
        String expectedMessage = "Invalid obstacle on planet";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }
}
