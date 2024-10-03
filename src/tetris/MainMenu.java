package tetris;

import javax.swing.*;
import java.awt.*;

public class MainMenu extends JFrame {

    public MainMenu() {
        setTitle("Main Menu");
        setSize(500, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the frame
        setLayout(null);

        //Creating a Jpanel to add buttons
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 1, 50, 50)); // padding between buttons
        panel.setBounds(130, 120, 250, 350); // Set bounds for the panel

        //Play Game
        JButton playButton = createStyledButton("Play");
        playButton.addActionListener(e -> {
            new GameForm().setVisible(true);
            dispose(); // closes the menu
        });

        //Configuration
        JButton configButton = createStyledButton("Configuration");
        configButton.addActionListener(e -> {
            new Configuration().setVisible(true);
            dispose(); // closes the menu
        });

        //High Scores
        JButton highScoresButton = createStyledButton("High Scores");
        highScoresButton.addActionListener(e -> {
            new HighScores().setVisible(true);
            dispose(); // closes the menu
        });


        // Exit Game
        JButton exitButton = createStyledButton("Exit");
        exitButton.addActionListener(e -> {
            int result = JOptionPane.showConfirmDialog(
                    this,
                    "Are you sure you want to exit?",
                    "Exit Game",
                    JOptionPane.YES_NO_OPTION
            );
            if (result == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        });

        panel.add(playButton);
        panel.add(configButton);
        panel.add(highScoresButton);
        panel.add(exitButton);


        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(20, 20, 20, 20); // Add some padding around the panel

        add(panel, gbc);
        addTitleLabel("Main Menu");
    }

    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setBackground(Color.WHITE);
        button.setForeground(Color.BLACK);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createLineBorder(Color.lightGray, 1, true));  // Add rounded border
        button.setHorizontalAlignment(SwingConstants.CENTER);


        return button;
    }

    public void addTitleLabel(String text) {
        JLabel titleLabel = new JLabel(text);
        titleLabel.setFont(new Font("Tahoma", Font.BOLD, 40));
        titleLabel.setForeground(Color.BLACK); // Set text color
        titleLabel.setBounds(0, 20, getWidth(), 50);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);


        add(titleLabel);
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new MainMenu().setVisible(true);
        });
    }
}