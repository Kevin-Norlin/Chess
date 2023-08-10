package tests;

import assets.Piece;
import assets.pieces.King;
import assets.pieces.Pawn;
import game.Game;
import game.Player;

import java.awt.*;
import java.util.ArrayList;

public class KingTest {
    public void test() {

        Game game = new Game();
        ArrayList<Piece> pieces = new ArrayList<>();

        King king = new King(new Point(4, 4), new Player(1));
        pieces.add(king);
        game.setPieces(pieces);

        // Test moving vertically
        king.setPos(new Point(4, 5));
        System.out.println("Can move vertically: " + (king.checkLogicNoEffects(game) == true));

        // Test moving horizontally
        king.revert();
        king.setPos(new Point(3, 4));
        System.out.println("Can move horizontally: " + (king.checkLogicNoEffects(game) == true));

        // Test diagonal move
        king.revert();
        king.setPos(new Point(5, 5));
        System.out.println("Can move diagonally: " + (king.checkLogicNoEffects(game) == true));

        // Test invalid move
        king.revert();
        king.setPos(new Point(6, 7));
        System.out.println("Cant move to invalid position: " + (king.checkLogicNoEffects(game) == false));

        // Test valid capture move
        king.revert();
        king.setPos(new Point(5, 5));
        pieces.clear();
        pieces.add(king);
        pieces.add(new Pawn(new Point(5, 5), new Player(2)));
        game.setPieces(pieces);
        System.out.println("Can capture: " + (king.checkLogicNoEffects(game) == true));

    }

}
