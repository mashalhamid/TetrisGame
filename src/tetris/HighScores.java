package tetris;

import javax.swing.*;
import java.awt.*;

public class HighScores extends JFrame {

    public HighScores() {
        setTitle("High Scores");
        setSize(500, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(20, 20)); // Add padding on the sides

        // Title
        JLabel titleLabel = new JLabel("HIGH SCORES", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Tahoma", Font.BOLD, 30));
        titleLabel.setForeground(Color.BLACK);
        add(titleLabel, BorderLayout.NORTH);

        // Panel for names and scores
        JPanel scoresPanel = new JPanel(new GridLayout(11, 2, 10, 10));
        JLabel nameLabel = new JLabel("Name", SwingConstants.CENTER);
        JLabel scoreLabel = new JLabel("Score", SwingConstants.CENTER);
        nameLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
        scoreLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
        scoresPanel.add(nameLabel);
        scoresPanel.add(scoreLabel);

        // Store name and scores in an array (hardcoded)
        String[] names = {"Draco", "Snape", "Luna", "Riddle", "Tom", "Lucious", "Ron", "Hermoine", "Harry", "Neville"};
        int[] scores = {10000, 9500, 8400, 8330, 8230, 7900, 7100, 4000, 2000, 1200};

        for (int i = 0; i < names.length; i++) {
            scoresPanel.add(new JLabel(names[i], SwingConstants.CENTER));
            scoresPanel.add(new JLabel(String.valueOf(scores[i]), SwingConstants.CENTER));
        }

        add(scoresPanel, BorderLayout.CENTER);

        // Back Button
        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> new MainMenu().setVisible(true));
        backButton.setBackground(Color.WHITE);
        backButton.setForeground(Color.BLACK);
        backButton.setFocusPainted(false);
        add(backButton, BorderLayout.SOUTH);

    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new HighScores().setVisible(true));
    }
}
