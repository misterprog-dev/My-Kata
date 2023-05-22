package mars_rover.models;

public record Obstacle(int x, int y) {
    public boolean hasSameCoordinateWith(Position position) {
        return x() == position.x() && y() == position.y();
    }

    @Override
    public String toString() {
        return "O:" + x() + ":" + y();
    }
}
