package com.dsoumaila;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PGCDTest {
    @Test
    public void shouldReturn_null_whenPgcd_WithZero_and_zero() {
        // Act
        Integer pgcd = PGCD.pgcd(0, 0);

        // Assert
        assertEquals(pgcd, null);
    }

    @Test
    public void shouldReturn_number_WhenPgcd_WithZero_and_number() {
        // Act
        Integer pgcd = PGCD.pgcd(0, 2);

        // Assert
        assertEquals(pgcd, (Integer)2);
    }

    @Test
    public void shouldReturn_number_WhenPgcd_WithNumber_and_zero() {
        // Act
        Integer pgcd = PGCD.pgcd(2, 0);

        // Assert
        assertEquals(pgcd, (Integer)2);
    }

    @Test
    public void shouldReturn_pgcd_WhenPgcd_WithFirstNumber_and_secondNumber() {
        // Act
        Integer pgcd = PGCD.pgcd(7, 5);

        // Assert
        assertEquals(pgcd, (Integer)1);
    }
}
