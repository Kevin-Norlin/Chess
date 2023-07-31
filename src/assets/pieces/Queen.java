package assets.pieces;

import assets.Piece;
import game.Player;

import java.awt.*;

public class Queen extends Piece {
    public Queen(Point p, Player player) {
        super(p, player);
    }
    public String getName() {
        return "Queen";
    }
}
