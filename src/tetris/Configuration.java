package tetris;

import javax.swing.*;
import java.awt.*;

public class Configuration extends JFrame {

    public Configuration() {
        setTitle("Configuration");
        setSize(500, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 20, 10, 20); // Padding: top, left, bottom, right
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        gbc.anchor = GridBagConstraints.WEST;

        // Add Field Width Slider
        addComponent(new JLabel("Field Width (cells):"), gbc, 0, 0);
        JSlider fieldWidthSlider = createSlider(5, 15, 10);
        addComponent(fieldWidthSlider, gbc, 1, 0);

        // Add Field Height Slider
        addComponent(new JLabel("Field Height (cells):"), gbc, 0, 1);
        JSlider fieldHeightSlider = createSlider(15, 30, 20);
        addComponent(fieldHeightSlider, gbc, 1, 1);

        // Add Game Level Slider
        addComponent(new JLabel("Game Level:"), gbc, 0, 2);
        JSlider gameLevelSlider = createSlider(1, 10, 1);
        addComponent(gameLevelSlider, gbc, 1, 2);

        // Add Music CheckBox
        addComponent(new JLabel("Music:"), gbc, 0, 3);
        JCheckBox musicCheckBox = new JCheckBox("On");
        addComponent(musicCheckBox, gbc, 1, 3);

        // Add AI Play CheckBox
        addComponent(new JLabel("AI Play:"), gbc, 0, 4);
        JCheckBox aiPlayCheckBox = new JCheckBox("On");
        addComponent(aiPlayCheckBox, gbc, 1, 4);

        // Add Extend Mode CheckBox
        addComponent(new JLabel("Extend Mode:"), gbc, 0, 5);
        JCheckBox extendModeCheckBox = new JCheckBox("On");
        addComponent(extendModeCheckBox, gbc, 1, 5);

        // Back Button
        gbc.gridx = 0; // Reset to first column
        gbc.gridy = 6; // Place button in the last row
        gbc.gridwidth = 2; // Span across both columns
        gbc.anchor = GridBagConstraints.SOUTH; // Align button to the bottom
        gbc.insets = new Insets(20, 20, 20, 20); // Adjust padding for the button
        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> new MainMenu().setVisible(true));
        backButton.setBackground(Color.WHITE);
        backButton.setForeground(Color.BLACK);
        backButton.setFocusPainted(false);
        add(backButton, gbc);

    }

    private void addComponent(Component component, GridBagConstraints gbc, int x, int y) {
        gbc.gridx = x;
        gbc.gridy = y;
        add(component, gbc);
    }

    private JSlider createSlider(int min, int max, int initial) {
        JSlider slider = new JSlider(min, max, initial);
        slider.setMajorTickSpacing(1);
        slider.setMinorTickSpacing(1);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        return slider;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Configuration().setVisible(true));
    }
}
