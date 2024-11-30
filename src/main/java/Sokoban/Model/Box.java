package Sokoban.Model;
import javafx.scene.Node;
import javafx.scene.shape.Rectangle;

import java.io.*;
public class Box extends Rectangle implements Serializable{

    private int currentRow;
    private int currentCol;
    private boolean movable;
    private Rectangle rectangle;  // 用于引用 JavaFX 的 Rectangle

    public boolean isMovable() {
        return movable;
    }

    public void setMovable(boolean movable) {
        this.movable = movable;
    }

    public int getCurrentRow() {
        return currentRow;
    }

    public void setCurrentRow(int currentRow) {
        this.currentRow = currentRow;
    }

    public int getCurrentCol() {
        return currentCol;
    }

    public void setCurrentCol(int currentCol) {
        this.currentCol = currentCol;
    }


}
