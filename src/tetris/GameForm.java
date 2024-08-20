package tetris;
import javax.swing.*;

public class GameForm extends JFrame{

    private GameArea ga;

    public GameForm()
    {
        setTitle("Tetris");
        setSize(400,600);
        setLocationRelativeTo(null);
        setLayout(null); // Disable layout manager for absolute positioning

        ga = new GameArea(10);
        this.add(ga);

        startGame();
    }

    public void startGame()
    {
        new GameThread(ga).start();
    }

    public static void main(String[] args){
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GameForm().setVisible(true);
            }
        });

    }
}
