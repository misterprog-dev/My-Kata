package mars_rover.models.instruction;

import mars_rover.exception.RoverInitialPositionException;
import mars_rover.models.Command;
import mars_rover.models.Planet;
import mars_rover.models.Position;
import mars_rover.models.Rover;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static mars_rover.models.Command.FORWARD;
import static mars_rover.models.Command.LEFT;
import static mars_rover.models.Direction.NORTH;
import static org.junit.jupiter.api.Assertions.*;

public class RoverInstructionTest {
    @ParameterizedTest
    @ValueSource(strings = {"1", "a b"})
    void should_return_exception_for_initial_position_with_bad_size(String initalPosition) {
        // Given
        Planet planet = new Planet(5, 5);
        List<Command> commands = List.of(FORWARD, LEFT);

        // when
        Exception exception = assertThrows(RoverInitialPositionException.class,
                () -> new RoverInstruction(planet, initalPosition, commands).getRoverFromInput());

        // then
        String expectedMessage = "Invalid initial position";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @ParameterizedTest
    @ValueSource(strings = {"1 a G", "a b 1", "1 1 1"})
    void should_return_exception_for_initial_position_with_bad_entered(String initalPosition) {
        // Given
        Planet planet = new Planet(5, 5);
        List<Command> commands = List.of(FORWARD, LEFT);

        // when
        Exception exception = assertThrows(RoverInitialPositionException.class,
                () -> new RoverInstruction(planet, initalPosition, commands).getRoverFromInput());

        // then
        String expectedMessage = "Invalid initial position";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void should_return_rover_for_valid_input() {
        // Given
        Planet planet = new Planet(5, 5);
        List<Command> commands = List.of(FORWARD, LEFT, FORWARD, LEFT);

        // when
        Rover result = new RoverInstruction(planet, "1 2 N", commands).getRoverFromInput();

        // then
        assertEquals(result.getDirection(), NORTH);
        Assertions.assertEquals(result.getPosition(), new Position(1, 2));
    }
}
