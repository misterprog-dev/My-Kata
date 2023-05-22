package mars_rover.models;

public record Position(int x, int y) {

    Position moveForward(Direction direction) {
        return switch (direction) {
            case NORTH -> new Position(x, y + 1);
            case SOUTH -> new Position(x, y - 1);
            case EAST -> new Position(x + 1, y);
            case WEST -> new Position(x - 1, y);
        };
    }

    public Position moveBackward(Direction direction) {
        return switch (direction) {
            case NORTH -> new Position(x, y - 1);
            case SOUTH -> new Position(x, y + 1);
            case EAST -> new Position(x - 1, y);
            case WEST -> new Position(x + 1, y);
        };
    }

    public boolean isOnPlanet(Planet planet) {
        return y <= planet.getDimY() && y >= 0 && x <= planet.getDimX() && x >= 0;
    }

    @Override
    public String toString() {
        return x + ":" + y;
    }
}
