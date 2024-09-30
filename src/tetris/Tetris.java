package tetris;

import javax.swing.*;

public class Tetris {

    // Use the Singleton instance:
    SoundPlayer soundPlayer = SoundPlayer.getInstance();

    public static void gameOver(int score){

        playGameFinishSound();

        String playerName = JOptionPane.showInputDialog("Game Over! \n Please enter your name");
        if (playerName != null && !playerName.trim().isEmpty()) {
            // Save the player's name and score
            String config = "Human";  // Adjust this as needed to match the actual game config
            HighScores.addPlayer(playerName, score, config);
        }
    }

    // methods for playing sound
    public static void playGameFinishSound(){
        SoundPlayer.getInstance().playGameFinish();
    }

    public static void main(String[] args) {

        // Initialize and show the splash screen
        SwingUtilities.invokeLater(() -> {
            SplashScreen splash = new SplashScreen(2000); // 5 seconds splash duration
            splash.showSplash();
        });
    }
}
