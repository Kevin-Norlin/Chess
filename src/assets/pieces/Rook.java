package assets.pieces;

import assets.Piece;

import java.awt.*;

// "Torn"
public class Rook extends Piece {
    public Rook(Point p, int width, int height, int player) {
        super(p, width, height, player);
    }
    public String getName() {
        return "Rook";
    }
}
