package Sokoban.Model;
import javafx.scene.shape.Rectangle;
import java.io.*;
public class Board extends Rectangle implements Serializable{
    private int currentRow;
    private int currentCol;

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
