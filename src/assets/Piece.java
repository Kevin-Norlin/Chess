package assets;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Piece extends Positionable {

    public Piece(int x, int y, int width, int height) {
        super(x, y, width, height);
    }
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.blue);
        g.fillRect(getX(),getY(),getWidth(),getHeight());

    }

}
