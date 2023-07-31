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
    @Override
    public boolean isValidMove() {
        int xDiff = Math.abs(this.getPos().x - this.getPrevPos().x);
        int yDiff = Math.abs(this.getPos().y - this.getPrevPos().y);
        if (xDiff == yDiff) {
            return true;
        }
        return false;
    }
}
