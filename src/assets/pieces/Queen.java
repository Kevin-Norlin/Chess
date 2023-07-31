package assets.pieces;

import assets.Piece;
import game.Player;

import java.awt.*;

public class Queen extends Piece {
    public Queen(Point p, Player player) {
        super(p, player);
    }
    public String getName() {
        return "Queen";
    }
    // Diagonally, vertically and horizontally.
    public boolean isValidMove() {
        if (this.getPos().x == this.getPrevPos().x || this.getPos().y == this.getPrevPos().y) {
            return true;
        }
        int xDiff = Math.abs(this.getPos().x - this.getPrevPos().x);
        int yDiff = Math.abs(this.getPos().y - this.getPrevPos().y);
        if (xDiff == yDiff) {
            return true;
        }
        return false;
    }
}
