package tests;

import assets.Piece;
import assets.pieces.Bishop;
import assets.pieces.Pawn;
import game.Game;
import game.Player;

import java.awt.*;
import java.util.ArrayList;

public class BishopTest {
    public void test() {
        Game game = new Game();
        ArrayList<Piece> pieces = new ArrayList<>();

        Bishop bishop = new Bishop(new Point(4, 4), new Player(1));
        pieces.add(bishop);
        game.setPieces(pieces);

        // Test moving diagonally
        bishop.setPos(new Point(6, 6));
        System.out.println("Can move diagonally: " + (bishop.checkLogicNoEffects(game) == true));

        // Test invalid vertical move
        bishop.revert();
        bishop.setPos(new Point(4, 6));
        System.out.println("Cant move vertically: " + (bishop.checkLogicNoEffects(game) == false));

        // Test invalid horizontal move
        bishop.revert();
        bishop.setPos(new Point(6, 4));
        System.out.println("Cant move horizontally: " + (bishop.checkLogicNoEffects(game) == false));

        // Test jumping over pieces diagonally
        Pawn pawn = new Pawn(new Point(5, 5), new Player(1));
        pieces.add(pawn);
        game.setPieces(pieces);
        bishop.revert();
        bishop.setPos(new Point(7, 7));
        System.out.println("Cant jump over piece diagonally: " + (bishop.checkLogicNoEffects(game) == false));

        // Test valid capture move
        pawn.setPos(new Point(7, 7));
        bishop.revert();
        bishop.setPos(new Point(5, 5));
        System.out.println("Can capture: " + (bishop.checkLogicNoEffects(game) == true));

    }

}
