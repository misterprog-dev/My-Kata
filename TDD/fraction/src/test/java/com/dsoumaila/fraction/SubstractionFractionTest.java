package com.dsoumaila.fraction;

import org.junit.jupiter.api.Test;

import static com.dsoumaila.fraction.Fraction.INFINITE;
import static com.dsoumaila.fraction.Fraction.ZERO;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SubstractionFractionTest {
    @Test
    public void shouldReturn_zero_WhenSubstract_WithZeroAndZero() {
        // Arrange
        Fraction firstFraction = new Fraction(0);
        Fraction secondFraction = new Fraction(0);

        // Act
        Fraction result = firstFraction.substract(secondFraction);

        // Assert
        assertEquals(ZERO, result);
    }

    @Test
    public void shouldReturn_nonZeroFraction_WhenSubstract_WithNonZeroAndZero() {
        // Arrange
        Fraction firstFraction = Fraction.createFraction(2, 3);
        Fraction secondFraction = new Fraction(0);

        // Act
        Fraction result = firstFraction.substract(secondFraction);

        // Assert
        assertEquals(firstFraction, result);
    }

    @Test
    public void shouldReturn_nonZeroFractionOpposite_When_Substract_WithZeroAndNonZero(){
        // Arrange
        Fraction firstFraction = new Fraction(0);
        Fraction secondFraction = Fraction.createFraction(3, 4);

        // Act
        Fraction result = firstFraction.substract(secondFraction);

        // Assert
        assertEquals(secondFraction.opposite(), result);
    }

    @Test
    public void substact_two_NonZerofraction() {
        // Arrange
        Fraction firstFraction = Fraction.createFraction(3, 4);
        Fraction secondFraction = Fraction.createFraction(5, 7);

        // Act
        Fraction result = firstFraction.substract(secondFraction);

        // Assert
        assertEquals(Fraction.createFraction(1, 28), result);
    }
}
