package assets.pieces;

import assets.Piece;

// "Löpare"
public class Bishop extends Piece {
    public Bishop(int x, int y, int width, int height, int player) {
        super(x, y, width, height, player);
    }
    public String getName() {
        return "Bishop";
    }
}
