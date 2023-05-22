package com.dsoumaila;

import org.junit.Test;

import static com.dsoumaila.Fraction.INFINITE;
import static com.dsoumaila.Fraction.ZERO;
import static org.junit.Assert.assertEquals;

public class SubstractionFractionTest {
    @Test
    public void shouldReturn_zero_WhenSubstract_WithZeroAndZero() {
        // Arrange
        Fraction firstFraction = new Fraction(0);
        Fraction secondFraction = new Fraction(0);

        // Act
        Fraction substract = firstFraction.substract(secondFraction);

        // Assert
        assertEquals(substract, ZERO);
    }

    @Test
    public void shouldReturn_nonZeroFraction_WhenSubstract_WithNonZeroAndZero() {
        // Arrange
        Fraction firstFraction = Fraction.createFraction(2, 3);
        Fraction secondFraction = new Fraction(0);

        // Act
        Fraction substract = firstFraction.substract(secondFraction);

        // Assert
        assertEquals(substract, firstFraction);
    }

    @Test
    public void shouldReturn_nonZeroFractionOpposite_When_Substract_WithZeroAndNonZero(){
        // Arrange
        Fraction firstFraction = new Fraction(0);
        Fraction secondFraction = Fraction.createFraction(3, 4);

        // Act
        Fraction substract = firstFraction.substract(secondFraction);

        // Assert
        assertEquals(substract, secondFraction.opposite());
    }

    @Test
    public void substact_two_NonZerofraction() {
        // Arrange
        Fraction firstFraction = Fraction.createFraction(3, 4);
        Fraction secondFraction = Fraction.createFraction(5, 7);

        // Act
        Fraction substract = firstFraction.substract(secondFraction);

        // Assert
        assertEquals(substract, Fraction.createFraction(1, 28));
    }

    @Test
    public void substract_NonZero_With_FractionDenominatorAsZero() {
        // Arrange
        Fraction firstFraction = Fraction.createFraction(3, 2);
        Fraction secondFraction = Fraction.createFraction(3, 0);

        // Act
        Fraction substract = firstFraction.substract(secondFraction);

        // Assert
        assertEquals(substract, INFINITE);
    }
}
