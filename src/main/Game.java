package main;

import assets.Piece;

public class Game {
    private GameWindow window;
    private GamePanel panel;
    public Game() {
        this.panel = new GamePanel();
        this.window = new GameWindow(panel);

        Piece test = new Piece(50,50,50,50);
        panel.addPositionable(test);
    }

    public void startGame() {
        // Initialize the game


        while (true) {
            panel.repaint();


        }
    }
}
