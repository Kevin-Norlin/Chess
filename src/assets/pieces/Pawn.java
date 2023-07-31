package assets.pieces;

import assets.Piece;
import game.Player;

import java.awt.*;

// "Bonde"
public class Pawn extends Piece {
    private boolean firstMove; // The pawn piece gets to move two tiles on the first move
    public Pawn(Point p, Player player) {
        super(p, player);
        firstMove = true;
    }



    public String getName() {
        return "Pawn";
    }
    public boolean isValidMove() {
        int xDiff = this.getPos().x - this.getPrevPos().x;
        int yDiff = this.getPos().y - this.getPrevPos().y;
        // The top player (p1) gets to move 2 tiles down the first time moving and then 1 tile.
        if (this.getPlayer().getNum() == 1) {
            if (firstMove && yDiff <= 2 && yDiff >= 0 && xDiff == 0) {
                this.firstMove = false;
                return true;
            }
            if (yDiff <= 1 && yDiff >= 0 && xDiff == 0) {
                this.firstMove = false;
                return true;
            }
            return false;
        }
        // The bottom player (p2) gets to move 2 tiles up the first time moving and then 1 tile.
        else {
            if (firstMove && yDiff >= -2 && yDiff <= 0 && xDiff == 0) {
                this.firstMove = false;
                return true;
            }
            if (yDiff >= -1 && yDiff <= 0 && xDiff == 0) {
                this.firstMove = false;
                return true;
            }
            return false;

        }
    }
}
