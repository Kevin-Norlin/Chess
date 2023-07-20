package main;

import assets.Piece;
import assets.pieces.King;

public class Game {
    private GameWindow window;
    private GamePanel panel;
    public Game() {
        this.panel = new GamePanel();
        this.window = new GameWindow(panel);

        //
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
