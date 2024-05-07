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
        } 
        return false;
    }
}
