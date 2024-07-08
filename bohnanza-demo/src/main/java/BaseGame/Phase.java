package BaseGame;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Phase {
    private final Scanner scanner;

    public Phase(Scanner scanner) {
        this.scanner = scanner;
    }

    public Scanner getScanner() {
        return scanner;
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
        int fieldChoice = scanner.nextInt();
        scanner.nextLine();  // Consume newline
        boolean planted = false;
        if (fieldChoice == 1) {
            planted = player.plantCardInField1(card);
        } else if (fieldChoice == 2) {
            planted = player.plantCardInField2(card);
        }
        if (planted) {
            player.removeCardFromHand(card);
            System.out.println("Planted " + card.showCardName() + " in Field " + fieldChoice);
        } else {
            System.out.println("Cannot plant " + card.showCardName() + " in Field " + fieldChoice);
        }
    }

    // Other phases remain unchanged...
    // Phase 2: Turn Over and Trade Bean Cards
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

    // Phase 3: Plant Turned-over and Traded Bean Cards
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
        System.out.println("Drawing 3 cards from the deck...");
        for (int i = 0; i < 3; i++) {
            if (!deck.isEmpty()) {
                Cards card = deck.remove(0);
                player.addCardToHand(card);
                System.out.println("Drew " + card.showCardName());
            } else {
                System.out.println("The deck is empty. Cannot draw more cards.");
                break;
            }
        }
    }

    // New Phase: Harvest Beans in Field
    public void harvestBeansInField(Player player) {
        System.out.print("Choose a field to harvest (1 or 2): ");
        int fieldChoice = scanner.nextInt();
        scanner.nextLine();  // Consume newline

        Field field = (fieldChoice == 1) ? player.getField1() : player.getField2();
        int numberOfCards = field.getNumberOfCards();
        if (numberOfCards > 0) {
            Cards beanType = field.getCards().get(0);
            int coinsEarned = calculateCoins(beanType, numberOfCards);
            player.addCoins(coinsEarned);
            field.clearField();
            System.out.println("Harvested " + numberOfCards + " " + beanType.showCardName() + " cards and earned " + coinsEarned + " coins.");
        } else {
            System.out.println("Field " + fieldChoice + " is empty. Cannot harvest.");
        }
    }

    private int calculateCoins(Cards beanType, int numberOfCards) {
        // Implement the logic to calculate the number of coins based on the bean type and number of cards
        // Replace with actual logic
        return (numberOfCards / 4); // Example placeholder calculation
    }

    // Method to display player's status after completing all phases
    public void displayPlayerStatus(Player player) {
        System.out.println("\nPlayer Status:");
        System.out.println("Player: " + player.getName());
        System.out.println("Coins: " + player.getCoins());

        System.out.print("Hand: ");
        for (Cards card : player.getHand()) {
            System.out.print(card.showCardName() + " ");
        }
        System.out.println();

        System.out.print("Field 1: ");
        for (Cards card : player.getField1().getCards()) {
            System.out.print(card.showCardName() + " ");
        }
        System.out.println();

        System.out.print("Field 2: ");
        for (Cards card : player.getField2().getCards()) {
            System.out.print(card.showCardName() + " ");
        }
        System.out.println();
    }
}
