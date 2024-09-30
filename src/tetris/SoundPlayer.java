package tetris;

import javax.sound.sampled.*;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SoundPlayer {
    private static SoundPlayer instance; // Singleton Instance
    private Clip eraseLineSound;
    private Clip gameFinishSound;
    private Clip backgroundMusic;
    private Clip levelUpSound;
    private Clip moveTurnSound;

    private SoundPlayer() {  //Constructor for singleton
        try {
            // Initialise Clips
            eraseLineSound = AudioSystem.getClip();
            gameFinishSound = AudioSystem.getClip();
            backgroundMusic = AudioSystem.getClip();
            levelUpSound = AudioSystem.getClip();
            moveTurnSound = AudioSystem.getClip();

            // Load audio files
            eraseLineSound.open(getAudioStream("/audiofiles/erase-line.wav"));
            gameFinishSound.open(getAudioStream("/audiofiles/game-finish.wav"));
            backgroundMusic.open(getAudioStream("/audiofiles/background.wav"));
            levelUpSound.open(getAudioStream("/audiofiles/level-up.wav"));
            moveTurnSound.open(getAudioStream("/audiofiles/move-turn.wav"));

            // Start background music loop, but only if not already playing
            startBackgroundMusic();

        } catch (LineUnavailableException | UnsupportedAudioFileException | IOException e) {
            Logger.getLogger(SoundPlayer.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    // Singleton getInstance method
    public static SoundPlayer getInstance() {
        if (instance == null) {
            instance = new SoundPlayer();
        }
        return instance;
    }

    private AudioInputStream getAudioStream(String path) throws UnsupportedAudioFileException, IOException {
        URL url = getClass().getResource(path);
        if (url == null) {
            throw new IOException("Audio file not found: " + path);
        }
        System.out.println("Resolved URL: " + url);  // Debugging
        return AudioSystem.getAudioInputStream(url);
    }

    public void playEraseLine() {
        if (eraseLineSound.isRunning()) {
            eraseLineSound.stop();
        }
        eraseLineSound.setFramePosition(0);
        eraseLineSound.start();
    }

    public void playGameFinish() {
        if (gameFinishSound.isRunning()) {
            gameFinishSound.stop();
        }
        gameFinishSound.setFramePosition(0);
        gameFinishSound.start();
    }

    public void playLevelUp() {
        if (levelUpSound.isRunning()) {
            levelUpSound.stop();
        }
        levelUpSound.setFramePosition(0);
        levelUpSound.start();
    }

    public void playMoveTurn() {
        if (moveTurnSound.isRunning()) {
            moveTurnSound.stop();
        }
        moveTurnSound.setFramePosition(0);
        moveTurnSound.start();
    }

    // Methods to control background music
    public void startBackgroundMusic() {
        if (!backgroundMusic.isRunning()) {
            backgroundMusic.loop(Clip.LOOP_CONTINUOUSLY);
        }
    }

    public void stopBackgroundMusic() {
        if (backgroundMusic.isRunning()) {
            backgroundMusic.stop();
        }
    }
}
