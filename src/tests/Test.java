package tests;



// Tests for all pieces, the results are printed
public class Test {

    public void runAllTests() {
        PawnTest pawnTest = new PawnTest();
        RookTest rookTest = new RookTest();
        QueenTest queenTest = new QueenTest();
        KingTest kingTest = new KingTest();
        BishopTest bishopTest = new BishopTest();
        KnightTest knightTest = new KnightTest();
        pawnTest.test();
        rookTest.test();
        queenTest.test();
        kingTest.test();
        bishopTest.test();
        knightTest.test();
    }

}
