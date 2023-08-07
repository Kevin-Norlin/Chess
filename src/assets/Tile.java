package assets;

import javax.swing.*;
import java.awt.*;

import static constants.Constants.TILE_COLOR_LIGHT;


public class Tile extends JComponent {
    private int x,y,width, height;
    private Color color;
    private Piece piece;

    // Can initiate object w or w/o piece tied to the Tile
    public Tile(int x, int y, int width, int height, Piece piece, Color color) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.piece = piece;
        this.color = color;
    }
    public Tile(int x, int y, int width, int height, Color color) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.color = color;
    }


    @Override
    public void paintComponent(Graphics g) {
        g.setColor(this.color);
        g.fillRect(getX(),getY(),getWidth(),getHeight());
        if (this.piece != null) {
            g.drawString(this.piece.getName(), this.getX(), this.getY());
        }
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
    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    // Check if a piece is in bounds of a Specific tile
    public boolean checkTile(Piece p) {
        if (this.getX() <= p.getX() && (this.getX() + this.getWidth()) >= p.getX()
            && this.getY() <= p.getY() && (this.getY() + this.getHeight()) >= p.getY()) {
            return true;
        }
        return false;
    }
    public boolean checkTile2() {
        return false;
    }
    public void clearTile() {
        setPiece(null);
    }

}
