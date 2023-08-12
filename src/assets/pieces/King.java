package assets.pieces;


import assets.Piece;
import game.Player;

import java.awt.*;
import java.util.ArrayList;

public class King extends Piece {
    private boolean firstMove;
    public King(Point p, Player player) {
        super(p, player, player.getNum() == 1 ? "/image/b_king.png" : "/image/w_king.png");
        this.firstMove = true;
    }

    // The king moves all directions 1 tile
    public boolean isValidMove() {
        int xDiff = Math.abs(this.getPos().x - this.getPrevPos().x);
        int yDiff = Math.abs(this.getPos().y - this.getPrevPos().y);
        if ((xDiff == 1 && yDiff == 0) || (xDiff == 0 && yDiff == 1) || (xDiff == 1 && yDiff == 1)) {
            return true;
        }
        return false;
    }
    public boolean isCastleMove() {
        int yDiff = Math.abs(this.getPos().y - this.getPrevPos().y);
        int xDiff = this.getPos().x - this.getPrevPos().x;
        Player p = this.getPlayer();
        int pNum = p.getNum();
        int yVal = pNum == 1 ? 1 : 8;
        // Castling
        Rook rookLeft = p.getRookInPos(new Point (1,yVal));
        Rook rookRight = p.getRookInPos(new Point(8,yVal));
        if (xDiff == 2 && yDiff == 0 && rookRight != null && rookRight.getFirstMove()) { // Right side
            return true;
        }
        if (xDiff == -2 && yDiff == 0 && rookLeft != null && rookLeft.getFirstMove()) { // Left side
            System.out.println("Left castle move");
            return true;
        }
        System.out.println("Aint no castle move" + (xDiff));
        return false;
    }
    public void castle() { // True to the right, false to the left
        int xDiff = this.getPos().x - this.getPrevPos().x;
        Player p = this.getPlayer();
        int pNum = p.getNum();
        int yVal = pNum == 1 ? 1 : 8;
        // Castling
        Rook rookLeft = p.getRookInPos(new Point (1,yVal));
        Rook rookRight = p.getRookInPos(new Point(8,yVal));
        if (xDiff == 2) { // Kingside
            rookRight.setPos(new Point(6,yVal));
            rookRight.update();
            this.setPos(new Point(7,yVal));
        } if (xDiff == -2) {
            rookLeft.setPos(new Point(4,yVal));
            rookLeft.update();
            this.setPos(new Point(3,yVal));
        }
        this.update();

    }


    public String getName() {
        return "King";
    }
    public void setFirstMove() {
        this.firstMove = false;
    }

    public boolean getFirstMove() {
        return this.firstMove;
    }
}
