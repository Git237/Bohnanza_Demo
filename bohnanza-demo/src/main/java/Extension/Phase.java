package Extension;

import java.util.*;

public class Phase {
    private final Scanner scanner;

    public Phase(Scanner scanner) {
        this.scanner = scanner;
    }

    // Phase 1: Plant Bean Cards from Your Hand
    public void plantBeanCardsFromHand(Player player) {
        List<Cards> hand = player.getHand();
        if (hand.isEmpty()) {
            System.out.println(player.getName() + " has no cards to plant.");
            return;
        }

        // Plant the first card in the hand
        Cards firstCard = hand.get(0);
        plantCardFromHand(player, firstCard, 1);

        // Ask if the player wants to plant the second card
        if (hand.size() > 1) {
            Cards secondCard = hand.get(1);
            System.out.print("Do you want to plant the second card from your hand (" + secondCard.showCardName() + ")? (yes/no): ");
            String choice = scanner.nextLine();
            if (choice.equalsIgnoreCase("yes")) {
                plantCardFromHand(player, secondCard, 2);
            }
        }
    }

    private void plantCardFromHand(Player player, Cards card, int cardPosition) {
        System.out.println(player.getName() + ", you must plant the card: " + card.showCardName());
        System.out.print("Choose a field to plant the card (1 or 2): ");
        int fieldNumber = scanner.nextInt();
        scanner.nextLine();  // Consume newline

        if (fieldNumber == 1) {
            player.getField1().plantCard(card);
        } else if (fieldNumber == 2) {
            player.getField2().plantCard(card);
        } else {
            System.out.println("Invalid field number. Planting in Field 1 by default.");
            player.getField1().plantCard(card);
        }

        // Remove the planted card from the player's hand
        player.getHand().remove(card);
    }

//     Phase 2: Turn Over and Trade Bean Cards
    public void turnOverAndTradeBeanCards(Player currentPlayer, List<Player> players) {
        System.out.println("Turning over top 2 cards from the deck...");

        // Simulate turning over 2 cards (here we assume a method drawCardFromDeck() to get cards from the deck)
        List<Cards> turnedOverCards = new ArrayList<>();
        turnedOverCards.add(drawCardFromDeck());
        turnedOverCards.add(drawCardFromDeck());

        System.out.println("Turned over cards: " + turnedOverCards.get(0).showCardName() + ", " + turnedOverCards.get(1).showCardName());

        // Handle trading logic (for simplicity, assume trading with other players directly)
        for (Cards card : turnedOverCards) {
            System.out.print("Do you want to keep " + card.showCardName() + " or trade it? (keep/trade): ");
            String choice = scanner.nextLine();
            if (choice.equalsIgnoreCase("keep")) {
                currentPlayer.addTradedCard(card); // Add to the current player's traded cards
            } else {
                System.out.print("Enter the name of the player you want to trade with: ");
                String playerName = scanner.nextLine();
                Player tradePartner = findPlayerByName(players, playerName);
                if (tradePartner != null) {
                    // Add trading logic (for simplicity, add the card to the partner's traded cards)
                    tradePartner.addTradedCard(card);
                } else {
                    System.out.println("Player not found.");
                }
            }
        }
    }

    private Cards drawCardFromDeck() {
        // Simulate drawing a card from the deck
        // Replace with actual deck drawing logic
        return Cards.getRandomCard(); // Example placeholder method
    }

    private Player findPlayerByName(List<Player> players, String name) {
        for (Player player : players) {
            if (player.getName().equalsIgnoreCase(name)) {
                return player;
            }
        }
        return null;
    }

//     Phase 3: Plant Turned-over and Traded Bean Cards
    public void plantTurnedOverAndTradedBeanCards(Player player) {
        List<Cards> tradedCards = player.getTradedCards();
        for (Cards card : tradedCards) {
            System.out.print("Choose a field to plant the traded card " + card.showCardName() + " (1 or 2): ");
            int fieldChoice = scanner.nextInt();
            scanner.nextLine();  // Consume newline
            boolean planted = false;
            if (fieldChoice == 1) {
                planted = player.plantCardInField1(card);
            } else if (fieldChoice == 2) {
                planted = player.plantCardInField2(card);
            }
            if (planted) {
                System.out.println("Planted " + card.showCardName() + " in Field " + fieldChoice);
            } else {
                System.out.println("Cannot plant " + card.showCardName() + " in Field " + fieldChoice);
            }
        }
        player.clearTradedCards();
    }

    // Phase 4: Draw Bean Cards
    public void drawBeanCards(Player player, List<Cards> deck) {
        // Draw 3 cards from the deck
        for (int i = 0; i < 3; i++) {
            if (!deck.isEmpty()) {
                Cards card = deck.remove(0);
                player.addCardToHand(card);
                System.out.println(player.getName() + " drew a card: " + card.showCardName());
            }
        }
    }

    public void displayPlayerStatus(Player player) {
        // Display player's hand cards
        System.out.println(player.getName() + "'s hand cards:");
        for (Cards card : player.getHand()) {
            System.out.println(card.showCardName());
        }

        // Display player's fields
        System.out.println(player.getName() + "'s fields:");
        System.out.println("Field 1 cards count: " + player.getField1().getNumberOfCards());
        System.out.println("Field 2 cards count: " + player.getField2().getNumberOfCards());
        System.out.println("Coins: " + player.getCoins());
    }
}
