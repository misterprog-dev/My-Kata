package mower.helpers;

import mower.exception.FileFormatInvalidException;
import mower.exception.MowerInitialPositionException;
import mower.models.Command;
import mower.models.Garden;
import mower.models.Mower;
import mower.models.Position;
import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.List;

import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toList;
import static mower.models.Direction.NORTH;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MowerHelperTest {
    @Test
    void should_construct_mower_with_limit_garden_and_position_and_command() throws MowerInitialPositionException, FileFormatInvalidException {
        // Given
        Iterator<String> fileLinesIterator = asList("1 2 N", "GAGAGAGA", "3 3 E", "AADAAGAGA").iterator();

        // when
        List<Mower> mowers = MowerHelper.constructMowers(fileLinesIterator, new Garden(5, 5));

        // then
        assertEquals(mowers.size(), 2);
        Mower firstMower = mowers.get(0);
        assertEquals(firstMower.getPosition(), new Position(1, 2, NORTH));
        assertEquals(firstMower.getCommands().size(), 8);
        List<String> commandsValue = firstMower.getCommands().stream()
                .map(Command::getCode)
                .collect(toList());
        assertEquals(commandsValue, asList("G","A", "G", "A", "G", "A", "G", "A"));
    }

    @Test
    void should_ignore_unknown_command() throws MowerInitialPositionException, FileFormatInvalidException {
        // Given
        Iterator<String> fileLinesIterator = asList("1 2 N", "GAGZXSDYTA").iterator();

        // when
        List<Mower> mowers = MowerHelper.constructMowers(fileLinesIterator, new Garden(5, 5));

        // then
        Mower firstMower = mowers.get(0);
        assertEquals(firstMower.getCommands().size(), 5);
        List<String> commandsValue = firstMower.getCommands().stream()
                .map(Command::getCode)
                .collect(toList());
        assertEquals(commandsValue, asList("G","A", "G", "D", "A"));
    }
}
