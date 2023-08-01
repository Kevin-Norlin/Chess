package assets.pieces;

import assets.Piece;
import game.Player;

import java.awt.*;
import java.util.ArrayList;

// "Torn"
public class Rook extends Piece {
    public Rook(Point p, Player player) {
        super(p, player);
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
    // Cursed collision code skull
    // TODO: Needs to handle case when capturing pieces.
    public boolean collisionInPath(ArrayList<Piece> pieces) {
        boolean collisionFound = false;
        boolean thisFound = false;
        Point start = new Point(this.getPrevPos());
        Point end = new Point(this.getPos());
        // Check vertically to right
        for (int x = start.x; x <= end.x; x++ ) {
            for (int i = 0; i < pieces.size(); i++) {
                if (x == pieces.get(i).getPos().x && end.y == pieces.get(i).getPos().y && !pieces.get(i).equals(this)) {
                    collisionFound = true;
                }
                if (end.x == x && start.y == end.y) {
                    thisFound = true;
                }
            }
        }
        if (!thisFound) {
            collisionFound = false;
            // Check vertically to the left
            for (int x = start.x; x >= end.x; x--) {
                for (int i = 0; i < pieces.size(); i++) {
                    if (x == pieces.get(i).getPos().x && end.y == pieces.get(i).getPos().y && !pieces.get(i).equals(this)) {
                        collisionFound = true;
                    }
                    if (end.x == x && start.y == end.y) {
                        thisFound = true;
                    }
                }
            }
        }

        if (collisionFound && thisFound) {
            System.out.println("X collision found");
            return true;
        }
        // Check horizantally from top
        thisFound = false;
        collisionFound = false;
        for (int y = start.y; y <= end.y; y++) {
            for (int i = 0; i < pieces.size(); i++) {
                if (y == pieces.get(i).getPos().y && end.x == pieces.get(i).getPos().x && !pieces.get(i).equals(this)) {
                    collisionFound = true;
                } if (end.y == y && start.x == end.x) {
                    thisFound = true;
                }
            }
        }
        if (!thisFound) {
            // Check horizontally from bottom
            collisionFound = false;
            for (int y = start.y; y >= end.y; y--) {
                for (int i = 0; i < pieces.size(); i++) {
                    if (y == pieces.get(i).getPos().y && end.x == pieces.get(i).getPos().x && !pieces.get(i).equals(this)) {
                        collisionFound = true;
                    } if (end.y == y && start.x == end.x) {
                        thisFound = true;
                    }
                }
            }
        }
        if (collisionFound && thisFound) {
            System.out.println("Y collision found");
            return true;
        }
        return false;
    }

}
