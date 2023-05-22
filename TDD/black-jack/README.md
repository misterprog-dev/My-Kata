This kata is for scoring a set of cards using blackjack rules, and returning both a total score of the cards given, and a status of the game.

The score() method totals the cards given and returns a total score;

- Pass a single card to the score method and return the score.
- Passez plusieurs cartes à la méthode score et renvoyez le score.
- From 2 to 9: Each card has its own face value.
- Tens, Jacks, Queens and Kings are worth 10 and are called "Logs".
- Aces are worth 1 or 11 depending on the player's play. If your hand does not exceed 21, the Ace counts as 11. 
  If your hand is over 21, the Ace counts as 1. The value of the Ace is always calculated to your advantage.
- The hand called "Blackjack" consists of an Ace and a card worth 10, for a total of 21, received at the beginning.
