package com.pluralsight;
import java.util.ArrayList;
import java.util.List;

public class Hand {
    private String name;
    private List<Card> cards;
    public Hand(String name) {
        this.name = name;
        cards = new ArrayList<>();
    }
    public void takeCard(Card card) {
        cards.add(card);
    }
    public int getScore() {
        int score = 0;
        int numAces = 0;
        for (Card card : cards) {
            score += card.getValue();
            if (card.getValue() == 11) {
                numAces++;
            }
        }
        // Adjust the score if there are aces and the total score is over 21
        while (numAces > 0 && score > 21) {
            // Change the value of the Ace from 11 to 1
            score -= 10;
            numAces--;
        }
        return score;
    }
    public List<Card> getCards() {
        return cards;
    }
    public String getName() {
        return name;
    }
}
