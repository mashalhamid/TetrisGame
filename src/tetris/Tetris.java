package tetris;

import javax.swing.*;

public class Tetris {

    // Use the Singleton instance:
    SoundPlayer soundPlayer = SoundPlayer.getInstance();

    public static void gameOver(int score){
        playGameFinishSound();

        SoundPlayer.getInstance().stopBackgroundMusic(); // sound

        // Ask for the player's name when the game ends
        String playerName = JOptionPane.showInputDialog("Game Over! \n Please enter your name");
        if (playerName != null && !playerName.trim().isEmpty()) {
            String config = "Human";

            HighScores.addPlayer(playerName, score, config);  // Save the player's score
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
