package Sokoban.Model;

public class GameSystem {
    private boolean victory;
    private boolean failed;
    private int time;
    private int steps;
    Player niker = new Player();
    private Box[]boxes;
    private Target[]targets;
    private int[][] matrix;



    public GameSystem(int boxNumber, int targetNumber,int width, int height) {
        victory = false;
        failed = false;
        time = 0;
        steps = 0;
        boxes = new Box[boxNumber];
        for(int i=0;i<boxNumber;i++){
            boxes[i] = new Box();
        }
        targets = new Target[targetNumber];
        for(int i=0;i<targetNumber;i++){
            targets[i] = new Target();
        }
        matrix = new int[height][width];

        //矩阵大小初始化，boxes和targets数组已经指向默认类对象
        /*
         * This class is to record the map of one game. For example:
         * matrix =
         * {1, 1,  1, 1,  1, 1},
         * {1, 20, 0, 0,  0, 1},
         * {1, 0,  0, 10, 2, 1},
         * {1, 0,  2, 10, 0, 1},
         * {1, 1,  1, 1,  1, 1}
         * The Unit digit number cannot be changed during one game.
         * 1 represents the wall
         * 0 represents the free space
         * 2 represents the target location
         * The Then digit number can be changed during one game.
         * 十位数 1 represents the box
         * 十位数 2 represents the player
         * So that 12 represents a box on the target location and 22 represents the player on the target location.
         */
    }
   //在controller的初始化方法中调用，量化矩阵
    public void setBoxPositons(){
        for(Box box : boxes){
             int x = box.getCurrentRow(); int y = box.getCurrentCol();
            matrix[x][y] =10;
        }
    }

    public void setPlayerPositons(int row,int col){
        matrix[row][col] = 20;
    }

    public void setBox(int i,int col,int row){
        boxes[i].setCurrentCol(col);
        boxes[i].setCurrentRow(row);
    }

    public void setTarget(int i,int row,int col){
        targets[i].setCurrentCol(col);
        targets[i].setCurrentRow(row);
    }

    public void moveNiker(int row, int col) {
        niker.setCurrentRow(row);
        niker.setCurrentCol(col);
        steps++;
    }

    public void moveBox(int i,int row,int col){
        boxes[i].setCurrentCol(col);
        boxes[i].setCurrentRow(row);
    }

    public boolean isVictory() {

        //判断当所有箱子是否都在目标位置
        return victory;
    }

    public boolean isFailed() {
        return failed;
    }




}
