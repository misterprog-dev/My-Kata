package com.dsoumaila.blackjack;

public class Result {
    public static int BLACKJACK = 21;
    private int score;
    public Result(int score) {
        this.score = score;
    }

    public int getScore() {
        return this.score;
    }

    public boolean isBust() {
        return this.score > BLACKJACK;
    }

    public boolean isBlackjack() {
        return this.score == BLACKJACK;
    }
}
