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

        int width = 346;
        int height = 559;
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (screen.width - width) / 2;
        int y = (screen.height - height) / 2;
        setBounds(x, y, width, height);

        ImageIcon originalIcon = new ImageIcon("src/tetris/splash.png");
        Image originalImage = originalIcon.getImage();
        Image scaledImage = originalImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);

        ImageIcon icon = new ImageIcon(scaledImage);
        JLabel label = new JLabel(icon);
        add(label);

        setVisible(true);
    }

    public void showSplash() {
        new Timer(duration, e -> {
            setVisible(false);
            ((Timer) e.getSource()).stop();
            // Start the main menu after the splash screen disappears
            SwingUtilities.invokeLater(() -> {
                new MainMenu().setVisible(true);
            });
        }).start();
    }
}
