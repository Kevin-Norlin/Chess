package game;

import assets.Movable;
import assets.Tile;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GamePanel extends JPanel {
    private final ArrayList<Movable> movables;
    private final ArrayList<Tile> chessBoard;
    public GamePanel(ArrayList<Tile> chessBoard) {
        // All pieces in the game
        movables = new ArrayList<>();
        this.chessBoard = chessBoard;
    }
    public void addPositionable(Movable m) {
        movables.add(m);
        add(m); // Add the Positionable object as a child component to the GamePanel
    }
    public void removePositionable(Movable m) {
        movables.remove(m);
        remove(m);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Draw chessBoard
        for (Tile t : chessBoard) {
            t.paintComponent(g);
        }

        // Draw all pieces
        for (Movable m : movables) {
            m.paintComponent(g);
        }







    }
}
