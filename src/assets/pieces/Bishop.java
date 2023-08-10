package assets.pieces;

import assets.Piece;
import game.Player;

import java.awt.*;
import java.util.ArrayList;

// "Löpare"
public class Bishop extends Piece {

    public Bishop(Point p, Player player) {
        super(p, player, player.getNum() == 1 ? "/image/b_bishop.png" : "/image/w_bishop.png");
    }

    // Diagonally
    @Override
    public boolean isValidMove() {
        int xDiff = Math.abs(this.getPos().x - this.getPrevPos().x);
        int yDiff = Math.abs(this.getPos().y - this.getPrevPos().y);
        return xDiff == yDiff && xDiff != 0;
    }

    @Override
    public boolean collisionInPath(ArrayList<Piece> pieces) {
       return collisionInPathDiag(pieces);
    }

    public String getName() {
        return "Bishop";
    }
}
