package game;

import assets.Piece;
import assets.Tile;
import assets.pieces.*;

import java.awt.*;
import java.util.ArrayList;
import static constants.Constants.*;

public class Game {
    private GameWindow window;
    private GamePanel panel;
    private ArrayList<Tile> chessBoard;
    private ArrayList<Piece> pieces;

    public Game() {
        Player p1 = new Player(1);
        Player p2 = new Player(2);

        chessBoard = new ArrayList<>();
        pieces = new ArrayList<>();
        int size = SIZE;

        // Fill the list with all tiles in the chessBoard
        for (int r = size; r <= size * 8; r += size) {
            for (int c = size; c <= size * 8; c += size) {
                if ((r + c) / size % 2 == 0) {
                    chessBoard.add(new Tile(r,c,size,size,TILE_COLOR_LIGHT));
                } else {
                    chessBoard.add(new Tile(r,c,size,size,TILE_COLOR_DARK));
                }
            }
        }
        this.panel = new GamePanel(chessBoard);
        this.window = new GameWindow(panel);
        
        // Create pieces for Player 1 (p1) and add them to the panel and pieces array
        Piece kingP1 = new King(new Point(5, 1), p1);
        panel.addPositionable(kingP1);
        pieces.add(kingP1);

        Piece queenP1 = new Queen(new Point(4, 1), p1);
        panel.addPositionable(queenP1);
        pieces.add(queenP1);

        Piece rook1P1 = new Rook(new Point(1, 1), p1);
        panel.addPositionable(rook1P1);
        pieces.add(rook1P1);

        Piece rook2P1 = new Rook(new Point(8, 1), p1);
        panel.addPositionable(rook2P1);
        pieces.add(rook2P1);

        Piece knight1P1 = new Knight(new Point(2, 1), p1);
        panel.addPositionable(knight1P1);
        pieces.add(knight1P1);

        Piece knight2P1 = new Knight(new Point(7, 1), p1);
        panel.addPositionable(knight2P1);
        pieces.add(knight2P1);

        Piece bishop1P1 = new Bishop(new Point(3, 1), p1);
        panel.addPositionable(bishop1P1);
        pieces.add(bishop1P1);

        Piece bishop2P1 = new Bishop(new Point(6, 1), p1);
        panel.addPositionable(bishop2P1);
        pieces.add(bishop2P1);

        for (int col = 1; col <= 8; col++) {
            Piece pawnP1 = new Pawn(new Point(col, 2), p1);
            panel.addPositionable(pawnP1);
            pieces.add(pawnP1);
        }

        // Create pieces for Player 2 (p2) and add them to the panel and pieces array
        Piece kingP2 = new King(new Point(5, 8), p2);
        panel.addPositionable(kingP2);
        pieces.add(kingP2);

        Piece queenP2 = new Queen(new Point(4, 8), p2);
        panel.addPositionable(queenP2);
        pieces.add(queenP2);

        Piece rook1P2 = new Rook(new Point(1, 8), p2);
        panel.addPositionable(rook1P2);
        pieces.add(rook1P2);

        Piece rook2P2 = new Rook(new Point(8, 8), p2);
        panel.addPositionable(rook2P2);
        pieces.add(rook2P2);

        Piece knight1P2 = new Knight(new Point(2, 8), p2);
        panel.addPositionable(knight1P2);
        pieces.add(knight1P2);

        Piece knight2P2 = new Knight(new Point(7, 8), p2);
        panel.addPositionable(knight2P2);
        pieces.add(knight2P2);

        Piece bishop1P2 = new Bishop(new Point(3, 8), p2);
        panel.addPositionable(bishop1P2);
        pieces.add(bishop1P2);

        Piece bishop2P2 = new Bishop(new Point(6, 8), p2);
        panel.addPositionable(bishop2P2);
        pieces.add(bishop2P2);

        for (int col = 1; col <= 8; col++) {
            Piece pawnP2 = new Pawn(new Point(col, 7), p2);
            panel.addPositionable(pawnP2);
            pieces.add(pawnP2);
        }
    }

    public void startGame() {
        // Game loop
        while (true) {

            boolean pieceFound;
            panel.repaint();

            // Check all tiles. Set pieces to the tiles they are over and clear the ones that have no piece.
            for (Tile t: chessBoard) {
                pieceFound = false;
                for (Piece p : pieces) {
                    if (t.checkTile(p)) {
                        t.setPiece(p);
                        pieceFound = true;
                    }
                }
                if (!pieceFound) {
                    t.clearTile();
                }
            }
            for (Piece p : pieces) {
                if (p.hasMoved() && p.isValidMove()) {
                    p.update();
                }
                if (p.hasMoved() && !p.isValidMove()) {
                    p.revert();
                }
            }
            //System.out.println(pieces.get(2).isValidMove());  //+ " Pos: " + pieces.get(2).getPos() + " " + "prevPos: " + pieces.get(2).getPrevPos());

        }
    }
}
