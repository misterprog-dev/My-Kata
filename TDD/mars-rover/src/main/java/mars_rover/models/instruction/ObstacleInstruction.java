package mars_rover.models.instruction;

import mars_rover.exception.InvalidObstaclesException;
import mars_rover.models.Obstacle;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import static java.lang.Integer.parseInt;
import static java.util.Arrays.stream;
import static mars_rover.models.Constants.COORDINATES_SEPARATOR;
import static mars_rover.models.Constants.COORDINATES_SIZE;
import static org.apache.commons.lang3.StringUtils.isNumeric;

public class ObstacleInstruction {
    private String obstacleCoordinates;

    public ObstacleInstruction(String obstacleCoordinates) {
        this.obstacleCoordinates = obstacleCoordinates;
    }

    public Set<Obstacle> getObstaclesFromInput() {
        String[] coordinates = obstacleCoordinates.split(COORDINATES_SEPARATOR);
        if (isNotValidObstaclesEntered(coordinates)) {
            throw new InvalidObstaclesException("Invalid obstacles given");
        }

        Iterator<String> coordiantesIterator = stream(coordinates).iterator();
        Set<Obstacle> obstacles = new HashSet<>();

        while (coordiantesIterator.hasNext()) {
            int x = parseInt(coordiantesIterator.next());
            int y = parseInt(coordiantesIterator.next());
            obstacles.add(new Obstacle(x, y));
        }

        return obstacles;
    }

    private boolean isNotValidObstaclesEntered(String[] coordinates) {
        return stream(coordinates).count() % COORDINATES_SIZE != 0 ||
                stream(coordinates).anyMatch(coordinate -> !isNumeric(coordinate));
    }
}
