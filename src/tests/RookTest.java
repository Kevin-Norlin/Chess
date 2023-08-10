package tests;

import assets.Piece;
import assets.pieces.Pawn;
import assets.pieces.Rook;
import game.Game;
import game.Player;

import java.awt.*;
import java.util.ArrayList;

public class RookTest {
    public void test() {
        Game game = new Game();
        ArrayList<Piece> pieces = new ArrayList<>();

        Rook rook = new Rook(new Point(4, 4), new Player(1));
        pieces.add(rook);
        game.setPieces(pieces);

        // Test moving vertically
        rook.setPos(new Point(4, 6));
        System.out.println("Can move vertically: " + (rook.checkLogicNoEffects(game) == true));

        // Test moving horizontally
        rook.revert();
        rook.setPos(new Point(2, 4));
        System.out.println("Can move horizontally: " + (rook.checkLogicNoEffects(game) == true));

        // Test invalid diagonal move
        rook.revert();
        rook.setPos(new Point(6, 6));
        System.out.println("Cant move diagonally: " + (rook.checkLogicNoEffects(game) == false));

        // Test jumping over pieces horizontally
        Pawn pawn = new Pawn(new Point(3, 4), new Player(1));
        pieces.add(pawn);
        game.setPieces(pieces);
        rook.revert();
        rook.setPos(new Point(1, 4));
        System.out.println("Cant jump over piece horizontally: " + (rook.checkLogicNoEffects(game) == false));

        // Test jumping over pieces vertically
        rook.setPos(new Point(3,3));
        rook.update();
        rook.setPos(new Point(3, 7));
        rook.setPos(new Point(4,4));
        rook.update();
        System.out.println("Cant jump over piece vertically: " + (rook.checkLogicNoEffects(game) == false));

        // Test valid capture move
        pawn.setPos(new Point(4, 4));
        rook.revert();
        rook.setPos(new Point(4, 5));
        System.out.println("Can capture: " + (rook.checkLogicNoEffects(game) == true));


    }
}
