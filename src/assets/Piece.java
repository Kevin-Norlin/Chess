package assets;

import assets.pieces.*;
import game.Game;
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
    public Piece(Piece p) {
        super(p.getPos(),p.getImgPath());
        this.player = p.player;
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
    @Override
    public ArrayList<Point> generateLegalMoves(Game g) {
        Piece p = null;
        Point currentPos = this.getPos();
        ArrayList<Point> legalMoves = new ArrayList<>();
        // Create copy of p with correct subtype, kinda hacky
        if (this instanceof Bishop) {
            p = new Bishop(currentPos, this.getPlayer());
        } else if (this instanceof King) {
            p = new King(currentPos, this.getPlayer());
        } else if (this instanceof Knight) {
            p = new Knight(currentPos, this.getPlayer());
        } else if (this instanceof Pawn) {
            p = new Pawn(currentPos, this.getPlayer());
            if (!((Pawn) this).getFirstMove()) {
                ((Pawn) p).setFirstMove();
            }
        } else if (this instanceof Queen) {
            p = new Queen(currentPos, this.getPlayer());
        } else if (this instanceof Rook) {
            p = new Rook(currentPos, this.getPlayer());
        }
        for (int r = 1; r <= 8; r++) {
            for (int c = 1; c <= 8; c++) {
                Point checkPos = new Point(r,c);
                if (checkPos.equals(currentPos)) {
                    continue;
                }
                // Setting pos to all possible positions
                p.setPos(checkPos);
                if (p.checkLogicNoEffects(g)) {
                    legalMoves.add(checkPos);
                }
                p.setPrevPos(currentPos);
                p.setPos(currentPos);

            }
        } return legalMoves;
    }
    // Checks all the necessary logic
    public boolean checkLogic(Game g) {
        if (this.isValidMove()) {
            if (collisionInPath(g.getPieces()) || (g.pieceInSamePos(this) != null && g.pieceInSamePos(this).getPlayer().equals(this.getPlayer()))) {
                return false;
            }
            for (Piece p: g.getPieces()) {
                if (p.equals(this)) {
                    continue;
                } if (p.getPos().equals(this.getPos()) && !this.getPlayer().equals(p.getPlayer())) {
                    // If its a valid capture move
                    g.setpToRemove(p);
                    return true;
                }
            }
            return true;
        }
        return false;
    }
    // Checks all the necessary logic with no side effects
    public boolean checkLogicNoEffects(Game g) {
        if (this.isValidMove()) {
            if (collisionInPath(g.getPieces()) || (g.pieceInSamePos(this) != null && g.pieceInSamePos(this).getPlayer().equals(this.getPlayer()))) {
                return false;
            }
            for (Piece p: g.getPieces()) {
                if (p.equals(this)) {
                    continue;
                } if (p.getPos().equals(this.getPos()) && !this.getPlayer().equals(p.getPlayer())) {
                    // If its a valid capture move
                    return true;
                }
            }
            return true;
        }
        return false;
    }









}
