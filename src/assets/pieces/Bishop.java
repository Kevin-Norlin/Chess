package assets.pieces;

import assets.Piece;
import game.Player;

import java.awt.*;
import java.util.ArrayList;

// "Löpare"
public class Bishop extends Piece {
    public Bishop(Point p, Player player) {
        super(p, player, player.getNum() == 1 ? "/image/b_bishop.png" : "/image/w_bishop.png");
    }

    public String getName() {
        return "Bishop";
    }

    // Diagonally
    @Override
    public boolean isValidMove() {
        int xDiff = Math.abs(this.getPos().x - this.getPrevPos().x);
        int yDiff = Math.abs(this.getPos().y - this.getPrevPos().y);
        if (xDiff == yDiff) {
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
}
