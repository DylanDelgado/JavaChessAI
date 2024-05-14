package main;

import piece.Piece;
import java.util.ArrayList;
import java.util.Random;

public class ChessBot {
    private static final Random random = new Random();

    public static void generateMoves(ArrayList<Piece> pieces) {
        ArrayList<Move> legalMoves = new ArrayList<>();

        // Iterate over all pieces to find legal moves for the current color
        for (Piece piece : pieces) {
            if (piece.color == GamePanel.currentcolor) {
                for (int col = 0; col < 8; col++) {
                    for (int row = 0; row < 8; row++) {
                        if (piece.canMove(col, row) && piece.isValidSquare(col, row)) {
                            legalMoves.add(new Move(piece, col, row));
                        }
                    }
                }
            }
        }

        // Pick a random move from the list of legal moves
        if (!legalMoves.isEmpty()) {
            Move move = legalMoves.get(random.nextInt(legalMoves.size()));
            executeMove(move);
        }
    }

    private static void executeMove(Move move) {
        // Perform the move
        move.piece.col = move.targetCol;
        move.piece.row = move.targetRow;
        move.piece.updatePosition();

        // Handle capturing a piece
        Piece capturedPiece = move.piece.gettingHit(move.targetCol, move.targetRow);
        if (capturedPiece != null) {
            GamePanel.pieces.remove(capturedPiece);
        }

        // Update the game state
        GamePanel.copyPiece(GamePanel.pieces, GamePanel.simPieces);
    }

    // Helper class to store a move
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
