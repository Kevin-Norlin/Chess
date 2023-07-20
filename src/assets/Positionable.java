package assets;

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
        setBorder(BorderFactory.createTitledBorder("Positionable")); // Show the actual border
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
        System.out.println("x: " + this.x + ", y: " + this.y);


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
        this.setLocation(x,y);


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
