package com.dsoumaila.core;

import org.junit.Test;

import static org.junit.Assert.*;

public class FizzBuzzTest {

    @Test
    public void Should_return_same_number_When_it_is_not_multiple_of_three_or_five() {
        // ARRANGE
        FizzBuzz fizzBuzz = new FizzBuzz(1);

        // ACT
        String resultat = fizzBuzz.print();

        // ASSERT
        assertEquals("1", resultat);
    }

    @Test
    public void Should_return_Fizz_When_it_is_multiple_of_three() {
        // ARRANGE
        FizzBuzz fizzBuzz = new FizzBuzz(3);

        // ACT
        String resultat = fizzBuzz.print();

        // ASSERT
        assertEquals("Fizz", resultat);
    }

    @Test
    public void Should_return_Buzz_When_it_is_multiple_of_five() {
        // ARRANGE
        FizzBuzz fizzBuzz = new FizzBuzz(5);

        // ACT
        String resultat = fizzBuzz.print();

        // ASSERT
        assertEquals("Buzz", resultat);
    }

    @Test
    public void Should_return_FizzBuzz_When_it_is_multiple_of_three_and_five() {
        // ARRANGE
        FizzBuzz fizzBuzz = new FizzBuzz(15);

        // ACT
        String resultat = fizzBuzz.print();

        // ASSERT
        assertEquals("FizzBuzz", resultat);
    }
}
