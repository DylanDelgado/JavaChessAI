package piece;

import main.GamePanel;

public class Pawn extends Piece {
    public Pawn(int color, int col, int row) {
        super(color, col, row);

        if(color == GamePanel.white) {
            image = getImage("src/res/piece/w-pawn.png");
        }
        else {
            image = getImage("src/res/piece/b-pawn.png");
        }
    }

    public boolean canMove(int targetCol, int targetRow) {
        if(isWithinBoard(targetCol,targetRow)) {
            hittingP = gettingHit(targetCol, targetRow);
            //We need to change direction for each color
            int moveValue;
            if(color == GamePanel.white){
                moveValue = -1;
            } else{moveValue = 1;}
            // single square movement
            if(targetCol == preCol && targetRow == preRow + moveValue && hittingP == null){
                return true;
            }
            // 2 square movement
            if(targetCol == preCol && targetRow == 2*moveValue + preRow && moved == false && hittingP == null
             && pieceIsOnStraightLine(targetCol, targetRow) == false){
                return true;
            }
            // taking diagonal piece
            if((targetCol == preCol + 1 || targetCol == preCol - 1) && targetRow == moveValue + preRow && hittingP != null && hittingP.color != this.color) {
                return true;
            }
        }
        return false;
    }
}
