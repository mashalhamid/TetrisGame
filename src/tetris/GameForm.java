package tetris;
import javax.swing.*;

public class GameForm extends JFrame{

    public GameForm()
    {
        setTitle("Tetris");
        setSize(400,600);
        setLocationRelativeTo(null);
        setLayout(null); // Disable layout manager for absolute positioning

        GameArea gameArea = new GameArea(10);
        add(gameArea); // Add the game area to the JFrame
    }

    public static void main(String[] args){

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GameForm().setVisible(true);
            }
        });

    }
}
