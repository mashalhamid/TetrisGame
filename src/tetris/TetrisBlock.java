package tetris;

import java.awt.*;
import java.util.Random;

public class TetrisBlock {
    private int [][] shape;
    private Color color;
    private int x, y;
    private int [][][] shapes;
    private int currentRotation;

    private Color[] availableColors = {
            new Color(0xBF00FF), // Electric Purple
            new Color(0xFF6EC7), // Neon Pink
            new Color(0xBFFF00), // Lime Green
            new Color(0x00FFFF), // Cyan Blue
            new Color(0xFFA500)  // Solar Orange
    };



    public TetrisBlock(int [][] shape){
        this.shape = shape;
        initShapes();
    }

    private void initShapes(){
        shapes = new int [4][][];
        for (int i = 0; i < 4; i++){
            int row = shape[0].length;
            int col = shape.length;
            shapes[i] = new int[row][col];

            for(int x = 0; x < row; x++){
                for(int y = 0; y < col; y++){
                    shapes[i][x][y] = shape [col-y-1][x];
                }
            }
            shape = shapes[i];
        }
    }

    public void create(int gridWidth){

        Random ran = new Random();

        currentRotation = ran.nextInt(4);
        shape = shapes [currentRotation];

        y = - getHeight();
        x = ran.nextInt(gridWidth - getWidth());

        color = availableColors[ran.nextInt(availableColors.length)];
    }

    public int [][] getShape(){
        return shape;
    }

    public Color getColor(){
        return color;
    }

    public int getHeight(){
        return shape.length;
    }

    public int getWidth(){
        return shape[0].length;
    }

    public int getX(){
        return x;
    }

    public void setX(int newX){
        x=newX;
    }

    public int getY(){
        return y;
    }

    public void setY(int newY) {
        y = newY;
    }

    // functions to move the block
    public void moveDown(){
        y++;
    }

    public void moveLeft(){
        x--;
    }

    public void moveRight(){
        x++;
    }

    public void rotate(){
        currentRotation++;
        if (currentRotation>3) currentRotation = 0;
        shape = shapes[currentRotation];
    }

    public int getBottomEdge(){
        return y + getHeight();
    }

    public int getLeftEdge(){
        return x;
    }

    public int getRightEdge(){
        return x+getWidth();
    }
}
