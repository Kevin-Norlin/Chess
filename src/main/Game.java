package main;

import assets.Piece;
import assets.Tile;
import assets.pieces.King;

import java.util.ArrayList;

import static constants.Constants.TILE_COLOR_DARK;
import static constants.Constants.TILE_COLOR_LIGHT;

public class Game {
    private GameWindow window;
    private GamePanel panel;
    public Game() {
        int size = 100;

        ArrayList<Tile> chessBoard = new ArrayList<>();
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

        Piece test = new King(125,125,50,50, 1);
        Piece test2 = new King(225,125, 50,50, 2);



        panel.addPositionable(test);
        panel.addPositionable(test2);
    }

    public void startGame() {
        // Initialize the game


        while (true) {
            panel.repaint();


        }
    }
}
