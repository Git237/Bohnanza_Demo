package BaseGame;

import java.util.Random;

public enum Cards {
    // enum initialization
    GARTENBOHNE("GartenBohne", 6, new int[][] {{2,2}, {3,3}}),
    ROTEBOHNE("RoteBohne", 8, new int[][] {{2,1}, {3,2}, {4,3}, {5,4}}),
    AUGENBOHNE("AugenBohne", 10, new int[][] {{2,1}, {4,2}, {5,3}, {6,4}}),
    SOJABOHNE("SojaBohne", 12, new int[][] {{2,1}, {4,2}, {5,3} ,{7,4}}),
    BRECHBOHNE("BrechBohne", 14, new int[][] {{3,1}, {5,2}, {6,3}, {7,4}}),
    SAUBOHNE("SauBohne", 16, new int[][] {{3,1}, {5,2}, {7,3}, {8,4}}),
    FRUERBOHNE("FruerBohne", 18, new int[][] {{3,1}, {6,2}, {8,3}, {9,4}}),
    BLAUEBOHNE("BlaueBohne", 20, new int[][] {{4,1}, {6,2}, {8,3}, {10,4}});

    // variable declaration
    private final String cardName;
    private final int cardQuantity;
    private final int[][] beanometer;

    // constructor
    Cards(String cardName, int cardQuantity, int[][] beanometer) {
        this.cardName = cardName;
        this.cardQuantity = cardQuantity;
        this.beanometer = beanometer;
    }

    // method to return variable
    public String showCardName() {
        return cardName;
    }

    public int showCardQuantity() {
        return cardQuantity;
    }

    public int[][] showBeanometer(){
        return beanometer;
    }

    public static void showCardDetails() {
        System.out.println("-----------------------");
        for (Cards card : Cards.values()) {
            System.out.println("Card Name: " + card.showCardName());
            System.out.println("Card Quantity: " + card.showCardQuantity());
            System.out.println("Beanometer:");
            int[][] beanometer = card.showBeanometer();
            for (int[] row : beanometer) {
                for (int elem : row) {
                    System.out.print(elem + " ");
                }
                System.out.println();
            }
            System.out.println("-----------------------");
        }
    }

    public static int totalNumberOfCards(){
        int total;
        total = Cards.GARTENBOHNE.cardQuantity + Cards.ROTEBOHNE.cardQuantity + Cards.AUGENBOHNE.cardQuantity + Cards.SOJABOHNE.cardQuantity + Cards.BRECHBOHNE.cardQuantity + Cards.SAUBOHNE.cardQuantity + Cards.FRUERBOHNE.cardQuantity + Cards.BLAUEBOHNE.cardQuantity;
        return total;
    }

    public static Cards getCardByName(String name) {
        for (Cards card : Cards.values()) {
            if (card.showCardName().equalsIgnoreCase(name)) {
                return card;
            }
        }
        return null;
    }

    public static Cards getRandomCard() {
        Cards[] cards = Cards.values();
        Random random = new Random();
        return cards[random.nextInt(cards.length)];
    }
}
