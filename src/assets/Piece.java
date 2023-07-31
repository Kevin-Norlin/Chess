package assets;

import game.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Piece extends Movable {

    private final Player player;
    public Piece(Point pos, Player player) {
        super(pos);
        this.player = player;
    }
    // Code responsible for drawing the piece
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (this.player.getNum() == 1) {
            g.setColor(Color.blue);
        } else {
            g.setColor(Color.red);
        }
        g.fillRect(this.getX(), this.getY(), getWidth(), getHeight());
    }
    public boolean isValidMove() {
        return true;
    }
    public Player getPlayer() {
        return this.player;
    }





}
