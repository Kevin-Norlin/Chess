package assets.pieces;

import assets.Piece;

import java.awt.*;

// "Bonde"
public class Pawn extends Piece {
    public Pawn(Point p, int width, int height, int player) {
        super(p, width, height, player);
    }
    public String getName() {
        return "Pawn";
    }
}
