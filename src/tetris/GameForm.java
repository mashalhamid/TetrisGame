package tetris;
import javax.swing.JFrame;

public class GameForm extends JFrame{
    public GameForm()
    {
        //initComponents();
        setTitle("Tetris");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(300, 200);
        setLocationRelativeTo(null);
        setVisible(true);
        this.add( new GameArea() );
    }

    public static void main(String[] args){
        new GameForm();
    }


}