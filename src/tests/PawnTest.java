package tests;

import assets.Piece;
import assets.pieces.Pawn;
import game.Game;
import game.Player;

import java.awt.*;
import java.util.ArrayList;

public class PawnTest {
    public void test() {
        Game game = new Game();
        ArrayList<Piece> pieces = new ArrayList<>();
        Pawn p1Pawn1 = new Pawn(new Point(2,2),new Player(1));
        Pawn p1Pawn2 = new Pawn(new Point(2,4),new Player(1));

        Pawn p2Pawn1 = new Pawn(new Point(7, 7), new Player(2));
        Pawn p2Pawn2 = new Pawn(new Point(7, 6), new Player(2));

        // Player 1
        // Move backwards
        p1Pawn1.setPos(new Point(2,1));
        pieces.add(p1Pawn1);
        System.out.println("Cant move backwards: " + (p1Pawn1.checkLogicNoEffects(game) == false));

        // Move horizontally
        p1Pawn1.revert();
        p1Pawn1.setPos(new Point(1,2));
        pieces.clear();
        pieces.add(p1Pawn1);
        System.out.println("Cant move vertically: " + (p1Pawn1.checkLogicNoEffects(game) == false));

        // Test firstMove 2 moves
        p1Pawn1 = new Pawn(new Point(2,2),new Player(1));
        p1Pawn1.setPos(new Point(2,4));
        pieces.add(p1Pawn1);
        game.setPieces(pieces);
        System.out.println("Initial 2 move: " + (p1Pawn1.checkLogic(game) == true));
        p1Pawn1.update(); // p1Pawn1 updated to pos (2,4)
        // Try to move twice again
        p1Pawn1.setPos(new Point(2,6));
        game.setPieces(pieces);
        System.out.println("2 move after initial: " + (p1Pawn1.checkLogicNoEffects(game) == false));
        p1Pawn1.revert();

        // Move once in beginning then try to move twice
        p1Pawn1 = new Pawn(new Point(2,2),new Player(1));
        p1Pawn1.setPos(new Point(2,3));
        p1Pawn1.isValidMove(); // Checks valid but also sets firstMove = false
        p1Pawn1.update();
        // Try to move twice after initial
        p1Pawn1.setPos(new Point(2,5));
        pieces.clear();
        pieces.add(p1Pawn1);
        game.setPieces(pieces);
        System.out.println("2 move after initial 1 move: " + (p1Pawn1.checkLogicNoEffects(game)== false));

        // Move to same pos as friendly piece
        p1Pawn1 = new Pawn(new Point(2,2),new Player(1));
        p1Pawn1.setPos(p1Pawn2.getPos());
        pieces.add(p1Pawn1);
        pieces.add(p1Pawn2);
        game.setPieces(pieces);
        System.out.println("Try to move to same pos as friendly piece: " + (p1Pawn1.checkLogicNoEffects(game) == false));

        p1Pawn1.revert();

        // Move to same pos vertically as hostile piece
        p1Pawn1.setPos(new Point(4,4));
        p1Pawn1.update();
        p1Pawn1.setPos(new Point(4,5));
        p2Pawn1.setPos(new Point(4,5));
        pieces.clear();
        pieces.add(p1Pawn1);
        pieces.add(p2Pawn1);
        game.setPieces(pieces);
        System.out.println("Try to move piece vertically to hostile piece: " + (p1Pawn1.checkLogicNoEffects(game) == false));

        // Capture  Moves
        p1Pawn1.setPos(new Point(4,4));
        p1Pawn1.update();
        p2Pawn1.setPos(new Point(5,5));
        p2Pawn2.update();

        p1Pawn1.setPos(new Point(5,5));
        pieces.clear();
        pieces.add(p1Pawn1);
        pieces.add(p2Pawn1);
        game.setPieces(pieces);
        System.out.println("Capture diagonally: " + (p1Pawn1.checkLogicNoEffects(game) == true));

        // Collision "Jumping over a piece"
        p1Pawn1 = new Pawn(new Point(2,2),new Player(1));
        p1Pawn2 = new Pawn(new Point(2,3),new Player(1));
        p1Pawn1.setPos(new Point(2,4));
        pieces.clear();
        pieces.add(p1Pawn1);
        pieces.add(p1Pawn2);
        game.setPieces(pieces);
        System.out.println("Cant jump over piece: " + (p1Pawn1.checkLogicNoEffects(game) == false));


        // Player 2 tests
        // Test moving backwards (invalid move for Player 2)
        p2Pawn1 = new Pawn(new Point(7,6),new Player(2));
        p2Pawn1.setPos(new Point(7, 6));
        pieces.add(p2Pawn1);
        System.out.println("Cant move backwards: " + (p2Pawn1.checkLogicNoEffects(game) == false));

        // Test moving horizontally (invalid move for Player 2)
        p2Pawn1.revert();
        p2Pawn1.setPos(new Point(6, 7));
        pieces.clear();
        pieces.add(p2Pawn1);
        System.out.println("Cant move vertically: " + (p2Pawn1.checkLogicNoEffects(game) == false));

        // Test initial 2-move capability (valid move for Player 2)
        p2Pawn1 = new Pawn(new Point(7, 7), new Player(2));
        p2Pawn1.setPos(new Point(7, 5));
        pieces.add(p2Pawn1);
        game.setPieces(pieces);
        System.out.println("Initial 2 move: " + (p2Pawn1.checkLogic(game) == true));
        p2Pawn1.update();
        p2Pawn1.setPos(new Point(7, 3));
        game.setPieces(pieces);
        System.out.println("2 move after initial: " + (p2Pawn1.checkLogicNoEffects(game) == false));
        p2Pawn1.revert();

        // Test moving twice after initial move (invalid move for Player 2)
        p2Pawn1 = new Pawn(new Point(7, 7), new Player(2));
        p2Pawn1.setPos(new Point(7, 6));
        p2Pawn1.isValidMove();
        p2Pawn1.update();
        p2Pawn1.setPos(new Point(7, 4));
        pieces.clear();
        pieces.add(p2Pawn1);
        game.setPieces(pieces);
        System.out.println("2 move after initial 1 move: " + (p2Pawn1.checkLogicNoEffects(game) == false));

        // Move to same pos as friendly piece (invalid move for Player 2)
        p2Pawn1 = new Pawn(new Point(7, 7), new Player(2));
        p2Pawn1.setPos(p2Pawn2.getPos());
        pieces.add(p2Pawn1);
        pieces.add(p2Pawn2);
        game.setPieces(pieces);
        System.out.println("Try to move to same pos as friendly piece: " + (p2Pawn1.checkLogicNoEffects(game) == false));

        // Move to same pos vertically as hostile piece (invalid move for Player 2)
        p2Pawn1.setPos(new Point(5, 5));
        p2Pawn1.update();
        p2Pawn1.setPos(new Point(5, 4));
        p1Pawn2.setPos(new Point(5, 4));
        pieces.clear();
        pieces.add(p2Pawn1);
        pieces.add(p1Pawn2);
        game.setPieces(pieces);
        System.out.println("Try to move piece vertically to hostile piece: " + (p2Pawn1.checkLogicNoEffects(game) == false));

        // Capture Moves (valid move for Player 2)
        p2Pawn1.setPos(new Point(5, 5));
        p2Pawn1.update();
        p1Pawn2.setPos(new Point(4, 4));
        p1Pawn1.setPos(new Point(4, 4));
        p1Pawn2.update();

        p2Pawn1.setPos(new Point(4, 4));
        pieces.clear();
        pieces.add(p2Pawn1);
        pieces.add(p1Pawn2);
        pieces.add(p1Pawn1);
        game.setPieces(pieces);
        System.out.println("Capture diagonally: " + (p2Pawn1.checkLogicNoEffects(game) == true));


        // Collision "Jumping over a piece" - Player 2
        p2Pawn1 = new Pawn(new Point(7, 7), new Player(2));
        p2Pawn2 = new Pawn(new Point(7, 6), new Player(2));
        p2Pawn1.setPos(new Point(7, 5));
        pieces.clear();
        pieces.add(p2Pawn1);
        pieces.add(p2Pawn2);
        game.setPieces(pieces);
        System.out.println("Cant jump over piece: " + (p2Pawn1.checkLogicNoEffects(game) == false));

    }
}
