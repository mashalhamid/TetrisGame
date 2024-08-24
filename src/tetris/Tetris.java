package tetris;

import javax.swing.*;

public class Tetris {
    public static void main(String[] args) {
        // Initialize and show the splash screen
        SwingUtilities.invokeLater(() -> {
            SplashScreen splash = new SplashScreen(5000); // 5 seconds splash duration
            splash.showSplash();
        });
    }
}
