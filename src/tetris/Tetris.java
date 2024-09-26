package tetris;

import javax.swing.*;

public class Tetris {

    public static void gameOver(int score){
        String playerName = JOptionPane.showInputDialog("Game Over! \n Please enter your name");
        if (playerName != null && !playerName.trim().isEmpty()) {
            // Save the player's name and score
            String config = "Human";
            HighScores.addPlayer(playerName, score, config);

        }
    }


    public static void main(String[] args) {

        // Initialize and show the splash screen
        SwingUtilities.invokeLater(() -> {
            SplashScreen splash = new SplashScreen(2000); // 5 seconds splash duration
            splash.showSplash();
        });
    }
}
