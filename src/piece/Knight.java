package piece;

import main.GamePanel;

public class Knight extends Piece {
    public Knight(int color, int col, int row) {
        super(color, col, row);

        if(color == GamePanel.white) {
            image = getImage("src/res/piece/w-knight.png");
        }
        else {
            image = getImage("src/res/piece/b-knight.png");
        }

    }

    public boolean canMove(int targetCol, int targetRow){
        if(isWithinBoard(targetCol, targetRow)){
            if((Math.abs(targetCol-preCol) == 2 && Math.abs(targetRow-preRow) == 1) ||
            Math.abs(targetCol-preCol) == 1 && Math.abs(targetRow-preRow) == 2){
                return true;
            }
        } 
        return false;
    }
}
