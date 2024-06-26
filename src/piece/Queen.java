package piece;

import main.GamePanel;

public class Queen extends Piece {
    int pieceValue = 900;

    public Queen(int color, int col, int row) {
        super(color, col, row);

        if(color == GamePanel.white) {
            image = getImage("src/res/piece/w-queen.png");
        }
        else {
            image = getImage("src/res/piece/b-queen.png");
        }

    }
    public boolean canMove(int targetCol, int targetRow){
        if(isWithinBoard(targetCol, targetRow)){

            //Straight
            if((preCol == targetCol && preRow != targetRow) ||
            preCol != targetCol && preRow == targetRow){
                if(isValidSquare(targetCol, targetRow) && pieceIsOnStraightLine(targetCol,targetRow) == false){
                    return true;
                }
            }
            //Diagonal
            if( (Math.abs(preCol-targetCol) == Math.abs(preRow-targetRow) && Math.abs(preCol-targetCol) != 0)){
                if(isValidSquare(targetCol, targetRow) && pieceOnDiagonal(targetCol,targetRow) == false){
                    return true;
                }
            }
        } 
        return false;
    }
}
