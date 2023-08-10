package game;

import assets.Piece;
import assets.Tile;
import assets.pieces.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import static constants.Constants.*;

// TODO: generate legalMoves...
public class Game {
    private GameWindow window;
    private GamePanel panel;
    private ArrayList<Tile> chessBoard;
    private ArrayList<Piece> pieces;
    private Player p1,p2, currentPlayer;

    private Piece pToRemove;
    private boolean event; // If a move has been made.
    private boolean checkEvent;
    private boolean gameOver;
    private boolean restart;

    public Game() {
        p1 = new Player(1);
        p2 = new Player(2);
        currentPlayer = p1;
        chessBoard = new ArrayList<>();
        pieces = new ArrayList<>();
        this.checkEvent = false;
        this.gameOver = false;
        this.restart = false;
        int size = SIZE;

        // Fill the list with all tiles in the chessBoard
        for (int r = size; r <= size * 8; r += size) {
            for (int c = size; c <= size * 8; c += size) {
                if ((r + c) / size % 2 == 0) {
                    chessBoard.add(new Tile(r,c,size,size,TILE_COLOR_LIGHT));
                } else {
                    chessBoard.add(new Tile(r,c,size,size,TILE_COLOR_DARK));
                }
            }
        }
        this.panel = new GamePanel(this,chessBoard, p1,p2);
        this.window = new GameWindow(panel);
        fillBoard();
        this.panel.displayPlayer(this.currentPlayer);
    }

    public void startGame() {
        panel.setCheckMate(currentPlayer);
        pToRemove = null;
        ArrayList<Point> legalMoves;

        // Game loop
        while (true) {
            currentPlayer.startTimer();
            panel.repaint();
            checkTiles();
            int count = 1;
            // Move pieces to new pos if its a valid move
            for (Piece p : pieces) {

                // Show valid moves on panel
                if (p.getIsActive()) {
                    panel.clearValidMoves();
                    legalMoves = p.generateLegalMoves(this);
                    if (legalMoves.size() > 0) {
                        panel.setValidMoves(legalMoves);
                        panel.repaint();
                    }
                    p.setIsActive(false);
                }

                // If a player tries to move when its not their turn
                if (p.hasMoved() && !p.getPlayer().equals(this.currentPlayer)) {
                    p.revert();
                    break;
                }
                // Check all logic for all pieces
                if (p.hasMoved()) {

                    if (p.checkLogic(this)) {

                        p.update(this);
                    } else {
                        p.revert();
                    }
                }
            }
            // Remove the piece that has been marked for removal
            purge();

            // If a move has been made successfully then change player
            if (this.event) {
                currentPlayer.stopTimer();
                this.currentPlayer = this.currentPlayer.getNum() == 1 ? this.p2 : this.p1;
                this.panel.displayPlayer(this.currentPlayer);
                toggleEvent();
                panel.clearValidMoves();
                boolean check = checkForCheck(currentPlayer);
                if (check) {
                    panel.setCheck();
                    panel.repaint();
                    if (checkForCheckmate(currentPlayer)) {
                        System.out.println(currentPlayer.getNum() == 1 ? "Checkmate! Black lost!" : "Checkmate! White lost!");
                        panel.setCheckMate(currentPlayer);
                        panel.repaint();
                        this.gameOver = true;
                    }

                } if (!check) {
                    panel.clearCheck();
                    panel.repaint();
                }
            }
            if (this.gameOver) {
                while (!this.restart);
                if (this.restart) {
                    break;
                }
            }
        }

    }

    // Check all tiles and tie Pieces to tiles
    public void checkTiles() {
        boolean pieceFound;
        // Check all tiles. Set pieces to the tiles they are over and clear the ones that have no piece.
        for (Tile t: chessBoard) {
            pieceFound = false;
            for (Piece p : pieces) {
                if (t.checkTile(p)) {
                    t.setPiece(p);
                    pieceFound = true;
                }
            }
            if (!pieceFound) {
                t.clearTile();
            }
        }
    }
    // Returns true for both friendly and hostile pieces
    public Piece pieceInSamePos(Piece p) {
        for (Piece piece : pieces) {
            if (p.getPos().x == piece.getPos().x && p.getPos().y == piece.getPos().y && !p.equals(piece)) {
                return piece;
            }
        }
        return null;
    }


    public void fillBoard() {
        // Create pieces for Player 1 (p1) and add them to the panel, pieces array, and player's pieces list
        Piece kingP1 = new King(new Point(5, 1), p1);
        panel.addPositionable(kingP1);
        pieces.add(kingP1);
        p1.addPiece(kingP1);

        Piece queenP1 = new Queen(new Point(4, 1), p1);
        panel.addPositionable(queenP1);
        pieces.add(queenP1);
        p1.addPiece(queenP1);

        Piece rook1P1 = new Rook(new Point(1, 1), p1);
        panel.addPositionable(rook1P1);
        pieces.add(rook1P1);
        p1.addPiece(rook1P1);

        Piece rook2P1 = new Rook(new Point(8, 1), p1);
        panel.addPositionable(rook2P1);
        pieces.add(rook2P1);
        p1.addPiece(rook2P1);

        Piece knight1P1 = new Knight(new Point(2, 1), p1);
        panel.addPositionable(knight1P1);
        pieces.add(knight1P1);
        p1.addPiece(knight1P1);

        Piece knight2P1 = new Knight(new Point(7, 1), p1);
        panel.addPositionable(knight2P1);
        pieces.add(knight2P1);
        p1.addPiece(knight2P1);

        Piece bishop1P1 = new Bishop(new Point(3, 1), p1);
        panel.addPositionable(bishop1P1);
        pieces.add(bishop1P1);
        p1.addPiece(bishop1P1);

        Piece bishop2P1 = new Bishop(new Point(6, 1), p1);
        panel.addPositionable(bishop2P1);
        pieces.add(bishop2P1);
        p1.addPiece(bishop2P1);

        for (int col = 1; col <= 8; col++) {
            Piece pawnP1 = new Pawn(new Point(col, 2), p1);
            panel.addPositionable(pawnP1);
            pieces.add(pawnP1);
            p1.addPiece(pawnP1);
        }

        // Create pieces for Player 2 (p2) and add them to the panel, pieces array, and player's pieces list
        Piece kingP2 = new King(new Point(5, 8), p2);
        panel.addPositionable(kingP2);
        pieces.add(kingP2);
        p2.addPiece(kingP2);

        Piece queenP2 = new Queen(new Point(4, 8), p2);
        panel.addPositionable(queenP2);
        pieces.add(queenP2);
        p2.addPiece(queenP2);

        Piece rook1P2 = new Rook(new Point(1, 8), p2);
        panel.addPositionable(rook1P2);
        pieces.add(rook1P2);
        p2.addPiece(rook1P2);

        Piece rook2P2 = new Rook(new Point(8, 8), p2);
        panel.addPositionable(rook2P2);
        pieces.add(rook2P2);
        p2.addPiece(rook2P2);

        Piece knight1P2 = new Knight(new Point(2, 8), p2);
        panel.addPositionable(knight1P2);
        pieces.add(knight1P2);
        p2.addPiece(knight1P2);

        Piece knight2P2 = new Knight(new Point(7, 8), p2);
        panel.addPositionable(knight2P2);
        pieces.add(knight2P2);
        p2.addPiece(knight2P2);

        Piece bishop1P2 = new Bishop(new Point(3, 8), p2);
        panel.addPositionable(bishop1P2);
        pieces.add(bishop1P2);
        p2.addPiece(bishop1P2);

        Piece bishop2P2 = new Bishop(new Point(6, 8), p2);
        panel.addPositionable(bishop2P2);
        pieces.add(bishop2P2);
        p2.addPiece(bishop2P2);

        for (int col = 1; col <= 8; col++) {
            Piece pawnP2 = new Pawn(new Point(col, 7), p2);
            panel.addPositionable(pawnP2);
            pieces.add(pawnP2);
            p2.addPiece(pawnP2);
        }
    }
    public boolean checkForCheck(Player player) {
        Player enemy = player.getNum() == 1 ? this.p2 : this.p1;
        for (Piece p : enemy.getPieces()) {
            for (Point point : p.generateLegalMoves(this)) {
                // If the enemy player has any legal moves that puts the players king in check
                if (player.getKing().getPos().equals(point)) {
                    return true;
                }
            }
        }
        return false;
    }
    // Very taxing method...
    public boolean checkForCheckmate(Player player) {
        Piece king = player.getKing();
        Point originalKingPos = king.getPos();
        ArrayList<Point> kingValidMoves = king.generateLegalMoves(this);
        // Check if the king can move out of check
        for (Point p : kingValidMoves) {
            king.setPos(p);
            king.update(); // Without toggling event
            if (!checkForCheck(player)) {
                king.setPos(originalKingPos);
                king.update(); // Without toggling event
                return false; // The king can move out of check
            } king.setPos(originalKingPos);
            king.update(); // Without toggling event
        }
        // Check if any of the pieces can prevent the check by moving
        for (Piece p: player.getPieces()) {
            ArrayList<Point> moves = p.generateLegalMoves(this);
            for (Point point : moves) {
                Point originalPos = p.getPos();
                p.setPos(point);
                p.update();
                if (!checkForCheck(player)) {
                    p.setPos(originalPos);
                    p.update();
                    return false;
                }p.setPos(originalPos);
                p.update();
            }
        } return true;
    }

    public void toggleEvent() {
        this.event = !this.event;
    }
    public void setpToRemove(Piece pToRemove) {
        this.pToRemove = pToRemove;
    }
    public ArrayList<Piece> getPieces() {
        return this.pieces;
    }
    private void purge() {
        if (pToRemove != null) {
            pieces.remove(pToRemove);
            panel.removePositionable(pToRemove);
            pToRemove = null;
        }
    }
    public void setPieces(ArrayList<Piece> pieces) {
        this.pieces = pieces;
    }
    public boolean getRestart(){
        return this.restart;
    }
    public void setRestart() {
        this.restart = true;
    }

}


