package assets.pieces;

import assets.Piece;
import game.Player;

import java.awt.*;
import java.util.ArrayList;

// "Torn"
public class Rook extends Piece {
    public Rook(Point p, Player player) {
        super(p, player, player.getNum() == 1 ? "/image/b_rook.png" : "/image/w_rook.png");
    }
    public String getName() {
        return "Rook";
    }
    // Vert / horizontal as many tiles as possible
    public boolean isValidMove() {

        if (this.getPos().x == this.getPrevPos().x || this.getPos().y == this.getPrevPos().y) {
            return true;
        }
        return false;
    }

    @Override
    public boolean collisionInPath(ArrayList<Piece> pieces) {
        int startX = this.getPrevPos().x;
        int startY = this.getPrevPos().y;
        int endX = this.getPos().x;
        int endY = this.getPos().y;
        Player player = this.getPlayer();


        for (Piece piece : pieces) {
            // Skip the current piece in the loop
            if (piece.equals(this)) {
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

}
