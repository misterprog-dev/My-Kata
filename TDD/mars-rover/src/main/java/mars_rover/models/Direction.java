package mars_rover.models;

import java.util.Optional;

import static java.util.Arrays.stream;

public enum Direction {
    NORTH("N"),
    SOUTH("S"),
    EAST("E"),
    WEST("W");
    private final String code;

    Direction(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public Direction getLeft() {
        return switch(this) {
            case NORTH -> WEST;
            case SOUTH -> EAST;
            case WEST -> SOUTH;
            case EAST -> NORTH;
        };
    }

    public Direction getRight() {
        return switch(this) {
            case NORTH -> EAST;
            case SOUTH -> WEST;
            case WEST -> NORTH;
            case EAST -> SOUTH;
        };
    }

    public static Direction fromCode(String code) {
        Optional<Direction> direction = stream(values())
                .filter(value -> value.getCode().equalsIgnoreCase(code))
                .findFirst();
        return direction.orElse(null);
    }
}
