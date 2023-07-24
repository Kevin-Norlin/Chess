package assets.pieces;


import assets.Piece;

import java.awt.*;

public class King extends Piece {
    public King(Point p, int width, int height, int player) {
        super(p, width, height, player);
    }
    public String getName() {
        return "King";
    }
}
