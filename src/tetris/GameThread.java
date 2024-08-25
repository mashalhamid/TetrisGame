package tetris;

import java.util.logging.Level;
import java.util.logging.Logger;

public class GameThread extends Thread{

    private GameArea ga;

    public GameThread(GameArea ga){
        this.ga = ga;
    }

    @Override
    public void run() {
        while (true) {
            ga.createBlock(); // We only want to create a new block when the current block stops
            while (ga.moveBlockDown()) {
                try {
                    Thread.sleep(1000); // Program waits for a second (how fast block moves)
                } catch (InterruptedException ex) {
                    Logger.getLogger(GameThread.class.getName()).log(Level.SEVERE, null, ex);
                }
                synchronized (ga) {
                    while (ga.isPaused()) {
                        try {
                            ga.wait(); // Wait until the game is resumed
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }

            if (ga.isBlockOutOfBounds()) {
                System.out.println("Game Over");
                break;
            }

            ga.moveBlockToBackground();
            ga.clearRows();
        }
    }
}