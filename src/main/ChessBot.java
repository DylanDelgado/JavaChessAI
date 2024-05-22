package main;

import piece.Piece;

import java.util.ArrayList;
import java.util.Random;

public class ChessBot {
    private static final Random random = new Random();

    public static void generateMoves(ArrayList<Piece> pieces) {
        ArrayList<Move> legalMoves = findAllLegalMoves(pieces, GamePanel.currentcolor);
        ArrayList<Move> capturingMoves = findCapturingMoves(legalMoves);

        if (!capturingMoves.isEmpty()) {
            Move randomCapturingMove = capturingMoves.get(random.nextInt(capturingMoves.size()));
            executeMove(randomCapturingMove);
        } else if (!legalMoves.isEmpty()) {
            Move randomMove = legalMoves.get(random.nextInt(legalMoves.size()));
            executeMove(randomMove);
        }
    }

    private static ArrayList<Move> findAllLegalMoves(ArrayList<Piece> pieces, int color) {
        ArrayList<Move> legalMoves = new ArrayList<>();
        for (Piece piece : pieces) {
            if (piece.color == color) {
                for (int col = 0; col < 8; col++) {
                    for (int row = 0; row < 8; row++) {
                        if (piece.canMove(col, row) && piece.isValidSquare(col, row)) {
                            legalMoves.add(new Move(piece, col, row));
                        }
                    }
                }
            }
        }
        return legalMoves;
    }

    private static ArrayList<Move> findCapturingMoves(ArrayList<Move> legalMoves) {
        ArrayList<Move> capturingMoves = new ArrayList<>();
        for (Move move : legalMoves) {
            if (move.piece.gettingHit(move.targetCol, move.targetRow) != null) {
                capturingMoves.add(move);
            }
        }
        return capturingMoves;
    }

    private static void executeMove(Move move) {
        move.piece.col = move.targetCol;
        move.piece.row = move.targetRow;
        move.piece.updatePosition();
        Piece capturedPiece = move.piece.gettingHit(move.targetCol, move.targetRow);
        if (capturedPiece != null) {
            GamePanel.pieces.remove(capturedPiece);
        }
        GamePanel.copyPiece(GamePanel.pieces, GamePanel.simPieces);
    }

    private static class Move {
        Piece piece;
        int targetCol;
        int targetRow;

        Move(Piece piece, int targetCol, int targetRow) {
            this.piece = piece;
            this.targetCol = targetCol;
            this.targetRow = targetRow;
        }
    }
}
