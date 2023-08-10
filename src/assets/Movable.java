package assets;

import constants.Constants;
import game.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;


public abstract class Movable extends Positionable implements MouseListener, MouseMotionListener {
    /*
    This class handles the Listener events,
    note that the logic uses x and y values
    on the panel and not the position on the chessboard.
     */
    private Point initialClick;
    private boolean isActive;
    private boolean hasMoved;
    public Movable(Point pos, String imgPath) {
        super(pos.x * Constants.SIZE + 25, pos.y * Constants.SIZE + 25, Constants.PIECE_SIZE, Constants.PIECE_SIZE, pos, imgPath);
        this.hasMoved = false;
        this.isActive = false;
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
        isActive = true;
        initialClick = e.getPoint(); // Store the initial click position
    }


    @Override
    // Handles snapping
    public void mouseReleased(MouseEvent e) {
        isActive = false;
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
        if (this.getPos() != this.getPrevPos()) {
            this.hasMoved = true;
        }


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
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
    }

    public void revert(){
        setPos(this.getPrevPos());
        this.hasMoved = false;
        this.setLocation((this.getPrevPos().x * Constants.SIZE) + 25, (this.getPrevPos().y * Constants.SIZE) + 25); // This is for the listener-events!
    }
    public void update(Game g){
        this.hasMoved = false;
        setPrevPos(this.getPos());
        g.toggleEvent();
    }
    public void update(){
        this.hasMoved = false;
        setPrevPos(this.getPos());
    }
    public boolean hasMoved() {
        return this.hasMoved;
    }
    public void clearHasMoved() {
        this.hasMoved = false;
    }
    public ArrayList<Point> generateLegalMoves(Game g) {
        return new ArrayList<>();
    }
    public boolean getIsActive() {
        return this.isActive;
    }
    public void setIsActive(boolean bol) {
        this.isActive = bol;
    }


}



