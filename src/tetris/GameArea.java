package tetris;

import javax.swing.*;
import java.awt.*;

public class GameArea extends JPanel
    {
        private int gridRows;
        private int gridColumns;
        private int gridCellSize;

        private TetrisBlock block; // tetris block

        public GameArea(int columns)
        {
            setBounds(50, 100, 200, 400); // Set bounds directly in the GameArea constructor
            setBorder(BorderFactory.createLineBorder(Color.black, 2));

            gridColumns = columns;
            gridCellSize = this.getBounds().width/gridColumns; // Calculate cell size based on width
            gridRows = this.getBounds().height/gridCellSize; // Calculate number of rows based on cell size and height

            spawnBlock();
        }

        public void spawnBlock(){
            block = new TetrisBlock(new int [][]{{1,1,1},{0,1,0}, {0,1,0}}, Color.blue);
        }

        public void moveBlockDown(){
            block.moveDown();
            repaint();
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

                        g.setColor(color);
                        g.fillRect(x, y, gridCellSize, gridCellSize);
                        g.setColor(Color.black);
                        g.drawRect(x, y,gridCellSize, gridCellSize);
                    }
                }
            }
        }

    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g); //draws background

        //Grid lines
//        for(int row = 0; row < gridRows; row++) {
//            for (int col = 0; col < gridColumns; col++) {
//                g.drawRect(col * gridCellSize, row * gridCellSize, gridCellSize, gridCellSize);
//            }
//        }

        drawBlock(g);

    }

}
