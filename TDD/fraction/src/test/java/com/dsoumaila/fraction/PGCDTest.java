package com.dsoumaila.fraction;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class PGCDTest {
    @Test
    public void shouldReturn_null_whenPgcd_WithZero_and_zero() {
        // Act
        Integer pgcd = PGCD.pgcd(0, 0);

        // Assert
        assertNull(pgcd);
    }

    @Test
    public void shouldReturn_number_WhenPgcd_WithZero_and_number() {
        // Act
        Integer pgcd = PGCD.pgcd(0, 2);

        // Assert
        assertEquals((Integer)2, pgcd);
    }

    @Test
    public void shouldReturn_number_WhenPgcd_WithNumber_and_zero() {
        // Act
        Integer pgcd = PGCD.pgcd(2, 0);

        // Assert
        assertEquals((Integer)2, pgcd);
    }

    @Test
    public void shouldReturn_pgcd_WhenPgcd_WithFirstNumber_and_secondNumber() {
        // Act
        Integer pgcd = PGCD.pgcd(7, 5);

        // Assert
        assertEquals((Integer)1, pgcd);
    }
}
