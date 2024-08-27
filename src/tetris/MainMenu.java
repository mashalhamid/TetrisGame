package tetris;

import javax.swing.*;
import java.awt.*;

public class MainMenu extends JFrame {

    public MainMenu() {
        setTitle("Main Menu");
        setSize(500, 550);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the frame
        setLayout(new GridBagLayout());

        //creating a Jpanel to add buttons
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 1, 50, 50)); // padding between buttons


        JButton playButton = createStyledButton("Play");
        playButton.addActionListener(e -> {
            new GameForm().setVisible(true);
            dispose(); // closes the menu
        });

        JButton configButton = createStyledButton("Configuration");
        JButton highScoresButton = createStyledButton("High Scores");
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
    }

    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setPreferredSize(new Dimension(200, 50));  // Set smaller size for the button
        button.setBackground(Color.WHITE);
        button.setForeground(Color.BLACK);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createLineBorder(Color.lightGray, 1, true));  // Add rounded border

        return button;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new MainMenu().setVisible(true);
        });
    }
}