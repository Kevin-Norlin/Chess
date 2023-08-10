package tests;

import assets.Piece;
import assets.pieces.Pawn;
import game.Game;
import game.Player;

import java.awt.*;
import java.util.ArrayList;

// Tests for all pieces, the results are printed
public class Test {

    public void runAllTests() {
        PawnTest pawnTest = new PawnTest();
        RookTest rookTest = new RookTest();
        QueenTest queenTest = new QueenTest();
        //pawnTest.test();
        //rookTest.test();
        queenTest.test();
    }

}
