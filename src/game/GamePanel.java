package game;

import assets.Movable;
import assets.Tile;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GamePanel extends JPanel {
    private final ArrayList<Movable> movables;
    private final ArrayList<Tile> chessBoard;
    private Player p1,p2;
    private Player activePlayer;

    public GamePanel(ArrayList<Tile> chessBoard, Player p1, Player p2) {
        // All pieces in the game
        movables = new ArrayList<>();
        this.chessBoard = chessBoard;
        this.p1 = p1;
        this.p2 = p2;
        this.activePlayer = p1;
        setPreferredSize(new Dimension(1000, 1000));

        // Text above chessboard
        JLabel title = new JLabel("Chess");
        Font font = new Font("Planc", Font.BOLD,40);
        title.setFont(font);
        add(title);
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
        // Draw players
        Font boldFont = new Font("Planc", Font.BOLD, 20);
        g.setFont(boldFont);
        g.drawString("Black  " + p1.getRemainingTimeAsString() ,50,50);
        g.drawString("White  " + p2.getRemainingTimeAsString(), 50,950);
        g.setColor(Color.GREEN);
        g.fillRect(10,this.activePlayer.getNum() == 1 ? 30 : 930,25,25);

        // Draw piece names
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
        this.activePlayer = player;
    }
}