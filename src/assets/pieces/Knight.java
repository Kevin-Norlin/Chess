package assets.pieces;

import assets.Piece;
import game.Player;

import java.awt.*;
import java.util.ArrayList;

// "Springare"
public class Knight extends Piece {
    public Knight(Point p, Player player) {
        super(p, player, player.getNum() == 1 ? "/image/b_knight.png" : "/image/w_knight.png");
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
    @Override
    public boolean collisionInPath(ArrayList<Piece> pieces) {
        for (Piece p: pieces) {
            if (p.equals(this)) {
                continue;
            }
            // If the knight tries to move to the same tile as another friendly piece
            if (this.getPos().x == p.getPos().x && this.getPos().y == p.getPos().y && this.getPlayer().equals(p.getPlayer())) {
                return true;
            }
        } return false;
    }

}
