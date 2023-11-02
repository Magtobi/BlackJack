package com.pluralsight;

import java.util.Scanner;

public class BlackJackGame {
    public static void main(String[] args) {
        Scanner myScanner = new Scanner(System.in);

        Deck deck = new Deck();
        deck.shuffle();
        Hand player = new Hand("Player");
        Hand dealer = new Hand("Dealer");
        // Deal 2 cards to each hand
        for (int i = 0; i < 2; i++) {
            player.takeCard(deck.dealCard());
            dealer.takeCard(deck.dealCard());
        }
        displayHand(player);
        displayPartialHand(dealer);
        // Players turn
        while (player.getScore() < 21) {
            System.out.println("Do you want to 'hit' or 'stand'? Enter your choice: ");
            String choice = myScanner.nextLine().toLowerCase();
            if (choice.equals("hit")) {
                player.takeCard(deck.dealCard());
                displayHand(player);
                displayPartialHand(dealer);
            } else if (choice.equals("stand")) {
                break;
            } else {
                System.out.println("Not a valid choice. Enter 'hit or 'stand'.");
            }
        }
        // Dealers turn
        while (dealer.getScore() < 17) {
            dealer.takeCard(deck.dealCard());
        }
        // Display final hands
        displayHand(player);
        displayHand(dealer);
        determineWinner(player, dealer);
    }
    private static void displayHand(Hand hand) {
        System.out.println(hand.getName() + "'s Hand:");
        for (Card card : hand.getCards()) {
            System.out.println(card);
        }
        System.out.println(hand.getName() + "'s Score: " + hand.getScore());
    }
    private static void displayPartialHand(Hand hand) {
        System.out.println(hand.getName() + "'s Hand:");
        System.out.println(hand.getCards().get(0)); // Display first card
        System.out.println(hand.getName() + "'s Score: " + hand.getCards().get(0).getValue());
    }
    private static void determineWinner(Hand player, Hand dealer) {
        int playerScore = player.getScore();
        int dealerScore = dealer.getScore();
        if (playerScore > 21) {
            System.out.println("Player Busts. Dealer wins.");
        } else if (dealerScore > 21 || playerScore > dealerScore) {
            System.out.println("Player wins!");
        } else if (playerScore < dealerScore) {
            System.out.println("Dealer wins!");
        } else {
            System.out.println("It is a tie!");
        }
    }
}
