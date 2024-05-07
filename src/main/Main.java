package main;

import javax.swing.JFrame;
// Trying to commit something
public class Main {
    public static void main(String[]args){

        JFrame window = new JFrame("Chess");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);

        //Here we add the game panel
        GamePanel gp = new GamePanel();
        window.add(gp);
        window.pack();
        
        window.setLocationRelativeTo(null);
        window.setVisible(true);

        gp.launchGame();


    }
}
