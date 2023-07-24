package assets.pieces;

import assets.Piece;

// "Torn"
public class Rook extends Piece {
    public Rook(int x, int y, int width, int height, int player) {
        super(x, y, width, height, player);
    }
    public String getName() {
        return "Rook";
    }
}
