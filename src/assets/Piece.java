package assets;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Piece extends Movable {

    private final int player;
    public Piece(Point p, int width, int height, int player) {
        super(p, width, height);
        this.player = player;
    }
    // Code responsible for drawing the piece
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (this.player == 1) {
            g.setColor(Color.blue);
        } else {
            g.setColor(Color.red);
        }
        g.fillRect(this.getX(), this.getY(), getWidth(), getHeight());
    }


}
