package mars_rover.models;

import mars_rover.exception.RoverEmptyCommandException;
import mars_rover.exception.RoverInValidPlanetException;
import mars_rover.exception.RoverInitialPositionException;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public class Rover {
    public static final String DISPLAY_SEPARATOR = " ";
    public static final String POSTION_SEPARATOR = ":";
    private Planet planet;
    private Position position;
    private Direction direction;
    private List<Command> commands;
    private Set<Obstacle> obstaclesMet = new HashSet<>();

    public Rover(Planet planet, Position position, Direction direction, List<Command> commands) {
        this.planet = planet;
        this.position = position;
        this.direction = direction;
        this.commands = commands;
        validateRoverData();
    }

    private void validateRoverData() {
        if (planet == null) {
            throw new RoverInValidPlanetException("Invalid planet for Rover");
        }

        if (position == null || direction == null) {
            throw new RoverInitialPositionException("Invalid initial position for Rover");
        }

        if (commands == null || commands.isEmpty()) {
            throw new RoverEmptyCommandException("Empty command for rover");
        }
    }

    private void moveForward() {
        Position newPosition = position.moveForward(direction);
        updateCurrentPosition(newPosition);
    }

    private void moveBackward() {
        Position newPosition = position.moveBackward(direction);
        updateCurrentPosition(newPosition);
    }

    private void updateCurrentPosition(Position newPosition) {
        Optional<Obstacle> obstacle = planet.getObstacleFor(newPosition);
        obstacle.ifPresent(value -> obstaclesMet.add(value));

        if (newPosition.isOnPlanet(planet) && obstacle.isEmpty()) {
            position = newPosition;
        }
    }

    private void turnLeft() {
        direction = direction.getLeft();
    }

    private void turnRight() {
        direction = direction.getRight();
    }

    public void processCommand() {
        for (Command command : commands) {
            move(command);
        }
    }

    private void move(Command command) {
        switch (command) {
            case LEFT -> turnLeft();
            case RIGHT -> turnRight();
            case FORWARD -> moveForward();
            case BACKWARD -> moveBackward();
        };
    }

    public String display() {
        List<String> obstaclesDisplay = getObstaclesMet().stream()
                .map(Obstacle::toString)
                .toList();
        StringBuilder roverPositionDisplay = new StringBuilder().append(position.toString())
                .append(POSTION_SEPARATOR)
                .append(direction.getCode())
                .append(DISPLAY_SEPARATOR)
                .append(String.join(DISPLAY_SEPARATOR, obstaclesDisplay));

        return roverPositionDisplay.toString().trim();
    }

    public Position getPosition() {
        return position;
    }

    public Direction getDirection() {
        return direction;
    }

    public Set<Obstacle> getObstaclesMet() {
        return obstaclesMet;
    }
}
