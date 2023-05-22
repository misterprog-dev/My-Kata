package mars_rover.models;

import org.junit.jupiter.api.Test;

import static mars_rover.models.Direction.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DirectionTest {
    @Test
    void should_turn_left() {
        // Given
        Direction direction = SOUTH;

        // When
        Direction result = direction.getLeft();

        // Then
        assertEquals(result, EAST);
    }

    @Test
    void should_turn_right_rover() {
        // Given
        Direction direction = WEST;

        // When
        Direction result = direction.getRight();

        // Then
        assertEquals(result, NORTH);
    }
}
