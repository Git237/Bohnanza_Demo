package Extension;

import java.util.*;

public class Field {
    private final List<Cards> cards;

    public Field() {
        this.cards = new ArrayList<>();
    }

    public void plantCard(Cards card) {
        if (this.cards.isEmpty() || this.cards.get(0).equals(card)) {
            this.cards.add(card);
        } else {
            System.out.println("Cannot plant different type of card in this field.");
        }
    }

    public List<Cards> getCards() {
        return this.cards;
    }

    public int getNumberOfCards() {
        return this.cards.size();
    }

    public boolean canPlantCard(Cards card) {
        return this.cards.isEmpty() || this.cards.get(0).equals(card);
    }

    public void clearField() {
        this.cards.clear();
    }
}

