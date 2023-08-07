package assets;

import game.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class Piece extends Movable {

    private final Player player;
    public Piece(Point pos, Player player, String imgPath) {
        super(pos, imgPath);
        this.player = player;
    }
    // Code responsible for drawing the piece
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
    }
    public boolean isValidMove() {
        return true;
    }
    public Player getPlayer() {
        return this.player;
    }
    public boolean collisionInPath(ArrayList<Piece> pieces) {
        return false;
    }





}
