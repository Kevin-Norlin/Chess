package assets.pieces;

import assets.Piece;
import game.Player;

import java.awt.*;

// "Springare"
public class Knight extends Piece {
    public Knight(Point p, Player player) {
        super(p, player);
    }
    public String getName() {
        return "Knight";
    }
    // Moves in L-shape (2 forward, 1 to the side)
    public boolean isValidMove() {
        int xDiff = Math.abs(this.getPos().x - this.getPrevPos().x);
        int yDiff = Math.abs(this.getPos().y - this.getPrevPos().y);
        if ((yDiff == 2 && xDiff == 1) || (yDiff == 1 && xDiff == 2)) {
            return true;
        }
        return false;

    }
}
