package server.core;

import java.util.ArrayList;

import server.services.BlackjackClientState;

public class Game {
    private Deck deck;
    private int id;
    private boolean player_stand;
    private boolean player_busted;

    private ArrayList<Card> player_cards;
    private ArrayList<Card> house_cards;

    public Game(int id) {
        this.id = id;

        house_cards = new ArrayList<>();
        player_cards = new ArrayList<>();
        deck = new Deck();

        house_cards.add(deck.get_card());
        house_cards.add(deck.get_card());
        player_cards.add(deck.get_card());
        player_cards.add(deck.get_card());
    }

    public int get_hand_val(ArrayList<Card> hand) {
        int sum = 0;
        int num_aces = 0;
        for (Card c : hand) {
            int val = c.get_val();
            if (val >= 2 && val <= 10)
                sum += val;
            else if (val >= 11)
                sum += 10;
            else // aces
                ++num_aces;
        }

        for (int i = 0; i < num_aces; ++i)
            ++sum;

        while (num_aces-- > 0)
            if (sum + 10 <= 21)
                sum += 10;

        return sum;
    }

    private void house_deal() {
        while (get_hand_val(house_cards) < 17)
            house_cards.add(deck.get_card());
    }

    /*
     * 0 = stand, 1 = hit
     */
    public BlackjackClientState do_action(int action) {
        switch (action) {
        case 0:
            player_stand = true;
            break;
        case 1:
            player_cards.add(deck.get_card());
            break;

        }
        return get_state();
    }

    public BlackjackClientState get_state() {
        // Create the list of card strings to send to client
        ArrayList<String> cards = new ArrayList<>();
        ArrayList<String> cards2 = new ArrayList<>();
        for (Card c : player_cards)
            cards.add(c.toString());

        // Calculate the current state of the game
        int player_hand = get_hand_val(player_cards);
        if (player_hand > 21)
            player_busted = true;
        else if (player_hand == 21)
            player_stand = true;

        String player_count = Integer.toString(player_hand);

        if (player_stand || player_busted) {
            house_deal();
            for (Card c : house_cards)
                cards2.add(c.toString());
            int house_hand = get_hand_val(house_cards);
            String house_count = Integer.toString(house_hand);
            if (house_hand > 21)
                return new BlackjackClientState(id, true, player_busted ? "Draw" : "You Win!", player_count,
                        house_count, cards, cards2);
            else if (house_hand > player_hand)
                return new BlackjackClientState(id, true, "You lose", player_count, house_count, cards, cards2);
            else if (!player_busted)
                return new BlackjackClientState(id, true, house_hand == player_hand ? "Draw" : "You Win!", player_count,
                        house_count, cards, cards2);
            else
                return new BlackjackClientState(id, true, "You lose", player_count, house_count, cards, cards2);
        }

        return new BlackjackClientState(id, false, null, player_count, "?", cards, cards2);
    }
}
