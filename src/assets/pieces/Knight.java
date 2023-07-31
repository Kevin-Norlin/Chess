package assets.pieces;

import assets.Piece;
import game.Player;

import java.awt.*;

// "Springare"
public class Knight extends Piece {
    public Knight(Point p, Player player) {
        super(p, player);
    }
    public String getName() {
        return "Knight";
    }
}
