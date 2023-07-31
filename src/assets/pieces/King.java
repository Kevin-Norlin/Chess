package assets.pieces;


import assets.Piece;
import game.Player;

import java.awt.*;

public class King extends Piece {
    public King(Point p, Player player) {
        super(p, player);
    }
    public String getName() {
        return "King";
    }
}
