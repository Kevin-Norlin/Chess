package assets.pieces;


import assets.Piece;
import game.Player;

import java.awt.*;
import java.util.ArrayList;

public class King extends Piece {
    public King(Point p, Player player) {
        super(p, player, player.getNum() == 1 ? "/image/b_king.png" : "/image/w_king.png");
    }
    public String getName() {
        return "King";
    }
    // The king moves all directions 1 tile
    public boolean isValidMove() {
        int xDiff = Math.abs(this.getPos().x - this.getPrevPos().x);
        int yDiff = Math.abs(this.getPos().y - this.getPrevPos().y);
        if ((xDiff == 1 && yDiff == 0) || (xDiff == 0 && yDiff == 1) || (xDiff == 1 && yDiff == 1)) {
            return true;
        }
        return false;
    }



}
