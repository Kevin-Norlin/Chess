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

    // Code responsible for drawing the piece
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
    }

    public boolean isValidMove() {
        return true;
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

    // Checks for collision vertically and horizontally between 2 positions
    public boolean collisionInPathVertHor(ArrayList<Piece> pieces) {
        int startX = this.getPrevPos().x;
        int startY = this.getPrevPos().y;
        int endX = this.getPos().x;
        int endY = this.getPos().y;
        Player player = this.getPlayer();
        for (Piece piece : pieces) {
            // Skip the current piece in the loop
            if (piece.equals(this) || piece.getPos().equals(this.getPos()) || piece.getPos().equals(this.getPrevPos())) {
                continue;
            }
            int pieceStartX = piece.getPrevPos().x;
            int pieceStartY = piece.getPrevPos().y;
            int pieceEndX = piece.getPos().x;
            int pieceEndY = piece.getPos().y;
            Player piecePlayer = piece.getPlayer();
            // Check if there is a piece on the same column as this and the rook is moving vertically
            if (startX == endX && startX == pieceStartX) {
                if ((startY <= pieceStartY && endY >= pieceStartY) || (startY >= pieceStartY && endY <= pieceStartY)) {
                    // If the collision is on the end position and the player is of the other team, return true
                    if (endY == pieceEndY && !player.equals(piecePlayer)) {
                        // Capture opponent's piece
                        continue;
                    }
                    return true; // Collision without capture
                }
            }
            // Check if there is a piece on the same row as this and the rook is moving horizontally
            if (startY == endY && startY == pieceStartY) {
                if ((startX <= pieceStartX && endX >= pieceStartX) || (startX >= pieceStartX && endX <= pieceStartX)) {
                    // If the collision is on the end position and the player is of the other team, return true
                    if (endX == pieceEndX && !player.equals(piecePlayer)) {
                        // Capture opponent's piece
                        continue;
                    }
                    return true; // Collision without capture
                }
            }
        }
        return false; // No collision
    }

    // Checks for collision diagonally between 2 positions
    public boolean collisionInPathDiag(ArrayList<Piece> pieces) {
        int startX = this.getPrevPos().x;
        int startY = this.getPrevPos().y;
        int endX = this.getPos().x;
        int endY = this.getPos().y;
        Player player = this.getPlayer();
        for (Piece piece : pieces) {
            // Skip the current piece in the loop
            if (piece.equals(this) || piece.getPos().equals(this.getPos()) || piece.getPos().equals(this.getPrevPos())) {
                continue;
            }
            int pieceStartX = piece.getPrevPos().x;
            int pieceStartY = piece.getPrevPos().y;
            int pieceEndX = piece.getPos().x;
            int pieceEndY = piece.getPos().y;
            Player piecePlayer = piece.getPlayer();
            if (Math.abs(startX - endX) == Math.abs(startY - endY)) {
                if (Math.abs(pieceStartX - pieceEndX) == Math.abs(pieceStartY - pieceEndY)) {
                    int dx = (startX < endX) ? 1 : -1;
                    int dy = (startY < endY) ? 1 : -1;
                    int x = startX + dx;
                    int y = startY + dy;
                    while (x != endX && y != endY) {
                        if (x == pieceStartX && y == pieceStartY) {
                            // If there is a piece blocking the diagonal path, return true (collision)
                            return true;
                        }
                        x += dx;
                        y += dy;
                    }
                    // If there is a piece at the end position and the player is of the other team, return false (valid capture)
                    if (endX == pieceEndX && endY == pieceEndY && !player.equals(piecePlayer)) {
                        continue;
                    }
                }
            }
        }
        return false; // No collision or valid capture
    }

    public Player getPlayer() {
        return this.player;
    }

}
