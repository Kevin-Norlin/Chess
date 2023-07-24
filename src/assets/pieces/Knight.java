package assets.pieces;

import assets.Piece;

// "Springare"
public class Knight extends Piece {
    public Knight(int x, int y, int width, int height, int player) {
        super(x, y, width, height, player);
    }
    public String getName() {
        return "Knight";
    }
}
