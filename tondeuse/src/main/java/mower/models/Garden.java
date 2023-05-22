package mower.models;

import mower.exception.InvalidGardenSizeException;

import static java.lang.Integer.parseInt;
import static java.util.Arrays.stream;
import static org.apache.commons.lang3.StringUtils.isNumeric;

public class Garden {
    public static final int GARDEN_COORDINATE_X_POSITION = 0;
    public static final int GARDEN_COORDINATE_Y_POSITION = 1;
    public static final int GARDEN_NUMBER_COORDINATES = 2;
    private final int x;
    private final int y;

    public Garden(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Garden(String[] line) throws InvalidGardenSizeException {
        if (isNotGardenSizeValid(line)) {
            throw new InvalidGardenSizeException("Garden size is invalid");
        }
        this.x = parseInt(line[GARDEN_COORDINATE_X_POSITION]);
        this.y = parseInt(line[GARDEN_COORDINATE_Y_POSITION]);
    }

    private boolean isNotGardenSizeValid(String[] line) {
        return stream(line).count() < GARDEN_NUMBER_COORDINATES ||
                !isNumeric(line[GARDEN_COORDINATE_X_POSITION]) ||
                !isNumeric(line[GARDEN_COORDINATE_Y_POSITION]);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
