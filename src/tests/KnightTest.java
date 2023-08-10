package tests;

import assets.Piece;
import assets.pieces.Knight;
import assets.pieces.Pawn;
import game.Game;
import game.Player;

import java.awt.*;
import java.util.ArrayList;

public class KnightTest {
    public void test() {
        Game game = new Game();
        ArrayList<Piece> pieces = new ArrayList<>();

        Knight knight = new Knight(new Point(4, 4), new Player(1));
        pieces.add(knight);
        game.setPieces(pieces);

        // Test moving in an L-shape
        knight.setPos(new Point(2, 3));
        System.out.println("Can move in L-shape: " + (knight.checkLogicNoEffects(game) == true));

        // Test valid capture move
        Pawn pawn = new Pawn(new Point(2, 3), new Player(2));
        pieces.add(pawn);
        game.setPieces(pieces);
        knight.revert();
        knight.setPos(new Point(2, 3));
        System.out.println("Can capture: " + (knight.checkLogicNoEffects(game) == true));

        // Test jumping over pieces
        pieces.clear();
        Pawn blockingPawn = new Pawn(new Point(3, 3), new Player(1));
        Pawn targetPawn = new Pawn(new Point(1, 2), new Player(2));
        pieces.add(knight);
        pieces.add(blockingPawn);
        pieces.add(targetPawn);
        game.setPieces(pieces);
        System.out.println("Can jump over piece: " + (knight.checkLogicNoEffects(game) == true));

        // Test invalid move
        knight.revert();
        knight.setPos(new Point(5, 4));
        System.out.println("Cant move to invalid position: " + (knight.checkLogicNoEffects(game) == false));
    }

}
