package tetris;

import javax.swing.*;
import java.awt.*;

public class GameArea extends JPanel
    {
        private int gridRows;
        private int gridColumns;
        private int gridCellSize;

        public GameArea(int columns)
        {
            setBounds(50, 100, 200, 400); // Set bounds directly in the GameArea constructor
//          setBackground(Color.red); //set bg of game area
            setBorder(BorderFactory.createLineBorder(Color.black, 2));

            gridColumns = columns;
            gridCellSize = this.getBounds().width/gridColumns; // Calculate cell size based on width
            gridRows = this.getBounds().height/gridCellSize; // Calculate number of rows based on cell size and height
        }

    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g); //draws background

    for(int row = 0; row < gridRows; row++) {
        for (int col = 0; col < gridColumns; col++) {
            g.drawRect(col * gridCellSize, row * gridCellSize, gridCellSize, gridCellSize);
        }
    }

    }

}
