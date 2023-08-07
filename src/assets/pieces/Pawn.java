package assets.pieces;

import assets.Piece;
import game.Player;

import java.awt.*;
import java.util.ArrayList;

// "Bonde"
public class Pawn extends Piece {
    private boolean firstMove; // The pawn piece gets to move two tiles on the first move
    private boolean passant; // En passant rule, this should be true if the piece has moved 2 tiles.
    private String promoteTo;

    public Pawn(Point p, Player player) {
        super(p, player, player.getNum() == 1 ? "/image/b_pawn.png" : "/image/w_pawn.png");
        this.firstMove = true;
        this.passant = false;
    }



    public String getName() {
        return "Pawn";
    }
    public boolean isValidMove() {
        int xDiff = this.getPos().x - this.getPrevPos().x;
        int yDiff = this.getPos().y - this.getPrevPos().y;
        // The top player (p1) gets to move 2 tiles down the first time moving and then 1 tile.
        if (this.getPlayer().getNum() == 1) {
            if (this.firstMove && yDiff == 2 && xDiff == 0) {
                this.firstMove = false;
                this.passant = true;
                System.out.println("Valid 2 move!");
                return true;
            }

            if (yDiff == 1 && xDiff == 0) {
                this.firstMove = false;
                this.passant = false;
                System.out.println("Valid!");
                return true;
            }
            if (yDiff == 0 && xDiff == 0) {
                return true;
            }
        }
        // The bottom player (p2) gets to move 2 tiles up the first time moving and then 1 tile.
        else  {
            if (this.firstMove && yDiff == -2 && xDiff == 0) {
                this.firstMove = false;
                this.passant = true;
                System.out.println("Valid 2 move!");
                return true;
            }

            if (yDiff == -1 && xDiff == 0) {
                this.firstMove = false;
                this.passant = false;
                System.out.println("Valid!");
                return true;
            }
            if (yDiff == 0 && xDiff == 0) {
                return true;
            }

        }


            return false;


    }
    // Returns true if the Pawn is moved diagonally
    public boolean isCaptureMove() {

        int xDiff = Math.abs(this.getPos().x - this.getPrevPos().x);
        int yDiff = this.getPos().y - this.getPrevPos().y;
        if (this.getPlayer().getNum() == 1 && xDiff == 1 && yDiff == 1) {
            System.out.println("CaptureMove1");
            return true;
        }
        if (this.getPlayer().getNum() == 2 && xDiff == 1 && yDiff == -1) {
            System.out.println("CaptureMove2");
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
        int diffX = Math.abs(startX - endX);
        int diffY = Math.abs(startY - endY);

        for (Piece piece: pieces) {
            if (piece.equals(this)) {
                continue;
            }
            int pieceX = piece.getPos().x;
            int pieceY = piece.getPos().y;
            if (this.getPlayer().getNum() == 1 && diffX == 0 && diffY == 2) {
                // If the piece is between the old and the new Y pos on the first "2 move"
                if (pieceX == startX && pieceY == endY - 1) {
                    return true;
                }
            } if (this.getPlayer().getNum() == 2 && diffX == 0 && diffY == 2) {
                // If the piece is between the old and the new Y pos on the first "2 move"
                if (pieceX == endX && pieceY == endY + 1) {
                    return true;
                }
            }
        } return false;
    }



    public String getPromoteTo() {
        return this.promoteTo;
    }
    public boolean getPassant() {
        return this.passant;
    }
    public void setFirstMove() {
        this.firstMove = false;
    }

}
