package mars_rover.models.instruction;

import mars_rover.exception.InvalidDimensionException;
import mars_rover.models.Obstacle;
import mars_rover.models.Planet;

import java.util.Set;

import static java.lang.Integer.parseInt;
import static java.util.Arrays.stream;
import static mars_rover.models.Constants.COORDINATES_SEPARATOR;
import static mars_rover.models.Constants.COORDINATES_SIZE;
import static org.apache.commons.lang3.StringUtils.isNumeric;

public class PlanetInstruction {
    private String dimension;
    private Set<Obstacle> obstacles;

    public PlanetInstruction(String dimension, Set<Obstacle> obstacles) {
        this.dimension = dimension;
        this.obstacles = obstacles;
    }

    public Planet getPlanetFromDimension() {
        String[] coordinates = dimension.split(COORDINATES_SEPARATOR);

        if (isNotValidPlanetDimension(coordinates)) {
            throw new InvalidDimensionException("Invalid planet");
        }

        return new Planet(parseInt(coordinates[0]), parseInt(coordinates[1]), obstacles);
    }

    private boolean isNotValidPlanetDimension(String[] coordinates) {
        return stream(coordinates).count() != COORDINATES_SIZE ||
                !isNumeric(coordinates[0]) ||
                !isNumeric(coordinates[1]);
    }
}
