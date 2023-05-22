package mars_rover.models;

import mars_rover.exception.InvalidObstaclesException;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static java.util.Optional.empty;
import static java.util.Optional.of;

public class Planet {
    int dimX;
    int dimY;
    Set<Obstacle> obstacles = new HashSet<>();

    public Planet(int dimX, int dimY) {
        this.dimX = dimX;
        this.dimY = dimY;
    }

    public Planet(int dimX, int dimY, Set<Obstacle> obstacles) {
        this(dimX, dimY);
        this.obstacles = obstacles;
        validateObstacles();
    }

    private void validateObstacles() {
        for (Obstacle obstacle : obstacles) {
            if ((obstacle.x() > dimX || obstacle.x() < 0) ||
                    (obstacle.y() > dimY || obstacle.y() < 0)) {
                throw new InvalidObstaclesException("Invalid obstacle on planet");
            }
        }
    }

    public Optional<Obstacle> getObstacleFor(Position position) {
        for (Obstacle obstacle : obstacles) {
            if (obstacle.hasSameCoordinateWith(position)) {
                return of(obstacle);
            }
        }
        return empty();
    }

    public int getDimX() {
        return dimX;
    }

    public int getDimY() {
        return dimY;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        final Planet other = (Planet) obj;
        return other.getDimX() == dimX && other.getDimY() == dimY;
    }
}
