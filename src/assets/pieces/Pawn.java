package assets.pieces;

import assets.Piece;
import game.Player;

import java.awt.*;

// "Bonde"
public class Pawn extends Piece {
    public Pawn(Point p, int width, int height, Player player) {
        super(p, player);
    }
    public String getName() {
        return "Pawn";
    }
}
