package tetris;

import java.util.logging.Level;
import java.util.logging.Logger;

public class GameThread extends Thread {

    private GameArea ga;
    private GameForm gameForm;
    private int score = 0; // Add a score variable

    public GameThread(GameArea ga) {
        this.ga = ga;
    }

    @Override
    public void run() {
        while (true) {
            ga.createBlock(); // Create a new block when the current one stops
            while (ga.moveBlockDown()) {
                try {
                    Thread.sleep(1000); // Block moves every second
                } catch (InterruptedException ex) {
                    Logger.getLogger(GameThread.class.getName()).log(Level.SEVERE, null, ex);
                }
                synchronized (ga) {
                    while (ga.isPaused()) {
                        try {
                            ga.wait(); // Wait if the game is paused
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }

            if (ga.isBlockOutOfBounds()) {
                int score = ga.getScore(); // Fetch the current score
                Tetris.gameOver(score); // Pass the score when the game is over
                break;
            }

            ga.moveBlockToBackground();
            ga.clearRows();
        }

    }

}
