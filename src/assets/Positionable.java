package assets;

import constants.Constants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

// Class for all positionables
public abstract class Positionable extends JComponent implements MouseListener, MouseMotionListener {
    private int x, y, width, height;
    private Point initialClick;

    public Positionable(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;


        setOpaque(false); // Allow mouse events to be detected
        setBounds(x, y, width, height); // Set the bounds of the component
        setBorder(BorderFactory.createTitledBorder(this.getClass().getName().substring(this.getClass().getName().lastIndexOf('.') + 1))); // Show the actual border
        addMouseListener(this); // Register the mouse listener
        addMouseMotionListener(this);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println("Mouse clicked");
    }

    @Override
    public void mousePressed(MouseEvent e) {
        System.out.println("Mouse pressed");
        initialClick = e.getPoint(); // Store the initial click position
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        System.out.println("Released");
        int xTile = 125;
        int yTile = 125;
        int minDiff = 1000;

        //Snapping logic for x pos
        for (int i = 0; i < Constants.TILES.length; i++) {
            if (Math.abs(Constants.TILES[i] - this.getX()) < minDiff) {
                minDiff = Math.abs(Constants.TILES[i] - this.getX());
                xTile = Constants.TILES[i];
            }
        }
        minDiff = 1000; // Reset it.
        // Snapping logic for y pos
        for (int i = 0; i < Constants.TILES.length; i++) {
            if (Math.abs(Constants.TILES[i] - this.getY()) < minDiff) {
                minDiff = Math.abs(Constants.TILES[i] - this.getY());
                yTile = Constants.TILES[i];
            }
        }
        System.out.println(xTile);
        // Set the new position for the box
        this.setX(xTile);
        this.setY(yTile);
        this.setLocation(xTile,yTile); // This is for the listener-events!


    }

    @Override
    public void mouseEntered(MouseEvent e) {
        System.out.println("Mouse entered");
    }

    @Override
    public void mouseExited(MouseEvent e) {
        System.out.println("Mouse exited");
    }
    @Override
    public void mouseDragged(MouseEvent e) {
        int x = this.getLocation().x + e.getX() - initialClick.x;
        int y = this.getLocation().y + e.getY() - initialClick.y;

        // Set the new position for the box
        this.setX(x);
        this.setY(y);
        this.setLocation(x,y); // This is for the listener-events!
    }
    @Override
    public void mouseMoved(MouseEvent e) {
    }
    public int getX() {
        return this.x;
    }
    public int getY() {
        return this.y;
    }
    public int getWidth() {
        return this.width;
    }
    public int getHeight() {
        return this.height;
    }
    public void setX(int x) {
        this.x = x;
    }
    public void setY(int y) {
        this.y = y;
    }
}
