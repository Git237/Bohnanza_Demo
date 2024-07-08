package BaseGame;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private final String name;
    private final List<Cards> hand;
    private final List<Cards> tradedCards; // New list for traded cards
    private final Field field1;
    private final Field field2;
    private int coins;

    public Player(String name) {
        this.name = name;
        this.hand = new ArrayList<>();
        this.tradedCards = new ArrayList<>(); // Initialize the traded cards list
        this.field1 = new Field();
        this.field2 = new Field();
        this.coins = 0;
    }

    public String getName() {
        return name;
    }

    public void addCardToHand(Cards card) {
        hand.add(card);
    }

    public void removeCardFromHand(Cards card) {
        hand.remove(card);
    }

    public List<Cards> getHand() {
        return hand;
    }

    public void addTradedCard(Cards card) {
        tradedCards.add(card);
    }

    public void clearTradedCards() {
        tradedCards.clear();
    }

    public List<Cards> getTradedCards() {
        return tradedCards;
    }

    public Field getField1() {
        return field1;
    }

    public Field getField2() {
        return field2;
    }

    public boolean plantCardInField1(Cards card) {
        if (field1.canPlantCard(card)) {
            field1.plantCard(card);
            return true;
        }
        return false;
    }

    public boolean plantCardInField2(Cards card) {
        if (field2.canPlantCard(card)) {
            field2.plantCard(card);
            return true;
        }
        return false;
    }

    public String showHand() {
        StringBuilder sb = new StringBuilder();
        for (Cards card : hand) {
            sb.append(card.showCardName()).append(" ");
        }
        return sb.toString().trim();
    }

    public void printPlayerState() {
        System.out.println("Hand cards: " + showHand());
        System.out.println("Field 1 cards:");
        for (Cards card : field1.getCards()) {
            System.out.println(card.showCardName());
        }
        System.out.println("Field 2 cards:");
        for (Cards card : field2.getCards()) {
            System.out.println(card.showCardName());
        }
    }

    public int getCoins() {
        return coins;
    }

    public void addCoins(int coins) {
        this.coins += coins;
    }
}
