package assets;

import constants.Constants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;


public abstract class Movable extends Positionable implements MouseListener, MouseMotionListener {
    /*
    This class handles the Listener events,
    note that the logic uses x and y values
    on the panel and not the position on the chessboard.
     */
    private Point prevPos;
    private Point initialClick;
    public Movable(Point pos) {
        super(pos.x * Constants.SIZE + 25, pos.y * Constants.SIZE + 25, Constants.PIECE_SIZE, Constants.PIECE_SIZE, pos);
        this.prevPos = pos;
        addMouseListener(this); // Register the mouse listener
        addMouseMotionListener(this);
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
    // Handles snapping
    public void mouseReleased(MouseEvent e) {
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
        this.setX(xTile);
        this.setY(yTile);
        this.setLocation(xTile,yTile); // This is for the listener-events!
        System.out.println(this.getPos());

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

}



