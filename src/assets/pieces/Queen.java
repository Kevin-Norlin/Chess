package assets.pieces;

import assets.Piece;
import game.Player;

import java.awt.*;
import java.util.ArrayList;

public class Queen extends Piece {
    public Queen(Point p, Player player) {
        super(p, player, player.getNum() == 1 ? "/image/b_queen.png" : "/image/w_queen.png");
    }

    // Diagonally, vertically and horizontally.
    public boolean isValidMove() {
        int xDiff = Math.abs(this.getPos().x - this.getPrevPos().x);
        int yDiff = Math.abs(this.getPos().y - this.getPrevPos().y);
        if ((this.getPos().x == this.getPrevPos().x && yDiff != 0) || (this.getPos().y == this.getPrevPos().y && xDiff != 0)) {
            return true;
        }
        if (xDiff == yDiff && xDiff != 0) {
            return true;
        }
        return false;
    }

    @Override
    public boolean collisionInPath(ArrayList<Piece> pieces) {
        return collisionInPathDiag(pieces) || collisionInPathVertHor(pieces);
    }

    public String getName() {
        return "Queen";
    }
}
