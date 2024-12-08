package Sokoban.Model;

import java.io.Serializable;

public class Player implements Serializable {
    private int currentRow;
    private int currentCol;
    private int initialcol, initialrow;

    public void setInitialrow(int initialrow) {
        this.initialrow = initialrow;
    }

    public void setInitialcol(int initialcol) {
        this.initialcol = initialcol;
    }

    public int getInitialcol() {
        return initialcol;
    }

    public int getInitialrow() {
        return initialrow;
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
