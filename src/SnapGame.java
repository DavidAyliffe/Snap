import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

// Card class representing a single card in the deck
class Card {
    private String rank;
    private String suit;

    public Card(String rank, String suit) {
        this.rank = rank;
        this.suit = suit;
    }

    public String getRank() {
        return rank;
    }

    public String getSuit() {
        return suit;
    }

    @Override
    public String toString() {
        return rank + suit;
    }
}

// Deck class representing a deck of cards
class Deck {
    private List<Card> cards;

    public Deck() {
        String[] ranks = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A"};
        String[] suits = {"♥", "♦", "♣", "♠"};
        cards = new ArrayList<>();

        for (String rank : ranks) {
            for (String suit : suits) {
                cards.add(new Card(rank, suit));
            }
        }
        Collections.shuffle(cards);
    }

    public Card dealCard() {
        if (!cards.isEmpty()) {
            return cards.removeFirst();
        }
        return null;
    }

    public boolean isEmpty() {
        return cards.isEmpty();
    }
}

// SnapGame class representing the game logic
public class SnapGame {
    private final Deck deck;
    private final List<Card> player1Pile;
    private final List<Card> player2Pile;

    public SnapGame() {
        deck = new Deck();
        player1Pile = new ArrayList<>();
        player2Pile = new ArrayList<>();
    }

    public void play() {
        Scanner scanner = new Scanner(System.in);
        boolean gameOver = false;

        while (!deck.isEmpty() && !gameOver) {
            System.out.print("Press Enter to deal a card...");
            scanner.nextLine();

            Card player1Card = deck.dealCard();
            Card player2Card = deck.dealCard();

            player1Pile.add(player1Card);
            player2Pile.add(player2Card);

            System.out.printf("Player 1: %s Player 2: %s\n", player1Card, player2Card);

            if (player1Card.getRank().equals(player2Card.getRank())) {
                System.out.println("SNAP! Both players have a " + player1Card.getRank() + "!");
                System.out.printf("Player 1 has %s cards left\n", player1Pile.size());
                System.out.printf("Player 2 has %s cards left\n", player2Pile.size());
                gameOver = true;
            }
        }

        if (deck.isEmpty() && !gameOver) {
            System.out.println("No more cards in the deck. It's a draw!");
        } else {
            System.out.println("Game Over!");
        }
        scanner.close();
    }

    public static void main(String[] args) {
        SnapGame game = new SnapGame();
        game.play();
    }
}