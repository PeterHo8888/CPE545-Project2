package server.core;

public class Card {

    public static enum Type {
        DIAMOND, CLUB, HEART, SPADE
    }

    private int value;
    private Type suit;

    public Card(int value, Type suit) {
        this.value = value;
        this.suit = suit;
    }

    public int get_val() {
        return value;
    }

    public String toString() {
        final String[] val = { "A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K" };
        final String[] sui = { "♦", "♣", "♥", "♠" };
        return val[value - 1] + sui[suit.ordinal()];
    }
}
