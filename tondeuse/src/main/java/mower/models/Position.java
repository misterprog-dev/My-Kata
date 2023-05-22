package mower.models;

import mower.exception.FileFormatInvalidException;
import mower.exception.MowerInitialPositionException;

import static java.lang.Integer.parseInt;
import static java.util.Arrays.stream;
import static org.apache.commons.lang3.StringUtils.isNumeric;

public class Position {
    public static final String MOWER_INITIAL_POSITION_SEPARATOR = " ";
    public static final int MOWER_NUMBER_OF_CHARACTER_FOR_INITIAL = 3;
    public static final int MOWER_COORDINATE_X_POSITION = 0;
    public static final int MOWER_COORDINATE_Y_POSITION = 1;
    public static final int MOWER_COORDINATE_DIRECTION_POSITION = 2;

    private int x;
    private int y;
    private Direction direction;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Position(int x, int y, Direction direction) {
        this(x, y);
        this.direction = direction;
    }

    public Position(String position) throws MowerInitialPositionException, FileFormatInvalidException {
        String[] splitOfMowerPosition = position.split(MOWER_INITIAL_POSITION_SEPARATOR);

        if (isNotInitialPositionValidForMower(splitOfMowerPosition)) {
            throw new MowerInitialPositionException("Invalid position for Mower");
        }

        if (isNotValidDirectionForMowerInitialPosition(splitOfMowerPosition)) {
            throw new FileFormatInvalidException("Invalid direction for Initial position");
        }

        this.x = parseInt(splitOfMowerPosition[MOWER_COORDINATE_X_POSITION]);
        this.y = parseInt(splitOfMowerPosition[MOWER_COORDINATE_Y_POSITION]);
        this.direction = Direction.fromCode(splitOfMowerPosition[MOWER_COORDINATE_DIRECTION_POSITION]);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Direction getDirection() {
        return direction;
    }

    public void turnRight() {
        direction = direction.getRight();
    }

    public void turnLeft() {
        direction = direction.getLeft();
    }

    public void goAhead(Garden garden) {
        switch(direction) {
            case NORTH:
                if (garden.getX() > y) y += 1;
                break;
            case EAST:
                if (garden.getX() > x) x += 1;
                break;
            case WEST:
                if (garden.getX() > 0) x -= 1;
                break;
            case SOUTH:
                if (garden.getY() > 0) y -= 1;
                break;
        }
    }

    public String getFinalPosition() {
        return x + " " + y + " " + direction.getCode();
    }

    private boolean isNotInitialPositionValidForMower(String[] line) {
        return stream(line).count() < MOWER_NUMBER_OF_CHARACTER_FOR_INITIAL ||
                !isNumeric(line[MOWER_COORDINATE_X_POSITION]) ||
                !isNumeric(line[MOWER_COORDINATE_Y_POSITION]);
    }

    private boolean isNotValidDirectionForMowerInitialPosition(String[] line) {
        return stream(line).count() >= MOWER_NUMBER_OF_CHARACTER_FOR_INITIAL &&
                Direction.fromCode(line[MOWER_COORDINATE_DIRECTION_POSITION]) == null;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (obj.getClass() != this.getClass()) {
            return false;
        }

        final Position other = (Position) obj;

        return other.getY() == y && other.getX() == x && other.getDirection() == direction;
    }
}
