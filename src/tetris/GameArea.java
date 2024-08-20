package tetris;

import javax.swing.*;
import java.awt.*;

public class GameArea extends JPanel
    {
        private int gridRows;
        private int gridColumns;
        private int gridCellSize;

        private int [] [] block = { {1,1,1}, {0,1,0}, {0,1,0} }; //t-shaped tetris block

        public GameArea(int columns)
        {
            setBounds(50, 100, 200, 400); // Set bounds directly in the GameArea constructor
//          setBackground(Color.red); //set bg of game area
            setBorder(BorderFactory.createLineBorder(Color.black, 2));

            gridColumns = columns;
            gridCellSize = this.getBounds().width/gridColumns; // Calculate cell size based on width
            gridRows = this.getBounds().height/gridCellSize; // Calculate number of rows based on cell size and height
        }

        private void drawBlock(Graphics g){
            for (int row = 0; row < block.length; row++){
                for(int col = 0; col< block [0].length; col++){
                    if (block[row][col]==1){ //1 means coloured
                        g.setColor(Color.red);
                        g.fillRect(col*gridCellSize, row*gridCellSize, gridCellSize, gridCellSize);
                        g.setColor(Color.black);
                        g.drawRect(col*gridCellSize, row*gridCellSize,gridCellSize, gridCellSize);
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
