package tetris;

import javax.swing.*;

public class Tetris {

    private static SoundPlayer audio = new SoundPlayer();

    public static void gameOver(int score){

        playGameFinishSound();

        String playerName = JOptionPane.showInputDialog("Game Over! \n Please enter your name");
        if (playerName != null && !playerName.trim().isEmpty()) {
            // Save the player's name and score
            String config = "Human";
            HighScores.addPlayer(playerName, score, config);

        }
    }


    // methods for playing sound
    public static void playEraseSound(){
        audio.playEraseLine();
    }

    public static void playGameFinishSound(){
        audio.playGameFinish();
    }

    public static void main(String[] args) {

        // Initialize and show the splash screen
        SwingUtilities.invokeLater(() -> {
            SplashScreen splash = new SplashScreen(2000); // 5 seconds splash duration
            splash.showSplash();
        });
    }
}
