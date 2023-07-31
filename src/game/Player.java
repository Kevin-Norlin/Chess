package game;

import assets.Piece;

import java.sql.Time;
import java.util.ArrayList;

public class Player {
    // TODO: Add timer capabilities for player (startTimer() etc..)
    private ArrayList<Piece> pieces;
    private int num;
    public Player(int num) {
        this.num = num;
    }
    public int getNum() {
        return this.num;
    }

}
