package mower;


import mower.exception.FileFormatInvalidException;
import mower.exception.InvalidGardenSizeException;
import mower.exception.MowerInitialPositionException;
import mower.models.Position;
import mower.services.MowerService;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static mower.Constant.baseDirForTestData;
import static mower.models.Direction.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MowerServiceTest {
    @Test
    void should_return_mower_position_with_turn_right() throws InvalidGardenSizeException, MowerInitialPositionException, IOException, FileFormatInvalidException {
        // Given
        MowerService mowerService = new MowerService(baseDirForTestData + "TurnRight.txt");

        // When
        List<Position> results = mowerService.launchMowers();

        // Then
        assertEquals(results.size(), 1);
        assertEquals(results.get(0), new Position(1, 2, SOUTH));
    }

    @Test
    void should_return_mower_position_with_turn_left() throws InvalidGardenSizeException, MowerInitialPositionException, IOException, FileFormatInvalidException {
        // Given
        MowerService mowerService = new MowerService(baseDirForTestData + "TurnLeft.txt");

        // When
        List<Position> results = mowerService.launchMowers();

        // Then
        assertEquals(results.size(), 1);
        assertEquals(results.get(0), new Position(1, 1, WEST));
    }

    @Test
    void should_return_mower_position_with_go_ahead() throws InvalidGardenSizeException, MowerInitialPositionException, IOException, FileFormatInvalidException {
        // Given
        MowerService mowerService = new MowerService(baseDirForTestData + "GoAhead.txt");

        // When
        List<Position> results = mowerService.launchMowers();

        // Then
        assertEquals(results.size(), 1);
        assertEquals(results.get(0), new Position(1, 2, NORTH));
    }

    @Test
    void should_return_mower_position_with_go_ahead_and_pass_limit() throws InvalidGardenSizeException, MowerInitialPositionException, IOException, FileFormatInvalidException {
        // Given
        MowerService mowerService = new MowerService(baseDirForTestData + "GoAheadAndPassLimit.txt");

        // When
        List<Position> results = mowerService.launchMowers();

        // Then
        assertEquals(results.size(), 1);
        assertEquals(results.get(0), new Position(2, 2, NORTH));
    }

    @Test
    void should_display_mower_final_position() throws InvalidGardenSizeException, MowerInitialPositionException, IOException, FileFormatInvalidException {
        // Given
        MowerService mowerService = new MowerService(baseDirForTestData + "MowerFinalPosition.txt");
        List<Position> positions = mowerService.launchMowers();

        // When
        String result = mowerService.getFinalMowersPositions(positions);

        // Then
        assertEquals(result, "1 3 N 5 1 E");
    }
}