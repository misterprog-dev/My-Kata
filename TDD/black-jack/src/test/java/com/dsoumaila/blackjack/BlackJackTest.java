package com.dsoumaila.blackjack;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.dsoumaila.blackjack.Card.*;
import static org.junit.jupiter.api.Assertions.*;

public class BlackJackTest {
    BlackJack blackJack;

    @BeforeEach
    public void setUp() {
        blackJack = new BlackJack();
    }

    @Test
    public void shouldScoreACard() {
        assertEquals(blackJack.getResult(TWO).getScore(), 2);
    }

    @Test
    public void shouldScoreMultiplyCards() {
        assertEquals(blackJack.getResult(TWO, THREE, FOUR).getScore(), 9);
    }

    @Test
    public void shouldReturnBust() {
        Result result = blackJack.getResult(FOUR,SIX,SEVEN,NINE);

        // Assert
        assertTrue(result.isBust());
        assertFalse(result.isBlackjack());
    }

    @Test
    public void shouldReturnBlackJack_WithAceAndTen(){
        // Arrange
        Result result = blackJack.getResult(ACE,TEN);

        // Assert
        assertTrue(result.isBlackjack());
        assertFalse(result.isBust());
    }

    @Test
    public void shouldReturnAceCardAsOne(){
        // Arrange
        Result result = blackJack.getResult(ACE,TEN,TWO,THREE);

        // Assert
        assertEquals(result.getScore(), 16);
        assertFalse(result.isBlackjack());
        assertFalse(result.isBust());
    }
}
