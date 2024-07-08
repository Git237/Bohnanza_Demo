package Extension;

import java.util.*;

public class AlCabohne {
    public static void playAlCabohne(Scanner scanner) {
        int numPlayers = 0;
        while (numPlayers < 1 || numPlayers > 2) {
            System.out.println("Enter the number of players (1 or 2):");
            numPlayers = scanner.nextInt();
            scanner.nextLine();  // Consume newline
            if (numPlayers < 1 || numPlayers > 2) {
                System.out.println("Invalid number of players. Please enter 1 or 2.");
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

        // Create players and mafia
        List<Player> players = new ArrayList<>();
        for (int i = 0; i < numPlayers; i++) {
            players.add(new Player("Player " + (i + 1)));
        }
        Mafia mafia = new Mafia("Mafia");

        // Distribute 5 cards to each player
        for (int i = 0; i < 5; i++) {
            for (Player player : players) {
                if (!deck.isEmpty()) {
                    player.addCardToHand(deck.remove(0));
                }
            }
        }

        // Store the number of remaining cards in the deck
        int remainingCardsInDeck = deck.size();
        System.out.println("Number of remaining cards in the deck: " + remainingCardsInDeck);

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

        // Initialize the phase handler
        Phase phase = new Phase(scanner);
        int reshuffleCount = 0;

        // Execute phases for each player
        while (reshuffleCount < 3) { // Limit to 3 reshuffles
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
                    if (reshuffleCount >= 3) {
                        break;
                    }
                }
                phase.drawBeanCards(player, deck);

                // Additional Phase for Al Cabohne: Mafia Actions
                mafia.takeAction(players, deck);

                // Display player's status after completing all phases
                phase.displayPlayerStatus(player);

                System.out.println("\nTurn complete for " + player.getName());
            }
        }
        determineWinner(players, mafia);
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

    private static void determineWinner(List<Player> players, Mafia mafia) {
        // Implement logic to determine the winner based on the players' and mafia's coins
        Player winner = null;
        int maxCoins = mafia.getCoins(); // Start with mafia's coins
        String winnerName = mafia.getName(); // Assume mafia is the winner initially

        for (Player player : players) {
            if (player.getCoins() > maxCoins) {
                maxCoins = player.getCoins();
                winner = player;
                winnerName = player.getName();
            }
        }

        System.out.println("The game has ended. The winner is " + winnerName + " with " + maxCoins + " coins.");
    }
}
