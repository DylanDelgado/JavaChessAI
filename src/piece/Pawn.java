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
}
