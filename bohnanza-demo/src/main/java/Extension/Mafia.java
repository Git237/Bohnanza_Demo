package Extension;

import java.util.*;

public class Mafia extends Player {

    public Mafia(String name) {
        super(name);
    }

    public void takeAction(List<Player> players, List<Cards> deck) {
        // Mafia gets beans from players' fields
        for (Player player : players) {
            System.out.println(getName() + " is taking beans from " + player.getName() + "'s fields.");
            stealBeans(player);
        }

        // Mafia gets beans from the discard pile
        if (!deck.isEmpty()) {
            Cards card = deck.remove(0);
            addCardToHand(card);
            System.out.println(getName() + " took a bean from the discard pile: " + card.showCardName());
        }
    }

    private void stealBeans(Player player) {
        Field field1 = player.getField1();
        Field field2 = player.getField2();

        // Take all beans from one of the fields (field with more beans)
        if (field1.getNumberOfCards() > field2.getNumberOfCards()) {
            stealFromField(field1);
        } else {
            stealFromField(field2);
        }
    }

    private void stealFromField(Field field) {
        List<Cards> beans = field.getCards();
        for (Cards bean : beans) {
            addCardToHand(bean);
        }
        field.clearField();
    }
}
