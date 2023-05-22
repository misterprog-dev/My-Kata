package com.dsoumaila;

import org.junit.Test;

import static com.dsoumaila.Fraction.INFINITE;
import static org.junit.Assert.assertEquals;

public class AdditionFractionTest {
    @Test
    public void zero_plus_zero_should_return_zero(){
        // Arrange
        Fraction zero = new Fraction(0);

        // Act
        Fraction sum = zero.add(zero);

        // Assert
        assertEquals(sum, zero);
    }

    @Test
    public void not_zero_fraction_plus_zero_fraction_should_return_not_zero_fraction() {
        // Arrange
        Fraction zero = new Fraction(0);
        Fraction notZero = new Fraction(3);

        // Act
        Fraction sum = notZero.add(zero);

        // Assert
        assertEquals(sum, notZero);
    }

    @Test
    public void zero_plus_not_zero_fraction_should_return_not_zero_fraction() {
        // Arrange
        Fraction zero = new Fraction(0);
        Fraction notZero = new Fraction(3);

        // Act
        Fraction sum = zero.add(notZero);

        // Assert
        assertEquals(sum, notZero);
    }

    @Test
    public void not_zero_plus_not_zero() {
        // Arrange
        Fraction wholeFraction = new Fraction(3);
        Fraction anotherWholeFraction = new Fraction(4);

        // Act
        Fraction sum = wholeFraction.add(anotherWholeFraction);

        // Assert
        assertEquals(3+4, sum.getNumerator());
    }

    @Test
    public void should_test_sum_fraction_with_same_denominator() {
        // Arrange
        Fraction firstFraction = Fraction.createFraction(2, 3);
        Fraction secondFraction = Fraction.createFraction(5, 3);

        // Act
        Fraction sum = firstFraction.add(secondFraction);

        // Assert
        assertEquals(2+5, sum.getNumerator());
        assertEquals(3, sum.getDenominator());
    }

    @Test
    public void should_test_sum_fraction_with_differents_denominators() {
        // Arrange
        Fraction firstFraction = Fraction.createFraction(2, 3);
        Fraction secondFraction = Fraction.createFraction(5, 4);

        // Act
        Fraction sum = firstFraction.add(secondFraction);

        // Assert
        assertEquals(23, sum.getNumerator());
        assertEquals(12, sum.getDenominator());
    }

    @Test
    public void should_test_sum_with_simplification(){
        // Arrange
        Fraction firstFraction = Fraction.createFraction(2, 6);
        Fraction secondFraction = Fraction.createFraction(1, 6);

        // Act
        Fraction sum = firstFraction.add(secondFraction);

        // Assert
        assertEquals(Fraction.createFraction(1, 2), sum);
    }

    @Test
    public void denominatorAsZero() {
        // Arrange
        Fraction firstFraction = Fraction.createFraction(1, 0);
        Fraction secondFraction = Fraction.createFraction(3, 4);

        // Act
        Fraction sum = firstFraction.add(secondFraction);

        // Assert
        assertEquals(INFINITE, sum);
    }
}
