package piece;

import main.GamePanel;

public class rook extends Piece {
    public rook(int color, int col, int row) {
        super(color, col, row);

        if(color == GamePanel.white) {
            image = getImage("src/res/piece/w-rook.png");
        }
        else {
            image = getImage("src/res/piece/b-rook.png");
        }

    }

    public boolean canMove(int targetCol, int targetRow){
        if(isWithinBoard(targetCol, targetRow)){
            if((preCol == targetCol && preRow != targetRow) ||
            preCol != targetCol && preRow == targetRow){
                if(isValidSquare(targetCol, targetRow) && pieceIsOnStraightLine(targetCol, targetRow) == false){
                    return true;
                }
            }
        } 
        return false;
    }
}
