package assets.pieces;

import assets.Piece;
import game.Player;

import java.awt.*;

// "Löpare"
public class Bishop extends Piece {
    public Bishop(Point p, Player player) {
        super(p, player);
    }
    public String getName() {
        return "Bishop";
    }

    // Diagonally
    public boolean isValidMove(Point newPos) {
        int xDiff = Math.abs(this.getPos().x - newPos.x);
        int yDiff = Math.abs(this.getPos().x - newPos.x);
        if (this.getPos() == newPos || xDiff == yDiff) {
            return true;
        }
        return false;
    }
}
