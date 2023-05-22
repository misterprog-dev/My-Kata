package mars_rover.models;

import mars_rover.exception.RoverInvalidCommandException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static java.util.List.of;
import static mars_rover.models.Command.*;
import static org.junit.jupiter.api.Assertions.*;

public class CommandTest {
    @ParameterizedTest
    @ValueSource(strings = {"FRV", "DG"})
    void should_return_exception_for_invalid_commands_entered(String commands) {
        // when
        Exception exception = assertThrows(RoverInvalidCommandException.class,
                () -> Command.commandsFromCodes(commands));

        // then
        String expectedMessage = "Invalid initial position";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void should_return_commands_from_entered() {
        // When
        List<Command> commands = Command.commandsFromCodes("FBLRF");

        // Given
        assertEquals(commands.size(), 5);
        assertTrue(commands.containsAll(of(FORWARD, BACKWARD, LEFT, RIGHT, FORWARD)));
    }
}
