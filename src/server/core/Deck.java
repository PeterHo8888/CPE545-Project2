package server.core;

import java.util.Collections;
import java.util.Stack;

public class Deck {

    private static Stack<Card> deck = null;

    public Deck() {
        deck = new Stack<>();
        for (Card.Type t : Card.Type.values())
            for (int i = 1; i <= 13; ++i)
                deck.push(new Card(i, t));
        shuffle();
    }

    public void shuffle() {
        Collections.shuffle(deck);
    }

    public Card get_card() {
        return deck.pop();
    }
}
