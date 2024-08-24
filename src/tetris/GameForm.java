package tetris;
import javax.swing.*;
import java.awt.event.ActionEvent;

public class GameForm extends JFrame{

    private GameArea ga;

    //constructor
    public GameForm()
    {
        setTitle("Tetris");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(450,650);
        setLocationRelativeTo(null);
        setLayout(null); // Disable layout manager for absolute positioning

        ga = new GameArea(10);
        this.add(ga);

        initControls();

        startGame();
    }

    private void initControls(){
        InputMap im = this.getRootPane().getInputMap();
        ActionMap am = this.getRootPane().getActionMap();

        im.put(KeyStroke.getKeyStroke("RIGHT"), "right");
        im.put(KeyStroke.getKeyStroke("LEFT"), "left");
        im.put(KeyStroke.getKeyStroke("UP"), "up");
        im.put(KeyStroke.getKeyStroke("DOWN"), "down");

        am.put("right", new AbstractAction(){
            @Override
            public void actionPerformed(ActionEvent e){
                ga.moveBlockRight();

            }
        });

        am.put("left", new AbstractAction(){
            @Override
            public void actionPerformed(ActionEvent e){
                ga.moveBlockLeft();

            }
        });

        am.put("up", new AbstractAction(){
            @Override
            public void actionPerformed(ActionEvent e){
                ga.rotateBlock();

            }
        });

        am.put("down", new AbstractAction(){
            @Override
            public void actionPerformed(ActionEvent e){
                ga.dropBlockInstantly();

            }
        });

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
