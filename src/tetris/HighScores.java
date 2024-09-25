package tetris;

import javax.swing.*;
import java.awt.*;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class HighScores extends JFrame {

    private static List<PlayerScore> highScores = new ArrayList<>(); // Store high scores
    private static final String FILE_PATH = "highscores.json";
    private static Gson gson = new Gson();

    public HighScores() {
        setTitle("High Scores");
        setSize(500, 600); // Increase size for the new column
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(20, 20));

        JLabel titleLabel = new JLabel("HIGH SCORES", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Tahoma", Font.BOLD, 30));
        titleLabel.setForeground(Color.BLACK);
        add(titleLabel, BorderLayout.NORTH);

        // Panel for No., names, scores, and config
        JPanel scoresPanel = new JPanel(new GridLayout(11, 4, 10, 10)); // GridLayout for 4 columns: No., Name, Score, Config
        JLabel noLabel = new JLabel("No.", SwingConstants.CENTER);
        JLabel nameLabel = new JLabel("Name", SwingConstants.CENTER);
        JLabel scoreLabel = new JLabel("Score", SwingConstants.CENTER);
        JLabel configLabel = new JLabel("Config", SwingConstants.CENTER);
        noLabel.setFont(new Font("Tahoma", Font.BOLD, 8));
        nameLabel.setFont(new Font("Tahoma", Font.BOLD, 8));
        scoreLabel.setFont(new Font("Tahoma", Font.BOLD, 8));
        configLabel.setFont(new Font("Tahoma", Font.BOLD, 8));
        scoresPanel.add(noLabel);
        scoresPanel.add(nameLabel);
        scoresPanel.add(scoreLabel);
        scoresPanel.add(configLabel);

        // Load high scores and display them
        loadHighScores();
        displayScores(scoresPanel);

        add(scoresPanel, BorderLayout.CENTER);

        // Back Button
        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> new MainMenu().setVisible(true));
        backButton.setBackground(Color.WHITE);
        backButton.setForeground(Color.BLACK);
        backButton.setFocusPainted(false);
        add(backButton, BorderLayout.SOUTH);
    }

    public static void addPlayer(String playerName, int score, String config) {
        // Add player and score to high scores list
        PlayerScore playerScore = new PlayerScore(playerName, score, config);
        highScores.add(playerScore);

        // Sort high scores by score (descending)
        highScores.sort(Comparator.comparingInt(PlayerScore::getScore).reversed());

        // Save high scores to file
        saveHighScores();

        // Show high scores window
        new HighScores().setVisible(true);
    }

    // Save high scores to JSON file
    private static void saveHighScores() {
        try (FileWriter writer = new FileWriter(FILE_PATH)) {
            gson.toJson(highScores, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Load high scores from JSON file
    private void loadHighScores() {
        try (FileReader reader = new FileReader(FILE_PATH)) {
            java.lang.reflect.Type highScoreListType = new TypeToken<ArrayList<PlayerScore>>() {}.getType();
            highScores = gson.fromJson(reader, highScoreListType);
        } catch (IOException e) {
            System.out.println("No high scores file found, creating new file.");
            highScores = new ArrayList<>();
        }
    }

    // Display high scores in the panel
    private void displayScores(JPanel scoresPanel) {
        for (int i = 0; i < Math.min(highScores.size(), 10); i++) {
            PlayerScore playerScore = highScores.get(i);
            scoresPanel.add(new JLabel(String.valueOf(i + 1), SwingConstants.CENTER)); // Display rank (No.)
            scoresPanel.add(new JLabel(playerScore.getName(), SwingConstants.CENTER));
            scoresPanel.add(new JLabel(String.valueOf(playerScore.getScore()), SwingConstants.CENTER));
            scoresPanel.add(new JLabel(playerScore.getConfig(), SwingConstants.CENTER)); // Display config
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new HighScores().setVisible(true));
    }
}