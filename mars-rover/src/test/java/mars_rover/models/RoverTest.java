package mars_rover.models;

import mars_rover.exception.RoverEmptyCommandException;
import mars_rover.exception.RoverInValidPlanetException;
import mars_rover.exception.RoverInitialPositionException;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;

import static java.util.List.of;
import static mars_rover.models.Command.*;
import static mars_rover.models.Direction.*;
import static org.junit.jupiter.api.Assertions.*;

public class RoverTest {

    @Test
    void should_return_exception_for_rover_invalid_planet() {
        // when
        Exception exception = assertThrows(RoverInValidPlanetException.class, () -> new Rover(null, new Position(1, 2), WEST, of(RIGHT)));

        // then
        String expectedMessage = "Invalid planet for Rover";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void should_return_exception_for_rover_invalid_position() {
        // when
        Exception exception = assertThrows(RoverInitialPositionException.class,
                () -> new Rover(
                        new Planet(2, 2),
                        null,
                        null,
                        of(RIGHT)));

        // then
        String expectedMessage = "Invalid initial position for Rover";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void should_return_exception_for_rover_invalid_direction() {
        // when
        Exception exception = assertThrows(RoverInitialPositionException.class,
                () -> new Rover(
                        new Planet(2, 2),
                        new Position(1, 2),
                        null,
                        of(LEFT)));

        // then
        String expectedMessage = "Invalid initial position for Rover";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void should_return_exception_for_rover_no_commands() {
        // when
        Exception exception = assertThrows(RoverEmptyCommandException.class,
                () -> new Rover(
                        new Planet(2, 2),
                        new Position(1, 2),
                        WEST,
                        of()));

        // then
        String expectedMessage = "Empty command for rover";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void should_move_rover_forward() {
        // Given
        Rover rover = new Rover(
                new Planet(2, 2),
                new Position(1, 2),
                WEST,
                of(FORWARD));

        // When
        rover.processCommand();

        // Then
        assertEquals(rover.getPosition(), new Position(0, 2));
        assertEquals(rover.getDirection(), WEST);
    }

    @Test
    void should_move_rover_backward() {
        // Given
        Rover rover = new Rover(
                new Planet(4, 4),
                new Position(1, 2),
                SOUTH,
                of(BACKWARD));

        // When
        rover.processCommand();

        // Then
        assertEquals(rover.getPosition(), new Position(1, 3));
        assertEquals(rover.getDirection(), SOUTH);
    }

    @Test
    void should_turn_left_rover() {
        // Given
        Rover rover = new Rover(
                new Planet(4, 4),
                new Position(1, 2),
                SOUTH,
                of(LEFT)
        );

        // When
        rover.processCommand();

        // Then
        assertEquals(rover.getPosition(), new Position(1, 2));
        assertEquals(rover.getDirection(), EAST);
    }

    @Test
    void should_turn_right_rover() {
        // Given
        Rover rover = new Rover(
                new Planet(4, 4),
                new Position(1, 2),
                WEST,
                of(RIGHT));

        // When
        rover.processCommand();

        // Then
        assertEquals(rover.getPosition(), new Position(1, 2));
        assertEquals(rover.getDirection(), NORTH);
    }

    @Test
    void should_process_rover_commands() {
        // Given
        Rover rover = new Rover(
                new Planet(4, 4),
                new Position(1, 2),
                WEST,
                of(RIGHT, RIGHT, FORWARD, FORWARD, RIGHT));

        // When
        rover.processCommand();

        // Then
        assertEquals(rover.getPosition(), new Position(3, 2));
        assertEquals(rover.getDirection(), SOUTH);
    }

    @Test
    void should_return_rover_position_with_foreward_pass_planet_limit() {
        // Given
        Rover rover = new Rover(new Planet(3, 3), new Position(1, 2), WEST, of(FORWARD, FORWARD, FORWARD, FORWARD, FORWARD));

        // When
        rover.processCommand();

        // Then
        assertEquals(rover.getPosition(), new Position(0, 2));
        assertEquals(rover.getDirection(), WEST);
    }

    @Test
    void should_return_rover_position_with_backward_pass_planet_limit() {
        // Given
        Rover rover = new Rover(new Planet(3, 3), new Position(1, 2), WEST, of(BACKWARD, BACKWARD, BACKWARD, BACKWARD, BACKWARD));

        // When
        rover.processCommand();

        // Then
        assertEquals(rover.getPosition(), new Position(3, 2));
        assertEquals(rover.getDirection(), WEST);
    }

    @Test
    void should_rover_report_obstacles_on_planet() {
        // Given
        Set<Obstacle> obstacles = Set.of(new Obstacle(1, 3), new Obstacle(0, 2));
        Planet planet = new Planet(3, 3, obstacles);
        Position position = new Position(1, 2);
        List<Command> commands = of(FORWARD, BACKWARD, BACKWARD);
        Rover rover = new Rover(planet, position, WEST, commands);

        // When
        rover.processCommand();

        // Then
        assertTrue(rover.getObstaclesMet().contains(new Obstacle(0, 2)));
    }

    @Test
    void should_rover_avoid_obstacles_on_planet() {
        // Given
        Set<Obstacle> obstacles = Set.of(new Obstacle(1, 3), new Obstacle(0, 2));
        Planet planet = new Planet(3, 3, obstacles);
        Position position = new Position(1, 2);
        List<Command> commands = of(FORWARD, BACKWARD, LEFT, BACKWARD, BACKWARD);
        Rover rover = new Rover(planet, position, WEST, commands);

        // When
        rover.processCommand();

        // Then
        assertTrue(rover.getObstaclesMet().contains(new Obstacle(0, 2)));
        assertEquals(rover.getPosition(), new Position(2, 3));
        assertEquals(rover.getDirection(), SOUTH);
    }

    @Test
    void should_display_rover_without_obstacle() {
        // Given
        Set<Obstacle> obstacles = Set.of();
        Planet planet = new Planet(3, 3, obstacles);
        Position position = new Position(1, 2);
        List<Command> commands = of(FORWARD);
        Rover rover = new Rover(planet, position, WEST, commands);

        // When
        rover.processCommand();
        String result = rover.display();

        // Then
        assertEquals(result, "0:2:W");
    }

    @Test
    void should_display_rover_with_obstacle() {
        // Given
        Set<Obstacle> obstacles = Set.of(new Obstacle(0, 2), new Obstacle(1, 0));
        Planet planet = new Planet(3, 3, obstacles);
        Position position = new Position(1, 2);
        List<Command> commands = of(FORWARD, LEFT, FORWARD, FORWARD);
        Rover rover = new Rover(planet, position, WEST, commands);

        // When
        rover.processCommand();
        String result = rover.display();

        // Then
        assertEquals(result, "1:1:S O:0:2 O:1:0");
    }
}
