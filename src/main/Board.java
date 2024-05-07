package main;

import java.awt.Color;
import java.awt.Graphics2D;

public class Board {

    final int MaxCol = 8;
    final int MaxRow = 8;
    public static final int SquareSize = 100;
    public static final int HalfSquareSize = SquareSize/2;

    public void draw(Graphics2D g2) {

        int c = 0;

        for(int row = 0; row < MaxRow; row++)   {

            for(int col = 0; col < MaxCol; col++)   {

                if(c%2 == 0){
                    g2.setColor(new Color(210,165,125));
                    c += 1;
                }
                else{
                    g2.setColor(new Color(175,115,70));
                    c += 1;
                }

                g2.fillRect(col*SquareSize,row*SquareSize,SquareSize,SquareSize);

            }
            c++;
        }
    }
}