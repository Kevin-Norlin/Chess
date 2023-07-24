package main;

import assets.Piece;
import assets.Tile;
import assets.pieces.King;

import java.awt.*;
import java.util.ArrayList;
import static constants.Constants.*;

public class Game {
    private GameWindow window;
    private GamePanel panel;
    private ArrayList<Tile> chessBoard;
    private ArrayList<Piece> pieces;
    public Game() {
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

        // Test pieces
        Piece test = new King(new Point(1,1), 50,50, 1);
        Piece test2 = new King(new Point(1,2), 50,50, 2);
        panel.addPositionable(test);
        panel.addPositionable(test2);

        pieces.add(test);
        pieces.add(test2);
    }

    public void startGame() {
        // Gameloop
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
        }
    }
}
