package tetris;

import javax.swing.*;
import java.awt.*;

public class Configuration extends JFrame {

    private SoundPlayer soundPlayer;  // Reference to SoundPlayer
    private JCheckBox musicCheckBox;

    public Configuration() {
        //Use the Singleton instance for sound
        SoundPlayer soundPlayer = SoundPlayer.getInstance();

        setTitle("Configuration");
        setSize(500, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridBagLayout());

        // Title Label Configuration
        GridBagConstraints titleGbc = new GridBagConstraints();
        titleGbc.gridx = 0;
        titleGbc.gridy = 0;
        titleGbc.gridwidth = 2; // Span across both columns
        titleGbc.anchor = GridBagConstraints.CENTER; // Center the label
        titleGbc.insets = new Insets(10, 0, 20, 0); // Padding for top, left, bottom, right

        JLabel titleLabel = new JLabel("CONFIGURATION");
        titleLabel.setFont(new Font("Tahoma", Font.BOLD, 30)); // Set font size and style
        titleLabel.setForeground(Color.BLACK); // Set text color
        add(titleLabel, titleGbc);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 20, 10, 20); // Padding for top, left, bottom, right
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        gbc.anchor = GridBagConstraints.WEST;

        // Add Field Width Slider
        addComponent(new JLabel("Field Width (cells):"), gbc, 0, 1);
        JSlider fieldWidthSlider = createSlider(5, 15, 10);
        addComponent(fieldWidthSlider, gbc, 1, 1);
        JLabel fieldWidthValueLabel = new JLabel(String.valueOf(fieldWidthSlider.getValue()));  // Create a label for the Field Width slider and set its initial value
        addComponent(fieldWidthValueLabel, gbc, 2, 1); // Add label next to the slider
        fieldWidthSlider.addChangeListener(e -> fieldWidthValueLabel.setText(String.valueOf(fieldWidthSlider.getValue()))); // Add ChangeListener to update the label when the slider is adjusted


        // Add Field Height Slider
        addComponent(new JLabel("Field Height (cells):"), gbc, 0, 2);
        JSlider fieldHeightSlider = createSlider(15, 30, 20);
        addComponent(fieldHeightSlider, gbc, 1, 2);
        JLabel fieldHeightValueLabel = new JLabel(String.valueOf(fieldHeightSlider.getValue())); // Create a label for the Field Height slider and set its initial value
        addComponent(fieldHeightValueLabel, gbc, 2, 2); // Add label next to the slider
        fieldHeightSlider.addChangeListener(e -> fieldHeightValueLabel.setText(String.valueOf(fieldHeightSlider.getValue()))); // Add ChangeListener to update the label when the slider is adjusted


        // Add Game Level Slider
        addComponent(new JLabel("Game Level:"), gbc, 0, 3);
        JSlider gameLevelSlider = createSlider(1, 10, 1);
        addComponent(gameLevelSlider, gbc, 1, 3);
        JLabel gameLevelValueLabel = new JLabel(String.valueOf(gameLevelSlider.getValue())); // Create a label for the Game Level slider and set its initial value
        addComponent(gameLevelValueLabel, gbc, 2, 3); // Add label next to the slider
        gameLevelSlider.addChangeListener(e -> gameLevelValueLabel.setText(String.valueOf(gameLevelSlider.getValue()))); // Add ChangeListener to update the label when the slider is adjusted

        SoundPlayer.getInstance().stopBackgroundMusic();
        // Add Music CheckBox
        addComponent(new JLabel("Music(ON|OFF):"), gbc, 0, 4);
        musicCheckBox = new JCheckBox("OFF"); // Initialise with "Off"
        musicCheckBox.addActionListener(e -> {
            if (musicCheckBox.isSelected()) {
                musicCheckBox.setText("ON");
                SoundPlayer.getInstance().setMusicEnabled(true); // Enable background music
            } else {
                musicCheckBox.setText("OFF");
                SoundPlayer.getInstance().setMusicEnabled(false); // Disable background music
            }
        });
        addComponent(musicCheckBox, gbc, 1, 4);

        // Add Sound Effects CheckBox
        addComponent(new JLabel("Sound Effect(ON|OFF): "), gbc, 0, 5);
        JCheckBox soundEffectsCheckBox = new JCheckBox("OFF");
        soundEffectsCheckBox.addActionListener(e -> {
            // Toggle sound effects based on checkbox state
            if (soundEffectsCheckBox.isSelected()) {
                soundEffectsCheckBox.setText("ON"); // Set text to "On" when selected
                // Enable sound effects (this could include logic to play sound effects)
            } else {
                soundEffectsCheckBox.setText("OFF");
                // Disable sound effects (this could include logic to stop sound effects)
            }
        });
        addComponent(soundEffectsCheckBox, gbc, 1, 5);

        // Add AI Play CheckBox
        addComponent(new JLabel("AI Play:"), gbc, 0, 6);
        JCheckBox aiPlayCheckBox = new JCheckBox("OFF");
        aiPlayCheckBox.addActionListener(e -> {
            if (aiPlayCheckBox.isSelected()) {
                aiPlayCheckBox.setText("ON"); // Set text to "On" when selected
            } else {
                aiPlayCheckBox.setText("OFF"); // Set text to "Off" when unselected
            }
        });
        addComponent(aiPlayCheckBox, gbc, 1, 6);

        // Add Extend Mode CheckBox
        addComponent(new JLabel("Extend Mode:"), gbc, 0, 7);
        JCheckBox extendModeCheckBox = new JCheckBox("OFF"); // Initialize with "Off"
        extendModeCheckBox.addActionListener(e -> {
            if (extendModeCheckBox.isSelected()) {
                extendModeCheckBox.setText("ON"); // Set text to "On" when selected
            } else {
                extendModeCheckBox.setText("OFF"); // Set text to "Off" when unselected
            }
        });
        addComponent(extendModeCheckBox, gbc, 1, 7);

        // Back Button
        gbc.gridx = 0; // Reset to first column
        gbc.gridy = 8; // Place button in the last row
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
