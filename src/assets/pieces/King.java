package assets.pieces;


import assets.Piece;
import game.Player;

import java.awt.*;

public class King extends Piece {
    public King(Point p, Player player) {
        super(p, player);
    }
    public String getName() {
        return "King";
    }
    public boolean isValidMove() {
        int xDiff = Math.abs(this.getPos().x - this.getPrevPos().x);
        int yDiff = Math.abs(this.getPos().y - this.getPrevPos().y);
        if ((xDiff == 1 && yDiff == 0) || (xDiff == 0 && yDiff == 1)) {
            return true;
        }
        return false;

    }
}
