package assets.pieces;

import assets.Piece;
import game.Player;

import java.awt.*;

// "Torn"
public class Rook extends Piece {
    public Rook(Point p, Player player) {
        super(p, player);
    }
    public String getName() {
        return "Rook";
    }
    // Vert / horizontal
    public boolean isValidMove() {
        if (this.getPos().x == this.getPrevPos().x || this.getPos().y == this.getPrevPos().y) {
            return true;
        }
        return false;
    }
}
