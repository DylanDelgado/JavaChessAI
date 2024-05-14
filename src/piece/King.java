package piece;

import main.GamePanel;

public class King extends Piece {
    public King(int color, int col, int row) {
        super(color, col, row);

        if(color == GamePanel.white) {
            image = getImage("src/res/piece/w-king.png");
        }
        else {
            image = getImage("src/res/piece/b-king.png");
        }

    }

    public boolean canMove(int targetCol, int targetRow){
        if(isWithinBoard(targetCol, targetRow)){
            
            if((Math.abs(targetCol-preCol) == 1 && Math.abs(targetRow-preRow) == 1) ||
            Math.abs(targetCol-preCol) + Math.abs(targetRow-preRow) == 1){

                if(isValidSquare(targetCol, targetRow)){
                    return true;
                }
            }

            //Castleling
            if(moved == false) { 
                //left castling
                if(targetCol == preCol-2 && targetRow == preRow && pieceIsOnStraightLine(targetCol-1, targetRow) == false) {
                    for(Piece piece : GamePanel.simPieces){
                        if(piece.col == preCol - 4 && piece.row == preRow && piece.moved == false){
                        GamePanel.castlingP = piece;
                        return true;
                        }
                    }
                }
                //right castling
                if(targetCol == preCol+2 && targetRow == preRow && pieceIsOnStraightLine(targetCol, targetRow) == false) {
                    for(Piece piece : GamePanel.simPieces){
                        if(piece.col == preCol + 3 && piece.row == preRow && piece.moved == false){
                        GamePanel.castlingP = piece;
                        return true;
                        }
                    }
                }
            }
        } 
        return false;
    }
}
