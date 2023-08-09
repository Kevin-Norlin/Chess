package assets.pieces;

import assets.Piece;
import game.Player;

import java.awt.*;
import java.util.ArrayList;

// "Torn"
public class Rook extends Piece {
    public Rook(Point p, Player player) {
        super(p, player, player.getNum() == 1 ? "/image/b_rook.png" : "/image/w_rook.png");
    }

    public boolean isValidMove() {
        int xDiff = Math.abs(this.getPos().x - this.getPrevPos().x);
        int yDiff = Math.abs(this.getPos().y - this.getPrevPos().y);
        if ((this.getPos().x == this.getPrevPos().x && yDiff != 0) || (this.getPos().y == this.getPrevPos().y && xDiff != 0)) {
            return true;
        }
        return false;
    }

    @Override
    public boolean collisionInPath(ArrayList<Piece> pieces) {
        return collisionInPathVertHor(pieces);
    }

    public String getName() {
        return "Rook";
    }

}
