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
        for (Piece p: pieces) {
            if (p.equals(this)) {
                continue;
            }
            // If the piece tries to move to the same tile as another friendly piece
            if (this.getPos().x == p.getPos().x && this.getPos().y == p.getPos().y && this.getPlayer().equals(p.getPlayer())) {
                return true;
            }
        } return false;
    }






}
