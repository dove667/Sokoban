package Sokoban.Model;
import java.io.*;
public class Player implements Serializable {
    private int currentRow=1;
    private int currentCol=1;

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
