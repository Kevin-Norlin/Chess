package assets.pieces;


import assets.Piece;

public class King extends Piece {
    public King(int x, int y, int width, int height, int player) {
        super(x, y, width, height, player);
    }
    public String getName() {
        return "King";
    }
}
