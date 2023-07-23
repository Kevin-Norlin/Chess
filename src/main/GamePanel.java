package main;

import assets.Positionable;
import assets.Tile;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class GamePanel extends JPanel {
    private final ArrayList<Positionable> positionables;
    private final ArrayList<Tile> chessBoard;
    public GamePanel(ArrayList<Tile> chessBoard) {
        // All pieces in the game
        positionables = new ArrayList<>();
        this.chessBoard = chessBoard;
    }
    public void addPositionable(Positionable positionable) {
        positionables.add(positionable);
        add(positionable); // Add the Positionable object as a child component to the GamePanel
    }
    public void removePositionable(Positionable positionable) {
        positionables.remove(positionable);
        remove(positionable);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        /*
        // Draw a 8x8 chess board, implement with mod?
        int size = 100;
        Color lightColor = new Color(0xF0D9B5);
        Color darkColor = new Color(0xB58863);

        for (int r = size; r <= size * 8; r += size) {
            for (int c = size; c <= size * 8; c += size) {
                if ((r + c) / size % 2 == 0) {
                    g.setColor(lightColor);
                } else {
                    g.setColor(darkColor);
                }
                g.fillRect(r, c, size, size);
            }
        }
        */

        // Draw chessBoard
        for (Tile t : chessBoard) {
            t.paintComponent(g);
        }

        // Draw all pieces
        for (Positionable p : positionables) {
            p.paintComponent(g);
        }







    }
}
