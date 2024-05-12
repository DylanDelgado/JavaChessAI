package piece;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.Board;
import main.GamePanel;
public class Piece {

    public BufferedImage image;
    public int x, y;
    public int col, row, preCol, preRow;
    public int color;
    public Piece hittingP;
    public Piece hitP;
    public boolean moved;

    public Piece(int color, int col, int row){
        this.color = color;
        this.col = col;
        this.row = row;
        x = getX(col);
        y = getY(row);
        preCol = col;
        preRow = row;

    }
    public BufferedImage getImage(String imagePath){

        BufferedImage image = null;

        try{
            image = ImageIO.read(new File(imagePath));
        }catch(IOException e) {
            System.out.println("Could not find image: " + e.getMessage());
        }
        return image;
    }
    public int getX(int col){
        return col * Board.SquareSize;
    }
    public int getY(int row){
        return row * Board.SquareSize;
    }
    public int getCol(int x){
        return (x + Board.HalfSquareSize)/Board.SquareSize;
    }
    public int getRow(int y){
        return (y + Board.HalfSquareSize)/Board.SquareSize;
    }

    public int getIndex(){
        for(int index = 0; index < GamePanel.simPieces.size(); index++){
            if(GamePanel.simPieces.get(index) == this){
                return index;
            }
        }
        return 0;
    }

    public void updatePosition(){
        x = getX(col);
        y = getY(row);
        preCol = getCol(x);
        preRow = getRow(y);
        moved = true;
    }
    public void resetPosition(){
        col = preCol;
        row = preRow;
        x = getX(col);
        y = getY(row);
    }
    
    public boolean canMove(int targetCol, int targetRow){
        return false;
    }

    public boolean isWithinBoard(int targetCol, int targetRow){
        if(targetCol >= 0 && targetCol <= 7 && targetRow >= 0 && targetRow <= 7){
            return true;
        } else {
            return false;
        }
    }

    public boolean isSameSquare(int targetCol, int targetRow){
        if(targetCol == col && targetRow == row){
            return true;
        }
        return false;
    }

    public boolean pieceIsOnStraightLine(int targetCol, int targetRow) {
        // Check left movement
        for(int c = preCol-1; c> targetCol; c--){
            for(Piece piece : GamePanel.simPieces) {
                if(piece.col == c && piece.row == targetRow){
                    if(piece != this) {
                        hittingP = piece;
                        return true;
                    }
                }
            }
        }
        // Check right movement
        for(int c = preCol+1; c < targetCol; c++){
            for(Piece piece : GamePanel.simPieces) {
                if(piece.col == c && piece.row == targetRow){
                    if(piece != this) {
                        hittingP = piece;
                        return true;
                    }
                }
            }
        }
        // Check up movement
        for(int r = preRow-1; r> targetRow; r--){
            for(Piece piece : GamePanel.simPieces) {
                if(piece.row == r && piece.col == targetCol){
                    if(piece != this) {
                        hittingP = piece;
                        return true;
                    }
                    
                    
                }
            }
        }
        // Check down movement
        for(int r = preRow+1; r < targetRow; r++){
            for(Piece piece : GamePanel.simPieces) {
                if(piece.row == r && piece.col == targetCol){
                    if(piece != this) {
                        hittingP = piece;
                        return true;
                    }
                }
            }
        }
        return false;
    }
    
    public Piece gettingHit(int targetCol, int targetRow){
        for(Piece piece : GamePanel.simPieces){
            if(piece.col == targetCol && piece.row == targetRow && piece != this){
                return piece;
            }
        }
        return null;
    }

    public boolean pieceOnDiagonal(int targetCol, int targetRow) {
        if(targetRow < preRow) {
            // Up left
            for(int c = preCol-1; c > targetCol; c--){
                int diff = Math.abs(c - preCol);
                for(Piece piece : GamePanel.simPieces) {
                    if(piece.col == c && piece.row == preRow - diff){
                        if(piece != this) {
                            hittingP = piece;
                            return true;
                        }
                    }
                }
            }
            // Up right
            for(int c = preCol+1; c < targetCol; c++) {
                int diff = Math.abs(c - preCol);
                for(Piece piece : GamePanel.simPieces) {
                    if(piece.col == c && piece.row == preRow - diff){
                        if(piece != this) {
                            hittingP = piece;
                            return true;
                        }
                    }
                }
            }
        }
        if(targetRow > preRow) {
                        // down left
                        for(int c = preCol-1; c > targetCol; c--){
                            int diff = Math.abs(c - preCol);
                            for(Piece piece : GamePanel.simPieces) {
                                if(piece.col == c && piece.row == preRow + diff){
                                    if(piece != this) {
                                        hittingP = piece;
                                        return true;
                                    }
                                }
                            }
                        }
                        for(int c = preCol+1; c < targetCol; c++) {
                            int diff = Math.abs(c - preCol);
                            for(Piece piece : GamePanel.simPieces) {
                                if(piece.col == c && piece.row == preRow + diff){
                                    if(piece != this) {
                                        hittingP = piece;
                                        return true;
                                    }
                                }
                            }
                        }
        }

        return false;
    }
    public boolean isValidSquare(int targetCol, int targetRow){

        hittingP = gettingHit(targetCol,targetRow);

        if(hittingP == null){
            return true;
        } else{
            if(hittingP.color != this.color){
                return true;
            } else{
                hittingP = null;
                return false;
            }
        }
    }
    public void draw(Graphics2D g2) {
        g2.drawImage(image, x, y, Board.SquareSize, Board.SquareSize, null, null);
    }
}
