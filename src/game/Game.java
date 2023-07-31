package game;

import assets.Piece;
import assets.Tile;
import assets.pieces.Bishop;
import assets.pieces.King;
import assets.pieces.Queen;

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

        // Test pieces
        Piece test = new King(new Point(1,1), p1);
        Piece test2 = new King(new Point(1,2), p2);
        Piece bishop = new Bishop(new Point(2,3),p2);
        panel.addPositionable(test);
        panel.addPositionable(test2);
        panel.addPositionable(bishop);
        pieces.add(test);
        pieces.add(test2);
        pieces.add(bishop);
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
