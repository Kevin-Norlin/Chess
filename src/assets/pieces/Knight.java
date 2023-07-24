package assets.pieces;

import assets.Piece;

import java.awt.*;

// "Springare"
public class Knight extends Piece {
    public Knight(Point p, int width, int height, int player) {
        super(p, width, height, player);
    }
    public String getName() {
        return "Knight";
    }
}
