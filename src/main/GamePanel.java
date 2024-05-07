package main;

import java.awt.*;
import java.util.*;
import javax.swing.JPanel;
import piece.*;

public class GamePanel extends JPanel implements Runnable {

    public static final int Width = 1100;
    public static final int Height = 800;
    final int FPS = 60;
    Thread gameThread;
    Board board = new Board();
    Mouse mouse = new Mouse();
    boolean canMove;
    boolean validSquare;
    
    //Pieces Array
    public static ArrayList<Piece> pieces = new ArrayList<>();
    public static ArrayList<Piece> simPieces = new ArrayList<>();
    Piece activeP;
    //Define the color index2
    public static final int white = 0;
    public static final int black = 1;
    int currentcolor = white;

    public GamePanel() {
        setPreferredSize(new Dimension(Width,Height));
        setBackground(Color.black);
        addMouseMotionListener(mouse);
        addMouseListener(mouse);

        setPieces();
        copyPiece(pieces, simPieces);
    }
    public void setPieces() {
        //White pieces
        pieces.add(new Pawn(white,0,6));
        pieces.add(new Pawn(white,1,6));
        pieces.add(new Pawn(white,2,6));
        pieces.add(new Pawn(white,3,6));
        pieces.add(new Pawn(white,4,6));
        pieces.add(new Pawn(white,5,6));
        pieces.add(new Pawn(white,6,6));
        pieces.add(new Pawn(white,7,6));
        pieces.add(new rook(white,0,7));
        pieces.add(new Knight(white,1,7));
        pieces.add(new Bishop(white,2,7));
        pieces.add(new Queen(white,3,7));
        pieces.add(new King(white,4,4));
        pieces.add(new Bishop(white,5,7));
        pieces.add(new Knight(white,6,7));
        pieces.add(new rook(white,7,7));
        //Black pieces
        pieces.add(new Pawn(black,0,1));
        pieces.add(new Pawn(black,1,1));
        pieces.add(new Pawn(black,2,1));
        pieces.add(new Pawn(black,3,1));
        pieces.add(new Pawn(black,4,1));
        pieces.add(new Pawn(black,5,1));
        pieces.add(new Pawn(black,6,1));
        pieces.add(new Pawn(black,7,1));
        pieces.add(new rook(black,0,0));
        pieces.add(new Knight(black,1,0));
        pieces.add(new Bishop(black,2,0));
        pieces.add(new Queen(black,3,0));
        pieces.add(new King(black,4,0));
        pieces.add(new Bishop(black,5,0));
        pieces.add(new Knight(black,6,0));
        pieces.add(new rook(black,7,0));

    }
    private void copyPiece(ArrayList<Piece> source, ArrayList<Piece> target) {
        target.clear();
        for(int i = 0; i < source.size(); i++){
            target.add(source.get(i));
        }
    }
    public void launchGame() {
        gameThread = new Thread(this);
        gameThread.start();
    }
    @Override
    public void run() {
        double drawInterval = 1000000000/FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;

        while(gameThread != null) {
            currentTime = System.nanoTime();

            delta += (currentTime - lastTime)/drawInterval;
            lastTime = currentTime;

            if(delta >=1) {
                update();
                repaint();
                delta--;
            }
        }
    }

    //This method is going to handle updating the board (piece position and count)
    private void update() {
    // WHEN THE MOUSE IS PRESSED PIECE IS SELECTED //
        if(mouse.pressed){
            if(activeP == null){

                for(Piece piece : simPieces){
                    if(piece.color ==currentcolor &&
                    piece.col == mouse.x/Board.SquareSize &&
                    piece.row == mouse.y/Board.SquareSize){

                        activeP = piece;
                    }

                }
            }
            else{
                simulate();
            }
    }
        // WHEN MOUSE IS RELEASED 

        if(mouse.pressed == false){
            if(activeP != null){
                if(validSquare){
                    activeP.updatePosition();
                } else{
                    activeP.resetPosition();
                }

                activeP = null;

            }
        }    
    }


    private void simulate() { 
        
        canMove = false;
        validSquare = false;
        
        activeP.x = mouse.x - Board.HalfSquareSize;
        activeP.y = mouse.y - Board.HalfSquareSize;
        activeP.col = activeP.getCol(activeP.x);
        activeP.row = activeP.getRow(activeP.y);

        if(activeP.canMove(activeP.col,activeP.row)){
            canMove = true;
            validSquare = true;
        }
    }

    
    //This method will handle drawing the board and pieces
    public void paintComponent (Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g;
        // Draw Board
        board.draw(g2);
        // Draw Pieces
        for(Piece p : simPieces){
            p.draw(g2);
        }
        if(activeP != null){

            if(canMove){
                g2.setColor(Color.white);
                g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f));
                g2.fillRect(activeP.col*Board.SquareSize,activeP.row*Board.SquareSize,Board.SquareSize,Board.SquareSize);

                g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
                activeP.draw(g2);
            }
        }

        //  Draw all allowed moves

        if(activeP != null){
            for(int i = 0; i < 8 ; i++){
                for(int j = 0; j < 8; j++){
                    if(activeP.canMove(i, j)){
                        int radius = 30;
                        g2.setColor(Color.gray);
                        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f));
                        g2.fillOval(i*Board.SquareSize + Board.HalfSquareSize - radius/2, j*Board.SquareSize + 50 - radius/2, radius,radius);
                        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
                        activeP.draw(g2);
                    }
                }
            }
        }
    }
}
