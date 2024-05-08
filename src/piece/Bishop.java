package piece;

import main.GamePanel;

public class Bishop extends Piece {
    public Bishop(int color, int col, int row) {
        super(color, col, row);

        if(color == GamePanel.white) {
            image = getImage("src/res/piece/w-bishop.png");
        }
        else {
            image = getImage("src/res/piece/b-bishop.png");
        }
        
    }

    public boolean canMove(int targetCol, int targetRow){
        if(isWithinBoard(targetCol, targetRow)){
            if(Math.abs(preCol-targetCol) == Math.abs(preRow-targetRow ) && Math.abs(preCol-targetCol) != 0){
                if(isValidSquare(targetCol, targetRow)){
                    return true;
                }
            }
        } 
        return false;
    }
}
