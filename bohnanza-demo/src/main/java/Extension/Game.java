package Extension;

import java.util.*;

public class Game {
    public static int remainingCardsInDeck;
    private static final int MAX_RESHUFFLES = 3;

    public static void playGame(Scanner scanner) {
        int numPlayers = 0;
        while (numPlayers < 3 || numPlayers > 7) {
            System.out.println("Enter the number of players (3 to 7):");
            numPlayers = scanner.nextInt();
            scanner.nextLine();  // Consume newline
            if (numPlayers < 3 || numPlayers > 7) {
                System.out.println("Invalid number of players. Please enter a number between 3 and 7.");
            }
        }

        // Calculate total number of cards
        int totalCards = Cards.totalNumberOfCards();
        System.out.println("Total number of cards in the deck: " + totalCards);

        // Initialize deck with all cards
        List<Cards> deck = new ArrayList<>();
        for (Cards card : Cards.values()) {
            for (int i = 0; i < card.showCardQuantity(); i++) {
                deck.add(card);
            }
        }

        // Shuffle the deck
        Collections.shuffle(deck);

        // Create players
        List<Player> players = new ArrayList<>();
        for (int i = 0; i < numPlayers; i++) {
            players.add(new Player("Player " + (i + 1)));
        }

        // Distribute 5 cards to each player
        for (int i = 0; i < 5; i++) {
            for (Player player : players) {
                if (!deck.isEmpty()) {
                    player.addCardToHand(deck.remove(0));
                }
            }
        }

        // Store the number of remaining cards in the deck
        remainingCardsInDeck = deck.size();

        // Print each player's hand cards and their fields
        for (Player player : players) {
            System.out.println(player.getName() + ":");
            System.out.println("Hand cards:");
            for (Cards card : player.getHand()) {
                System.out.println(card.showCardName());
            }
            System.out.println("Field 1 cards count: " + player.getField1().getNumberOfCards());
            System.out.println("Field 2 cards count: " + player.getField2().getNumberOfCards());
            System.out.println("-----------------------");
        }

        System.out.println("Number of remaining cards in the deck: " + remainingCardsInDeck);

        // Initialize the phase handler
        Phase phase = new Phase(scanner);
        int reshuffleCount = 0;

        // Execute phases for each player
        while (reshuffleCount < MAX_RESHUFFLES) {
            for (Player player : players) {
                System.out.println("\n" + player.getName() + "'s turn:");

                // Phase 1: Plant Bean Cards from Your Hand
                phase.plantBeanCardsFromHand(player);

                // Phase 2: Turn Over and Trade Bean Cards
                phase.turnOverAndTradeBeanCards(player, players);

                // Phase 3: Plant Turned-over and Traded Bean Cards
                phase.plantTurnedOverAndTradedBeanCards(player);

                // Phase 4: Draw Bean Cards
                if (deck.isEmpty()) {
                    reshuffleDeck(deck);
                    reshuffleCount++;
                    if (reshuffleCount >= MAX_RESHUFFLES) {
                        break;
                    }
                }
                phase.drawBeanCards(player, deck);

                // Display player's status after completing all phases
                phase.displayPlayerStatus(player);

                System.out.println("\nTurn complete for " + player.getName());
            }
        }
        determineWinner(players);
    }

    private static void reshuffleDeck(List<Cards> deck) {
        for (Cards card : Cards.values()) {
            for (int i = 0; i < card.showCardQuantity(); i++) {
                deck.add(card);
            }
        }
        Collections.shuffle(deck);
        System.out.println("The deck has been refilled and shuffled.");
    }

    private static void determineWinner(List<Player> players) {
        // Implement logic to determine the winner based on the players' coins
        Player winner = null;
        int maxCoins = 0;
        for (Player player : players) {
            if (player.getCoins() > maxCoins) {
                maxCoins = player.getCoins();
                winner = player;
            }
        }
        System.out.println("The game has ended. The winner is " + (winner != null ? winner.getName() : "No winner") + " with " + maxCoins + " coins.");
    }
}

