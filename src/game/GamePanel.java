package game;

import assets.Movable;
import assets.Tile;
import constants.Constants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class GamePanel extends JPanel {
    private final ArrayList<Movable> movables;
    private final ArrayList<Tile> chessBoard;
    private final ArrayList<Point> validMoves;
    private final Game game;
    private Player p1,p2;
    private Player activePlayer;
    private boolean check, checkMate;


    public GamePanel(Game game, ArrayList<Tile> chessBoard, Player p1, Player p2) {
        // All pieces in the game
        movables = new ArrayList<>();
        validMoves = new ArrayList<>();
        this.chessBoard = chessBoard;
        this.game = game;
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
        if (checkMate) {
            this.removeAll();
            Graphics2D g2d = (Graphics2D) g;

            String text = activePlayer.getNum() == 1 ? "White won" : "Black won";
            Font font = new Font("Planc", Font.BOLD, 60);
            g2d.setFont(font);

            FontMetrics fm = g2d.getFontMetrics();
            int textWidth = fm.stringWidth(text);
            int textHeight = fm.getHeight();

            int x = (getWidth() - textWidth) / 2;
            int y = (getHeight() + textHeight) / 2 - textHeight; // Adjust y position

            g2d.drawString(text, x, y);

        }
        else {
            // Draw players
            Font boldFont = new Font("Planc", Font.BOLD, 20);
            g.setFont(boldFont);
            g.drawString("Black  " + p1.getRemainingTimeAsString(), 50, 50);
            g.drawString("White  " + p2.getRemainingTimeAsString(), 50, 950);
            g.setColor(Color.GREEN);
            g.fillRect(10, this.activePlayer.getNum() == 1 ? 30 : 930, 25, 25);

            // Draw piece names
            Font smallFont = new Font("Planc", Font.CENTER_BASELINE, 12);
            g.setFont(smallFont);
            // Draw chessBoard
            for (Tile t : chessBoard) {
                t.paintComponent(g);
            }

            // Draw all pieces
            for (Movable m : movables) {
                m.paintComponent(g);
            }
            if (validMoves.size() > 0) {
                for (Point p : validMoves) {
                    g.setColor(Color.GREEN);
                    g.fillRect((p.x * Constants.SIZE) + 25, (p.y * Constants.SIZE) + 25, 5, 5);
                }
            }
            if (check) {
                g.setColor(Color.GREEN);
                g.setFont(new Font("Planc", Font.BOLD, 20));
                g.drawString("Check", 920, 500);
            }
        }
    }
    public void displayPlayer(Player player) {
        this.activePlayer = player;
    }
    public void setValidMoves(ArrayList<Point> moves) {
        this.validMoves.clear();
        this.validMoves.addAll(moves);
    }
    public void clearValidMoves() {
        this.validMoves.clear();
    }
    public void setCheck() {
        this.check = true;
        this.repaint();
    }
    public void clearCheck() {
        this.check = false;
        this.repaint();
    }
    public void setCheckMate(Player player) {
        this.checkMate = true;
        this.activePlayer = player;
    }

}