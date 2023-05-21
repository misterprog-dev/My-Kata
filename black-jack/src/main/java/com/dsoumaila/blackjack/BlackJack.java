package com.dsoumaila.blackjack;

import static com.dsoumaila.blackjack.Card.ACE;
import static com.dsoumaila.blackjack.Result.BLACKJACK;

public class BlackJack {
    private int score(Card ...cards) {
        int result = 0;
        boolean hasAce = false;
        for (Card card: cards) {
            if (card == ACE) hasAce = true;
            result+= card.getValue();
        }

        if (result > BLACKJACK && hasAce) result -= ACE.getValue() - 1;
        return result;
    }

    public Result getResult(Card ...cards) {
        return new Result(score(cards));
    }
}
