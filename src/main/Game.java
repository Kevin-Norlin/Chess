package main;

import assets.Piece;

public class Game {
    private GameWindow window;
    private GamePanel panel;
    public Game() {
        this.panel = new GamePanel();
        this.window = new GameWindow(panel);

        Piece test = new Piece(50,50,50,50);
        Piece test2 = new Piece(150,50, 50,50);
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
