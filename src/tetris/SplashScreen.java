package tetris;

import javax.swing.*;
import java.awt.*;

public class SplashScreen extends JWindow {

    private int duration;

    public SplashScreen(int duration) {
        this.duration = duration;
        createSplash();
    }

    private void createSplash() {
        JPanel content = (JPanel) getContentPane();

        // Set window size and position
        int width = 346;
        int height = 559;
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (screen.width - width) / 2;
        int y = (screen.height - height) / 2;
        setBounds(x, y, width, height);

        // Load and scale the image
        ImageIcon originalIcon = new ImageIcon("src/tetris/splash.png");
        Image originalImage = originalIcon.getImage();
        Image scaledImage = originalImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);

        // Load and scale the image
        ImageIcon icon = new ImageIcon("src/tetris/splash.png");
        Image img = icon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);

        // Add scaled image to the splash screen
        JLabel label = new JLabel(new ImageIcon(img));
        add(label);


        // Make splash screen visible
        setVisible(true);
    }

    public void showSplash() {
        // Use a Timer to hide the splash screen after the duration
        new Timer(duration, e -> {
            setVisible(false);
            ((Timer) e.getSource()).stop();
            // Start the main application after splash screen disappears
            SwingUtilities.invokeLater(() -> {
                GameForm gameForm = new GameForm();
                gameForm.setVisible(true);
            });
        }).start();
    }
}
