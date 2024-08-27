package tetris;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class GameForm extends JFrame{

    private GameArea ga;

    //constructor
    public GameForm()
    {
        setTitle("Tetris");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(500,600);
        setLocationRelativeTo(null);
        setLayout(null); // Disable layout manager for absolute positioning

        ga = new GameArea(10);
        this.add(ga);

        addTitleLabel("PLAY");
        initControls();
        startGame();
        backButton();
    }

    private void initControls(){
        InputMap im = this.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap am = this.getRootPane().getActionMap();

        im.put(KeyStroke.getKeyStroke("RIGHT"), "right");
        im.put(KeyStroke.getKeyStroke("LEFT"), "left");
        im.put(KeyStroke.getKeyStroke("UP"), "up");
        im.put(KeyStroke.getKeyStroke("DOWN"), "down");
        im.put(KeyStroke.getKeyStroke("P"), "pause");

        am.put("right", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ga.moveBlockRight();
            }
        });

        am.put("left", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ga.moveBlockLeft();
            }
        });

        am.put("up", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ga.rotateBlock();
            }
        });

        am.put("down", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ga.dropBlockInstantly();
            }
        });

        am.put("pause", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ga.togglePause();
                ga.repaint();
                }

        });
    }

    private void backButton() {
        JButton backButton = new JButton("Back");
        backButton.setBounds(130, 510, 200, 30);
        backButton.setBackground(Color.WHITE);
        backButton.setForeground(Color.BLACK);
        backButton.setFocusPainted(false);

        backButton.addActionListener(e -> {
            ga.togglePause(); // Pause the game

            int result = JOptionPane.showConfirmDialog(
                    this,
                    "Are you sure to stop the game?",
                    "Exit Confirmation",
                    JOptionPane.YES_NO_OPTION);

            if (result == JOptionPane.YES_OPTION) {
                dispose(); // Close the game form
                new MainMenu().setVisible(true); // Return to the main menu
            }
            else{
                ga.togglePause();
                ga.togglePause();
                ga.repaint();
            }
        });
        add(backButton);
    }

    public void startGame() {
        new GameThread(ga).start();
    }

    public void addTitleLabel(String text) {
        JLabel titleLabel = new JLabel(text);
        titleLabel.setFont(new Font("Tahoma", Font.BOLD, 40)); // Set font size and style
        titleLabel.setForeground(Color.BLACK); // Set text color
        titleLabel.setBounds(180, 20, getWidth(), 30); // Position the label at the top center of the screen


        add(titleLabel);
    }


    public static void main(String[] args){
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GameForm().setVisible(true);
            }
        });

    }
}
