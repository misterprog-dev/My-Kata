package mars_rover.models;

import org.junit.jupiter.api.Test;

import static mars_rover.models.Direction.SOUTH;
import static mars_rover.models.Direction.WEST;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PositionTest {
    @Test
    void should_move_forward() {
        // Given
        Position position = new Position(1, 2);

        // When
        Position result = position.moveForward(WEST);

        // Then
        assertEquals(result, new Position(0, 2));
    }

    @Test
    void should_move_backward() {
        // Given
        Position position = new Position(1, 2);

        // When
        Position result = position.moveBackward(SOUTH);

        // Then
        assertEquals(result, new Position(1, 3));
    }
}
