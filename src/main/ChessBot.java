package main;

import piece.Piece;

import java.util.ArrayList;
import java.util.Random;

public class ChessBot {
    Move PlayingMove = null;
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
/* 
Here is the code where I implement the tree search and evaluation function.
I ran into many errors that I just couldn't reconcile related to the piece and simpiece arrays.
I got the search function from the chessPrograming wiki. It recursively calls itself and is very interesting to dive into if you would like.
Too bad I couldn't get this figured out in time. I had so much fun working on this project, it was very fulfilling, have a good summer break!

 *     private static int evalFunction(ArrayList<Piece> pieces){
        int eval = 0;
        for(Piece piece : pieces){
            if(piece.color == GamePanel.white){
                eval += piece.pieceValue;
            } else{
                eval -= piece.pieceValue;
            }
        }
        return eval;
    }
    public static Move findBestMove(ArrayList<Piece> pieces, int depth) {
        int bestScore = Integer.MIN_VALUE;
        Move bestMove = null;
        ArrayList<Move> legalMoves = findAllLegalMoves(pieces, GamePanel.currentcolor);

        for (Move move : legalMoves) {
            // Simulate the move
            ArrayList<Piece> newPieces = simulateMove(pieces, move);
            // Call negaMax for the next depth
            int score = -Search(newPieces, depth - 1);

            if (score > bestScore) {
                bestScore = score;
                bestMove = move;
            }
        }
        return bestMove;
    }
    private static int Search(ArrayList<Piece> pieces, int depth) {
        if(depth == 0){
            return evalFunction(pieces);
        }
        ArrayList<Piece> simpieces = null;
        GamePanel.copyPiece(pieces, simpieces);
        ArrayList<Move> moves = findAllLegalMoves(simpieces, GamePanel.currentcolor);

        int BestEvaluation = -99999;

        for(Move move : moves){
            move.piece.updatePosition();
            GamePanel.changePlayer();
            int evaluation = - Search(simpieces, depth-1);
            BestEvaluation = Math.max(evaluation, BestEvaluation);
            GamePanel.copyPiece(pieces, simpieces);
        }
        return BestEvaluation;
    }

    This is to simulate a move in the search function.

    private static ArrayList<Piece> simulateMove(ArrayList<Piece> pieces, Move move) {
        ArrayList<Piece> newPieces = new ArrayList<>(pieces.size());
        for (Piece piece : pieces) {
            newPieces.add(piece.clone()); // Ensure your Piece class has a clone method
        }

        for (Piece piece : newPieces) {
            if (piece.col == move.piece.col && piece.row == move.piece.row) {
                piece.col = move.targetCol;
                piece.row = move.targetRow;
                piece.updatePosition();
            } else if (piece.col == move.targetCol && piece.row == move.targetRow) {
                newPieces.remove(piece);
                break;
            }
        }
        return newPieces;
    }

*/

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
