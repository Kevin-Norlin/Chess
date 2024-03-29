package assets.pieces;

import assets.Piece;
import game.Player;

import java.awt.*;
import java.util.ArrayList;

// "Torn"
public class Rook extends Piece {
    private boolean firstMove;
    public Rook(Point p, Player player) {
        super(p, player, player.getNum() == 1 ? "/image/b_rook.png" : "/image/w_rook.png");
        this.firstMove = true;
    }

    public boolean isValidMove() {
        int xDiff = Math.abs(this.getPos().x - this.getPrevPos().x);
        int yDiff = Math.abs(this.getPos().y - this.getPrevPos().y);
        return (this.getPos().x == this.getPrevPos().x && yDiff != 0) || (this.getPos().y == this.getPrevPos().y && xDiff != 0);
    }

    @Override
    public boolean collisionInPath(ArrayList<Piece> pieces) {
        return collisionInPathVertHor(pieces);
    }

    public String getName() {
        return "Rook";
    }

    public void setFirstMove() {
        this.firstMove = false;
    }

    public boolean getFirstMove() {
        return this.firstMove;
    }

}
