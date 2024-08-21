package tetris;

import javax.swing.*;
import java.awt.*;

public class GameArea extends JPanel
    {
        private int gridRows;
        private int gridColumns;
        private int gridCellSize;
        private Color[][] background;

        private TetrisBlock block; // tetris block

        public GameArea(int columns) //constructor
        {
            setBounds(50, 100, 200, 400); // Set bounds directly in the GameArea constructor
            setBorder(BorderFactory.createLineBorder(Color.black, 2));

            gridColumns = columns;
            gridCellSize = getBounds().width/gridColumns; // Calculate cell size based on width
            gridRows = getBounds().height/gridCellSize; // Calculate number of rows based on cell size and height

            background = new Color[gridRows][gridColumns];  //background array (for blocks that have landed at the end)

        }

        public void createBlock(){
            block = new TetrisBlock(new int [][]{{1,1,1},{0,1,0}, {0,1,0}}, Color.blue);
            block.create(gridColumns);
        }

        //does the check if the block moves down
        public boolean moveBlockDown(){
            if (!checkBottom()){
                moveBlockToBackground();
                return false;
            }

            block.moveDown();
            repaint();

            return true;
        }

        public void moveBlockRight(){
            block.moveRight();
            repaint();

        }

        public void moveBlockLeft(){
            block.moveLeft();
            repaint();
        }

        public void dropBlockInstantly(){
            while(checkBottom()){
                block.moveDown();
            }
            repaint();
        }

        public void rotateBlock(){

        }



        private boolean checkBottom(){
            if(block.getBottomEdge() == gridRows)
            {
                return false;
            }
            return true;
        }

        private void moveBlockToBackground(){
            int[][] shape = block.getShape();
            int height = block.getHeight();
            int width = block.getWidth();

            int xPos = block.getX();
            int yPos = block.getY();

            Color color = block.getColor();

            for (int row = 0; row<height; row++){
                for (int col = 0; col<width; col++){
                    if(shape[row][col] == 1){
                        background[row + yPos][col + xPos] = color;
                    }
                }
            }
        }

        private void drawBlock(Graphics g){

            int height = block.getHeight();
            int width = block.getWidth();
            Color color = block.getColor();
            int [][] shape = block.getShape();

            for (int row = 0; row < height; row++){
                for(int col = 0; col< width; col++){
                    if (shape[row][col]==1){ //1 means block is coloured

                        int x = (block.getX() + col) * gridCellSize;
                        int y = (block.getY() + row) * gridCellSize;

                        drawGridSquare(g,color,x,y);
                    }
                }
            }
        }

        private void drawBackground(Graphics g){
            Color color;

            for (int row = 0; row< gridRows; row++){
                for(int col = 0; col<gridColumns; col++){
                    color = background [row][col];

                    if(color != null){
                        int x = col * gridCellSize;
                        int y = row * gridCellSize;

                        drawGridSquare(g, color, x, y);
                    }
                }
            }
        }

        public void drawGridSquare(Graphics g, Color color, int x, int y){
            g.setColor(color);
            g.fillRect(x, y, gridCellSize, gridCellSize);
            g.setColor(Color.black);
            g.drawRect(x, y,gridCellSize, gridCellSize);
        }

    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g); //draws background
        drawBackground(g);
        drawBlock(g);

    }

}
