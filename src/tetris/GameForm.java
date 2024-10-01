package tetris;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class GameForm extends JFrame {

    private GameArea ga;
    private SoundPlayer soundPlayer;
    private JLabel playerTypeLabel;
    private JLabel initialLevelLabel;
    private JLabel currentLevelLabel;
    private JLabel scoreLabel;
    private JLabel linesErasedLabel;

    private int currentScore = 0;
    private int linesErased = 0;
    private int currentLevel = 1; // starting level
    private String playerType = "Human";
    private int initialLevel = 1; // Set initial level

    private static boolean isSoundEffectsOn = true;    // To track sound effects toggle

    // Constructor
    public GameForm() {
        setTitle("Tetris");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(500, 600);
        setLocationRelativeTo(null);
        setLayout(null); // Disable layout manager for absolute positioning

        // Initialise GameArea
        ga = new GameArea(10, this);  // Pass the GameForm instance (this)
        this.add(ga);

        // Use the Singleton instance:
        SoundPlayer soundPlayer = SoundPlayer.getInstance();


        // Add game status display at the top
        addGameStatusDisplay();

        // Add title
        addTitleLabel("PLAY");

        // Initialize controls
        initControls();

        // Start the game
        startGame();

        // Add back button
        backButton();
    }

    // Add the game status display (Player type, levels, score, etc.)
    private void addGameStatusDisplay() {
        JPanel statusPanel = new JPanel();
        statusPanel.setBounds(30, 70, 150, 400); // Set bounds for the status panel
        statusPanel.setLayout(new GridLayout(5, 2, 5, 5)); // 5 rows, 1 column with spacing

        // Create labels
        playerTypeLabel = new JLabel("Player: " + playerType);
        playerTypeLabel.setFont(new Font("Tahoma", Font.BOLD, 13)); // font size

        initialLevelLabel = new JLabel("Initial Level: " + initialLevel);
        initialLevelLabel.setFont(new Font("Tahoma", Font.BOLD, 13)); // font size

        currentLevelLabel = new JLabel("Current Level: " + currentLevel);
        currentLevelLabel.setFont(new Font("Tahoma", Font.BOLD, 13)); // font size

        scoreLabel = new JLabel("Score: " + currentScore);
        scoreLabel.setFont(new Font("Tahoma", Font.BOLD, 20)); // font size

        linesErasedLabel = new JLabel("Lines Erased: " + linesErased);
        linesErasedLabel.setFont(new Font("Tahoma", Font.BOLD, 13)); // font size

        // Add labels to panel
        statusPanel.add(playerTypeLabel);
        statusPanel.add(initialLevelLabel);
        statusPanel.add(currentLevelLabel);
        statusPanel.add(scoreLabel);
        statusPanel.add(linesErasedLabel);

        // Add status panel to the form
        add(statusPanel);
    }

    // Method to update the number of lines erased
    public void updateLinesErased(int lines) {
        linesErased += lines;
        linesErasedLabel.setText("Lines Erased: " + linesErased);

        // Increase level after every 10 lines erased
        if (linesErased % 10 == 0) {
            currentLevel++;
            currentLevelLabel.setText("Level: " + currentLevel);

            if(isSoundEffectsOn) {
                SoundPlayer.getInstance().playLevelUp(); // sound
            }
        }
    }

   // Method to update the score
    public void updateScore(int points) {
        currentScore += points;
        scoreLabel.setText("Score: " + currentScore);
    }

    // Method to update player type (AI, Human, etc.)
    public void updatePlayerType(String type) {
        playerType = type;
        playerTypeLabel.setText("Player: " + playerType);
    }

    // Initialize controls for Tetris
    private void initControls() {
        InputMap im = this.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap am = this.getRootPane().getActionMap();

        im.put(KeyStroke.getKeyStroke("RIGHT"), "right");
        im.put(KeyStroke.getKeyStroke("LEFT"), "left");
        im.put(KeyStroke.getKeyStroke("UP"), "up");
        im.put(KeyStroke.getKeyStroke("DOWN"), "down");
        im.put(KeyStroke.getKeyStroke("P"), "pause");
        im.put(KeyStroke.getKeyStroke("M"), "music");
        im.put(KeyStroke.getKeyStroke("S"), "sound");

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

        am.put("music", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ga.toggleBackgroundMusic();
            }
        });

        am.put("sound", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ga.toggleSoundEffects();
            }
        });


    }

    // Back button implementation
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
                triggerGameOver();
                dispose(); // Close the game form
//                new MainMenu().setVisible(true); // Return to the main menu
            } else {
                ga.togglePause();
                ga.repaint();
            }
        });
        add(backButton);
    }

    // Start the game
    public void startGame() {
        new GameThread(ga).start();
    }

    // Add title label at the top
    public void addTitleLabel(String text) {
        JLabel titleLabel = new JLabel(text);
        titleLabel.setFont(new Font("Tahoma", Font.BOLD, 40)); // Set font size and style
        titleLabel.setForeground(Color.BLACK); // Set text color
        titleLabel.setBounds(180, 10, getWidth(), 30); // Position the label at the top center of the screen
        add(titleLabel);
    }

    public void triggerGameOver() {
        Tetris.gameOver(currentScore);
    }

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> new GameForm().setVisible(true));
    }
}
