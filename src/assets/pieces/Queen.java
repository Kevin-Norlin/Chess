package assets.pieces;

import assets.Piece;

import java.awt.*;

public class Queen extends Piece {
    public Queen(Point p, int width, int height, int player) {
        super(p, width, height, player);
    }
    public String getName() {
        return "Queen";
    }
}
