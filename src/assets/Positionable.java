package assets;

import constants.Constants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

// Superclass for all components
public abstract class Positionable extends JComponent {
    private int x, y, width, height;
    private Point pos;

    // X,Y position is position on the PANEL
    // POINT position is position on the CHESSBOARD (size 8x8)
    // setX() and setY() also sets the appropriate pos on the chessboard.
    // setPos() also sets the appropriate x and y values on the panel.
    public Positionable(int x, int y, int width, int height, Point pos) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.pos = pos;
        setOpaque(false); // Allow mouse events to be detected
        setBounds(x, y, width, height); // Set the bounds of the component
        setBorder(BorderFactory.createTitledBorder(this.getClass().getName().substring(this.getClass().getName().lastIndexOf('.') + 1))); // Show the actual border

    }
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
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
    public Point getPos() {
        return this.pos;
    }

    public void setX(int x) {
        this.x = x;
        this.pos.x = (int) Math.ceil(x - 25) / Constants.SIZE;
    }
    public void setY(int y) {
        this.y = y;
        this.pos.y = (int) Math.ceil(y - 25) / Constants.SIZE;
    }

    public void setPos(Point p) {
        this.pos = p;
        this.x = (p.x * Constants.SIZE) + 25;
        this.y = (p.y * Constants.SIZE) + 25;
    }


}
