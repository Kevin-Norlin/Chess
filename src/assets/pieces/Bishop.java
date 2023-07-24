package assets.pieces;

import assets.Piece;

import java.awt.*;

// "Löpare"
public class Bishop extends Piece {
    public Bishop(Point p, int width, int height, int player) {
        super(p, width, height, player);
    }
    public String getName() {
        return "Bishop";
    }
}
