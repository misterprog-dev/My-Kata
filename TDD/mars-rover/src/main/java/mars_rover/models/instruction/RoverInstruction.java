package mars_rover.models.instruction;

import mars_rover.exception.RoverInitialPositionException;
import mars_rover.models.*;

import java.util.List;

import static java.lang.Integer.parseInt;
import static java.util.Arrays.stream;
import static mars_rover.models.Constants.COORDINATES_SEPARATOR;
import static org.apache.commons.lang3.StringUtils.isNumeric;

public class RoverInstruction {
    private static final int ROVER_INITIAL_POSITION_SIZE = 3;
    private Planet planet;
    private String initialPosition;
    private List<Command> commands;

    public RoverInstruction(Planet planet, String initialPosition, List<Command> commands) {
        this.planet = planet;
        this.initialPosition = initialPosition;
        this.commands = commands;
    }

    public Rover getRoverFromInput() {
        String[] coordinates = initialPosition.split(COORDINATES_SEPARATOR);
        validateInputForRoverInitialPosition(coordinates);

        Position position = new Position(parseInt(coordinates[0]), parseInt(coordinates[1]));
        Direction direction = Direction.fromCode(coordinates[2]);

        return new Rover(planet, position, direction, commands);
    }

    private void validateInputForRoverInitialPosition(String[] coordinates) {
        if ((stream(coordinates).count() != ROVER_INITIAL_POSITION_SIZE) ||
                (!isNumeric(coordinates[0]) ||
                        !isNumeric(coordinates[1]) ||
                        Direction.fromCode(coordinates[2]) == null)) {
            throw new RoverInitialPositionException("Invalid initial position");
        }
    }
}
