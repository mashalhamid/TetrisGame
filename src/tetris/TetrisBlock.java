package tetris;

import java.awt.*;

public class TetrisBlock {
    private int [][] shape;
    private Color color;
    private int x, y;

    public TetrisBlock(int [][] shape, Color color){
        this.shape = shape;
        this.color = color;
    }

    public void create(int gridWidth){
        y = 0 - getHeight();
        x = (gridWidth - getWidth()) / 2;
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

    public int getY(){
        return y;
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

    public int getBottomEdge(){
        return y + getHeight();
    }

}
