package server.services;

import java.io.Serializable;
import java.util.ArrayList;

public class BlackjackClientState implements Serializable {
    private static final long serialVersionUID = 1L;

    public ArrayList<String> cards = null;
    public ArrayList<String> house_cards = null;

    public int id;
    public boolean game_over;
    public String err_string;
    public String player_count;
    public String house_count;

    public BlackjackClientState(int id, boolean game_over, String err_string, String player_count, String house_count,
            ArrayList<String> cards, ArrayList<String> house_cards) {
        this.id = id;
        this.game_over = game_over;
        this.err_string = err_string;
        this.player_count = player_count;
        this.house_count = house_count;
        this.cards = new ArrayList<>(cards);
        this.house_cards = new ArrayList<>(house_cards);
    }
}
