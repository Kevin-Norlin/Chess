package assets.pieces;

import assets.Piece;
import game.Game;
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


    public boolean isValidMove() {
        int diffX = Math.abs(this.getPrevPos().x - this.getPos().x);
        int diffY = this.getPos().y - this.getPrevPos().y;
        if (this.getPlayer().getNum() == 1) {
            // First move for player 1
            if (diffX == 0 && diffY == 2 && this.firstMove) {
                this.firstMove = false;
                return true;
            } // Ordinary first move for player 1
            if (diffX == 0 && diffY == 1 && this.firstMove) {
                this.firstMove = false;
                return true;
            } // Ordinary move for player 1
            if (diffX == 0 && diffY == 1) {
                return true;
            }
            return false; // If the move is not correct
        } if (this.getPlayer().getNum() == 2) {
            // First move for player 2
            if (diffX == 0 && diffY == -2 && this.firstMove) {
                this.firstMove = false;
                return true;
            } // Ordinary first move for player 2
            if (diffX == 0 && diffY == -1 && this.firstMove) {
                this.firstMove = false;
                return true;
            } // Ordinary move for player 2
            if (diffX == 0 && diffY == -1) {
                return true;
            }
            return false; // If the move is not correct
        } return false;
    }

    public boolean isValidMoveNoEffects() {
        int diffX = Math.abs(this.getPrevPos().x - this.getPos().x);
        int diffY = this.getPos().y - this.getPrevPos().y;
        if (this.getPlayer().getNum() == 1) {
            // First move for player 1
            if (diffX == 0 && diffY == 2 && this.firstMove) {
                return true;
            } // Ordinary first move for player 1
            if (diffX == 0 && diffY == 1 && this.firstMove) {
                return true;
            } // Ordinary move for player 1
            if (diffX == 0 && diffY == 1) {
                return true;
            }
            return false; // If the move is not correct
        } if (this.getPlayer().getNum() == 2) {
            // First move for player 2
            if (diffX == 0 && diffY == -2 && this.firstMove) {
                return true;
            } // Ordinary first move for player 2
            if (diffX == 0 && diffY == -1 && this.firstMove) {
                return true;
            } // Ordinary move for player 2
            if (diffX == 0 && diffY == -1) {
                return true;
            }
            return false; // If the move is not correct
        } return false;
    }

    // Returns true if the Pawn has moved diagonally
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

    // Collision in path should check for collision from start to end position, it doesn't check collision on same pos
    @Override
    public boolean collisionInPath(ArrayList<Piece> pieces) {
        for (Piece piece : pieces) {
            if (piece.getPos().equals(this.getPos()) || piece.getPos().equals(this.getPrevPos())) {
                continue;
            }
            if (this.getPlayer().getNum() == 1) {
                if (piece.getPos().x == this.getPos().x && piece.getPos().y >= this.getPrevPos().y && piece.getPos().y <= this.getPos().y) {
                    return true;
                }
            }
            if (this.getPlayer().getNum() == 2) {
                if (piece.getPos().x == this.getPos().x && piece.getPos().y <= this.getPrevPos().y && piece.getPos().y >= this.getPos().y) {
                    return true;
                }
            }
        } return false;
    }

    // Checks all logic for the pawn
    @Override
    public boolean checkLogic(Game g) {
        // Capture Move
        if (g.pieceInSamePos(this) != null && isCaptureMove() && !(g.pieceInSamePos(this).getPlayer().equals(this.getPlayer()))) {
            g.setpToRemove(g.pieceInSamePos(this));
            setFirstMove();
            return true;
        }
        if ((isValidMove() && !collisionInPath(g.getPieces()) && g.pieceInSamePos(this) == null)) {
            setFirstMove();
            return true;
        } return false;
    }

    @Override
    public boolean checkLogicNoEffects(Game g) {
        // Capture Move
        if (g.pieceInSamePos(this) != null && isCaptureMove() && !(g.pieceInSamePos(this).getPlayer().equals(this.getPlayer()))) {
            return true;
        }
        // Check if the move is valid and there are no pieces blocking the path
        if (isValidMoveNoEffects() && g.pieceInSamePos(this) == null && !collisionInPath(g.getPieces())) {
            return true;
        }
        // If neither of the above conditions are met, the move is not valid
        return false;
    }

    public String getName() {
        return "Pawn";
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

    public boolean getFirstMove() {
        return this.firstMove;
    }
}
