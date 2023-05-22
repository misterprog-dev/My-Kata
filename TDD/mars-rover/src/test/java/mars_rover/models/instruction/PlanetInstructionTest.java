package mars_rover.models.instruction;

import mars_rover.exception.InvalidDimensionException;
import mars_rover.models.Obstacle;
import mars_rover.models.Planet;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Set;

import static java.util.Set.of;
import static org.junit.jupiter.api.Assertions.*;

public class PlanetInstructionTest {
    @ParameterizedTest
    @ValueSource(strings = {"1", "a", "b"})
    void should_return_exception_for_dimension_input_with_bad_size(String dimension) {
        // when
        Exception exception = assertThrows(InvalidDimensionException.class,
                () -> new PlanetInstruction(dimension, of()).getPlanetFromDimension());

        // then
        String expectedMessage = "Invalid planet";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @ParameterizedTest
    @ValueSource(strings = {"1 a", "a a", "b 1"})
    void should_return_exception_for_dimension_input_with_bad_type_entered(String dimension) {
        // when
        Exception exception = assertThrows(InvalidDimensionException.class,
                () -> new PlanetInstruction(dimension, of()).getPlanetFromDimension());

        // then
        String expectedMessage = "Invalid planet";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void should_return_planet_for_valid_dimension_entered() {
        // When
        Planet result = new PlanetInstruction("2 4", Set.of(new Obstacle(1, 4))).getPlanetFromDimension();

        // Then
        assertEquals(result, new Planet(2, 4));
    }
}
