package tetris;
import tetrisblocks.*;
import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class GameArea extends JPanel {
    private int gridRows;
    private int gridColumns;
    private int gridCellSize;
    private Color[][] background;
    private TetrisBlock block; // tetris block
    private TetrisBlock[] blocks;
    private GameForm gameForm;  // Reference to GameForm
    private SoundPlayer soundPlayer;

    private boolean isPaused = false; // Flag to indicate if the game is paused
    private static boolean isBackgroundMusicOn = true; // To track background music toggle
    private static boolean isSoundEffectsOn = true;    // To track sound effects toggle



    private int score = 0;  // Track player's score
    private int level = 1;  // Track player's level
    private int rowsCleared = 0;  // Track total rows cleared
    private int columns;



    public GameArea(int columns ,GameForm gameForm) //constructor
    {
        // Use the Singleton instance:
        SoundPlayer soundPlayer = SoundPlayer.getInstance();

        this.columns = columns;
        this.gameForm = gameForm;  // Store reference to GameForm
        setBounds(130, 70, 200, 400);
        setBorder(BorderFactory.createLineBorder(Color.lightGray, 2));

        gridColumns = columns;
        gridCellSize = getBounds().width / gridColumns; // Calculate cell size based on width
        gridRows = getBounds().height / gridCellSize; // Calculate number of rows based on cell size and height

        background = new Color[gridRows][gridColumns];  //background array (for blocks that have landed at the end)

        blocks = new TetrisBlock[]{new IShape(),
                new LShape(),
                new JShape(),
                new CubeShape(),
                new SShape(),
                new TShape(),
                new ZShape(),
        };
    }

    public void createBlock() {
        Random ran = new Random();
        block = blocks[ran.nextInt(blocks.length)];
        block.create(gridColumns);
    }

    public boolean isBlockOutOfBounds() {
        if (block.getY() < 0) {
            block = null;
            return true;
        }
        return false;
    }

    public boolean moveBlockDown() {
        if (isPaused) return true; // Allow block to fall while paused

        if (!checkBottom()) {
            return false;
        }

        block.moveDown();
        repaint();
        return true;
    }

    public void moveBlockRight() {
        if (isPaused) return; // Ignore movement if paused

        if (block == null) return;

        if (!checkRight()) return;

        block.moveRight();
        if(isSoundEffectsOn) {
            SoundPlayer.getInstance().playMoveTurn(); // sound
        }
        repaint();
    }

    public void moveBlockLeft() {
        if (isPaused) return; // Ignore movement if paused

        if (block == null) return;

        if (!checkLeft()) return;

        block.moveLeft();
        if(isSoundEffectsOn) {
            SoundPlayer.getInstance().playMoveTurn(); // sound
        }
        repaint();
    }

    public void dropBlockInstantly() {
        if (isPaused) return; // Ignore instant drop if paused

        if (block == null) return;

        while (checkBottom()) {
            block.moveDown();
        }
        if(isSoundEffectsOn) {
            SoundPlayer.getInstance().playMoveTurn(); // sound
        }
        repaint();
    }

    public void rotateBlock() {
        if (isPaused) return; // Ignore rotation if paused

        if (block == null) return;
        block.rotate();
        if(isSoundEffectsOn) {
            SoundPlayer.getInstance().playMoveTurn(); // sound
        }

        // To check if block goes outside
        if (block.getLeftEdge() < 0) block.setY(0);
        if (block.getRightEdge() >= gridColumns) block.setX(gridColumns - block.getWidth());
        if (block.getBottomEdge() >= gridRows) block.setY(gridRows - block.getHeight());

        repaint();
    }

    public void togglePause() {
        isPaused = !isPaused;
        synchronized (this) {
            if (!isPaused) {
                notify(); // Notify the waiting thread to resume
            }
        }

        if (isPaused) {
            if(isBackgroundMusicOn) {
                SoundPlayer.getInstance().stopBackgroundMusic(); // Stop background music when paused
            }
        } else {
            if( isBackgroundMusicOn) {
                SoundPlayer.getInstance().startBackgroundMusic(); // Stop background music when paused
            }
        }

    }


    public boolean isPaused() {
        return isPaused;
    }

    // Method to toggle background music
    public void toggleBackgroundMusic() {
        if (isBackgroundMusicOn) {
            SoundPlayer.getInstance().stopBackgroundMusic();
        } else {
            SoundPlayer.getInstance().startBackgroundMusic();
        }
        isBackgroundMusicOn = !isBackgroundMusicOn; // Toggle the flag
    }

    // Method to toggle sound effects
    public void toggleSoundEffects() {
        isSoundEffectsOn = !isSoundEffectsOn; // Toggle the flag
    }

    private boolean checkBottom() {
        if (block.getBottomEdge() == gridRows) {
            return false;
        }
        int[][] shape = block.getShape();
        int width = block.getWidth();
        int height = block.getHeight();

        for (int col = 0; col < width; col++) {
            for (int row = height - 1; row >= 0; row--) {
                if (shape[row][col] != 0) {
                    int x = col + block.getX();
                    int y = row + block.getY() + 1;
                    if (y < 0) break;
                    if (background[y][x] != null) {
                        return false;
                    }
                    break;
                }
            }
        }

        return true;
    }

    private boolean checkLeft() {
        if (block.getLeftEdge() == 0) {
            return false;
        }

        int[][] shape = block.getShape();
        int width = block.getWidth();
        int height = block.getHeight();

        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                if (shape[row][col] != 0) {
                    int x = col + block.getX() - 1;
                    int y = row + block.getY();
                    if (y < 0) break;
                    if (background[y][x] != null) {
                        return false;
                    }
                    break;
                }
            }
        }
        return true;
    }

    private boolean checkRight() {
        if (block.getRightEdge() == gridColumns) {
            return false;
        }
        int[][] shape = block.getShape();
        int width = block.getWidth();
        int height = block.getHeight();

        for (int row = 0; row < height; row++) {
            for (int col = width - 1; col >= 0; col--) {
                if (shape[row][col] != 0) {
                    int x = col + block.getX() + 1;
                    int y = row + block.getY();
                    if (y < 0) break;
                    if (background[y][x] != null) {
                        return false;
                    }
                    break;
                }
            }
        }
        return true;

    }

    public void clearRows() {
        int rowsClearedThisTurn = 0;
        boolean rowComplete;

        for (int row = gridRows - 1; row >= 0; row--) {
            rowComplete = true;
            for (int col = 0; col < gridColumns; col++) {
                if (background[row][col] == null) {
                    rowComplete = false;
                    break;
                }
            }
            if (rowComplete) {
                clearRow(row);
                shiftRowDown(row);
                clearRow(0);
                row++; //  increment to check the last index row to remove every row
//                repaint();
                rowsClearedThisTurn++;
            }
        }
        if (rowsClearedThisTurn > 0) {
            int points = 0;
            switch (rowsClearedThisTurn) {
                case 1:
                    points = 100;
                    break;
                case 2:
                    points = 300;
                    break;
                case 3:
                    points = 600;
                    break;
                case 4:
                    points = 1000;

            }

            // Update score in GameForm
            gameForm.updateScore(points);

            //Update score in PlayerScore

            // Update lines erased in GameForm
            gameForm.updateLinesErased(rowsClearedThisTurn);

            if(rowsClearedThisTurn>0){
                if(isSoundEffectsOn) {
                    SoundPlayer.getInstance().playEraseLine(); // sound
                }
            }
        }
    }

    private void clearRow(int row) {
        for (int i = 0; i < gridColumns; i++) {
            background[row][i] = null;
        }
    }

    public void shiftRowDown(int r) {
        for (int row = r; row > 0; row--) {
            for (int col = 0; col < gridColumns; col++) {
                background[row][col] = background[row - 1][col];
            }
        }
    }

    public void moveBlockToBackground() {
        int[][] shape = block.getShape();
        int height = block.getHeight();
        int width = block.getWidth();

        int xPos = block.getX();
        int yPos = block.getY();

        Color color = block.getColor();

        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                if (shape[row][col] == 1) {
                    background[row + yPos][col + xPos] = color;
                }
            }
        }
    }

    private void drawBlock(Graphics g) {

        int height = block.getHeight();
        int width = block.getWidth();
        Color color = block.getColor();
        int[][] shape = block.getShape();

        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                if (shape[row][col] == 1) { //1 means block is coloured

                    int x = (block.getX() + col) * gridCellSize;
                    int y = (block.getY() + row) * gridCellSize;

                    drawGridSquare(g, color, x, y);
                }
            }
        }
    }

    private void drawBackground(Graphics g) {
        Color color;

        for (int row = 0; row < gridRows; row++) {
            for (int col = 0; col < gridColumns; col++) {
                color = background[row][col];

                if (color != null) {
                    int x = col * gridCellSize;
                    int y = row * gridCellSize;

                    drawGridSquare(g, color, x, y);
                }
            }
        }
    }

    public void drawGridSquare(Graphics g, Color color, int x, int y) {
        g.setColor(color);
        g.fillRect(x, y, gridCellSize, gridCellSize);
        g.setColor(Color.black);
        g.drawRect(x, y, gridCellSize, gridCellSize);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); //draws background
        drawBackground(g);
        drawBlock(g);

        if (isPaused) {
            drawPauseMessage(g);
        }
    }

    private void drawPauseMessage(Graphics g) {
        String message = "Game is paused\nPress P to continue";
        Font font = new Font("Arial", Font.BOLD, 10);
        g.setFont(font);
        FontMetrics metrics = g.getFontMetrics(font);
        int x = (getWidth() - metrics.stringWidth(message)) / 2;
        int y = getHeight() / 2;

        g.setColor(Color.BLACK);
        g.drawString("Game is paused", x, y);
        g.drawString("Press P to continue", x, y + 30); // Draw the second line below the first
    }
}
