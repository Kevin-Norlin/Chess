package game;

import assets.Movable;
import assets.Tile;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GamePanel extends JPanel {
    private final ArrayList<Movable> movables;
    private final ArrayList<Tile> chessBoard;
    private Player player;

    public GamePanel(ArrayList<Tile> chessBoard) {
        // All pieces in the game
        movables = new ArrayList<>();
        this.chessBoard = chessBoard;
        this.player = new Player(1);

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
        // Draw player
        Font boldFont = new Font("Planc", Font.BOLD, 20);
        g.setFont(boldFont);
        g.drawString(this.player.getNum() == 1 ? "Player 1" : "Player 2",50,50);
        Font smallFont = new Font("Planc",Font.CENTER_BASELINE, 12);
        g.setFont(smallFont);
        // Draw chessBoard
        for (Tile t : chessBoard) {
            t.paintComponent(g);
        }

        // Draw all pieces

        for (Movable m : movables) {
            m.paintComponent(g);
        }
    }
    public void displayPlayer(Player player) {
        this.player = player;
    }
}