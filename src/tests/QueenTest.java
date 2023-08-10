package tests;

import assets.Piece;
import assets.pieces.Pawn;
import assets.pieces.Queen;
import game.Game;
import game.Player;

import java.awt.*;
import java.util.ArrayList;

public class QueenTest {
    public void test() {
        Game game = new Game();
        ArrayList<Piece> pieces = new ArrayList<>();

        Queen queen = new Queen(new Point(4, 4), new Player(1));
        pieces.add(queen);
        game.setPieces(pieces);

        // Test moving vertically
        queen.setPos(new Point(4, 6));
        System.out.println("Can move vertically: " + (queen.checkLogicNoEffects(game) == true));

        // Test moving horizontally
        queen.revert();
        queen.setPos(new Point(2, 4));
        System.out.println("Can move horizontally: " + (queen.checkLogicNoEffects(game) == true));

        // Test diagonal move
        queen.revert();
        queen.setPos(new Point(6, 6));
        System.out.println("Can move diagonally: " + (queen.checkLogicNoEffects(game) == true));

        // Test jumping over pieces horizontally
        Pawn pawn = new Pawn(new Point(3, 4), new Player(1));
        pieces.add(pawn);
        game.setPieces(pieces);
        queen.revert();
        queen.setPos(new Point(1, 4));
        System.out.println("Cant jump over piece horizontally: " + (queen.checkLogicNoEffects(game) == false));

        // Test jumping over pieces vertically
        queen.revert();
        queen.setPos(new Point(4, 7));
        queen.update();
        queen.setPos(new Point(4, 4));
        queen.update();
        System.out.println("Cant jump over piece vertically: " + (queen.checkLogicNoEffects(game) == false));

        // Test invalid diagonal jump over pieces
        queen.revert();
        queen.setPos(new Point(2, 2));
        pieces.clear();
        pieces.add(queen);
        pieces.add(new Pawn(new Point(3, 3), new Player(1)));
        game.setPieces(pieces);
        System.out.println("Cant jump diagonally over piece: " + (queen.checkLogicNoEffects(game) == false));

        // Test valid capture move
        queen.revert();
        queen.setPos(new Point(3, 3));
        pieces.clear();
        pieces.add(queen);
        pieces.add(new Pawn(new Point(3, 3), new Player(2)));
        game.setPieces(pieces);
        System.out.println("Can capture: " + (queen.checkLogicNoEffects(game) == true));

    }

}
