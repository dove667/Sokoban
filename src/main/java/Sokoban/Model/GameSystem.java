package Sokoban.Model;

import java.io.*;
import java.net.URL;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import static Sokoban.Login_Application.primaryStage;

public class GameSystem implements Serializable {

    private boolean isvictory;
    private boolean isfailed;
    private int time;
    private int steps;
    Player niker ;
    private Box[]boxes;
    private final Board[] boards;
    private final Target[]targets;
    private final int[][] matrix;
    private static int currentLevel;
    private static String CurrentLevel ;
    private static boolean isVisitor;
    private final Set<String> walls = new HashSet<>();//集合类，单一性，无序，null允许

    public GameSystem(int boxNumber, int targetNumber,int boardNumber, int width, int height) {
        isvictory = false;
        isfailed = false;
        time = 0;
        steps = 0;
        boxes = new Box[boxNumber];
        for (int i = 0; i < boxNumber; i++) {
            boxes[i] = new Box();
            boxes[i].setMovable(true);
        }
        targets = new Target[targetNumber];
        for (int i = 0; i < targetNumber; i++) {
            targets[i] = new Target();
        }
        boards = new Board[boardNumber];
        for (int i = 0; i < boardNumber; i++) {
            boards[i] = new Board();
        }
        matrix = new int[height][width];
        niker = new Player();
    }

    public void setBox(int i,int col,int row){
        boxes[i-1].setCurrentCol(col);
        boxes[i-1].setCurrentRow(row);
        boxes[i-1].setId(i);
    }
    public void setBoard(int i,int col,int row) {
        boards[i].setCurrentCol(col);
        boards[i].setCurrentRow(row);
    }
    public void setTarget(int i,int col,int row){
        targets[i].setCurrentCol(col);
        targets[i].setCurrentRow(row);
    }
    public void setPlayer(int col,int row){
        niker.setCurrentCol(col);
        niker.setCurrentRow(row);
    }


    public void victoryJudge() throws IOException {
        int count =0;
        for(Target target : targets){
            for(Box box : boxes){
                if(target.getCurrentCol() == box.getCurrentCol() && target.getCurrentRow() == box.getCurrentRow()) {
                    count++;
                }
            }
        }
        if(count == targets.length) {
            isvictory = true;
            URL url = getClass().getResource("/Sokoban/Victory.fxml");
            Parent root = FXMLLoader.load(Objects.requireNonNull(url));
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
        }
    }

    public void failedJudge() throws IOException {
        int count =0;
        for(Box box : boxes){
            judgeBoxMovable(box);
            if(!box.isMovable()){
                count++;
            }
        }
        if(count == boxes.length){
            isfailed = true;
            URL url = getClass().getResource("/Sokoban/Failed.fxml");
            Parent root = FXMLLoader.load(Objects.requireNonNull(url));
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
        }
    }

    public void judgeBoxMovable(Box box) throws IOException {
        //左右，上下各有一面时board时
        if((matrix[box.getCurrentRow()-1][box.getCurrentCol()] == 1 && matrix[box.getCurrentRow()][box.getCurrentCol()-1] == 1)
        || (matrix[box.getCurrentRow()-1][box.getCurrentCol()] == 1 && matrix[box.getCurrentRow()][box.getCurrentCol()+1] == 1)
        || (matrix[box.getCurrentRow()+1][box.getCurrentCol()] == 1 && matrix[box.getCurrentRow()][box.getCurrentCol()-1] == 1)
        || (matrix[box.getCurrentRow()+1][box.getCurrentCol()] == 1 && matrix[box.getCurrentRow()][box.getCurrentCol()+1] == 1)){
            box.setMovable(false);
        }
        //左右，上下一面board一面box时(1--10,12)4
        if(matrix[box.getCurrentRow()-1][box.getCurrentCol()] == 1 && matrix[box.getCurrentRow()][box.getCurrentCol()-1] == 10
        || matrix[box.getCurrentRow()-1][box.getCurrentCol()] == 1 && matrix[box.getCurrentRow()][box.getCurrentCol()+1] == 10
        || matrix[box.getCurrentRow()+1][box.getCurrentCol()] == 1 && matrix[box.getCurrentRow()][box.getCurrentCol()-1] == 10
        || matrix[box.getCurrentRow()+1][box.getCurrentCol()] == 1 && matrix[box.getCurrentRow()][box.getCurrentCol()+1] == 10
        || matrix[box.getCurrentRow()][box.getCurrentCol()-1] == 1 && matrix[box.getCurrentRow()-1][box.getCurrentCol()] == 10
        || matrix[box.getCurrentRow()][box.getCurrentCol()-1] == 1 && matrix[box.getCurrentRow()+1][box.getCurrentCol()] == 10
        || matrix[box.getCurrentRow()][box.getCurrentCol()+1] == 1 && matrix[box.getCurrentRow()-1][box.getCurrentCol()] == 10
        || matrix[box.getCurrentRow()][box.getCurrentCol()+1] == 1 && matrix[box.getCurrentRow()+1][box.getCurrentCol()] == 10
        ||matrix[box.getCurrentRow()-1][box.getCurrentCol()] == 1 && matrix[box.getCurrentRow()][box.getCurrentCol()-1] == 12
        || matrix[box.getCurrentRow()-1][box.getCurrentCol()] == 1 && matrix[box.getCurrentRow()][box.getCurrentCol()+1] == 12
        || matrix[box.getCurrentRow()+1][box.getCurrentCol()] == 1 && matrix[box.getCurrentRow()][box.getCurrentCol()-1] == 12
        || matrix[box.getCurrentRow()+1][box.getCurrentCol()] == 1 && matrix[box.getCurrentRow()][box.getCurrentCol()+1] == 12
        || matrix[box.getCurrentRow()][box.getCurrentCol()-1] == 1 && matrix[box.getCurrentRow()-1][box.getCurrentCol()] == 12
        || matrix[box.getCurrentRow()][box.getCurrentCol()-1] == 1 && matrix[box.getCurrentRow()+1][box.getCurrentCol()] == 12
        || matrix[box.getCurrentRow()][box.getCurrentCol()+1] == 1 && matrix[box.getCurrentRow()-1][box.getCurrentCol()] == 12
        || matrix[box.getCurrentRow()][box.getCurrentCol()+1] == 1 && matrix[box.getCurrentRow()+1][box.getCurrentCol()] == 12){
            box.setMovable(false);
        }
        //左右，上下各一面box时(10,12--10,12)4
       if(matrix[box.getCurrentRow()-1][box.getCurrentCol()] == 1 && matrix[box.getCurrentRow()][box.getCurrentCol()-1] == 10
               || matrix[box.getCurrentRow()-1][box.getCurrentCol()] == 10 && matrix[box.getCurrentRow()][box.getCurrentCol()+1] == 10
               || matrix[box.getCurrentRow()+1][box.getCurrentCol()] == 10 && matrix[box.getCurrentRow()][box.getCurrentCol()-1] == 10
               || matrix[box.getCurrentRow()+1][box.getCurrentCol()] == 10 && matrix[box.getCurrentRow()][box.getCurrentCol()+1] == 10
               || matrix[box.getCurrentRow()][box.getCurrentCol()-1] == 10 && matrix[box.getCurrentRow()-1][box.getCurrentCol()] == 10
               || matrix[box.getCurrentRow()][box.getCurrentCol()-1] == 10 && matrix[box.getCurrentRow()+1][box.getCurrentCol()] == 10
               || matrix[box.getCurrentRow()][box.getCurrentCol()+1] == 10 && matrix[box.getCurrentRow()-1][box.getCurrentCol()] == 10
               || matrix[box.getCurrentRow()][box.getCurrentCol()+1] == 10 && matrix[box.getCurrentRow()+1][box.getCurrentCol()] == 10
               ||matrix[box.getCurrentRow()-1][box.getCurrentCol()] == 10 && matrix[box.getCurrentRow()][box.getCurrentCol()-1] == 12
               || matrix[box.getCurrentRow()-1][box.getCurrentCol()] == 10 && matrix[box.getCurrentRow()][box.getCurrentCol()+1] == 12
               || matrix[box.getCurrentRow()+1][box.getCurrentCol()] == 10 && matrix[box.getCurrentRow()][box.getCurrentCol()-1] == 12
               || matrix[box.getCurrentRow()+1][box.getCurrentCol()] == 10 && matrix[box.getCurrentRow()][box.getCurrentCol()+1] == 12
               || matrix[box.getCurrentRow()][box.getCurrentCol()-1] == 10 && matrix[box.getCurrentRow()-1][box.getCurrentCol()] == 12
               || matrix[box.getCurrentRow()][box.getCurrentCol()-1] == 10 && matrix[box.getCurrentRow()+1][box.getCurrentCol()] == 12
               || matrix[box.getCurrentRow()][box.getCurrentCol()+1] == 10 && matrix[box.getCurrentRow()-1][box.getCurrentCol()] == 12
               || matrix[box.getCurrentRow()][box.getCurrentCol()+1] == 10 && matrix[box.getCurrentRow()+1][box.getCurrentCol()] == 12
               || matrix[box.getCurrentRow()-1][box.getCurrentCol()] == 12 && matrix[box.getCurrentRow()][box.getCurrentCol()-1] == 10
                || matrix[box.getCurrentRow()-1][box.getCurrentCol()] == 12 && matrix[box.getCurrentRow()][box.getCurrentCol()+1] == 10
                || matrix[box.getCurrentRow()+1][box.getCurrentCol()] == 12 && matrix[box.getCurrentRow()][box.getCurrentCol()-1] == 10
                || matrix[box.getCurrentRow()+1][box.getCurrentCol()] == 12 && matrix[box.getCurrentRow()][box.getCurrentCol()+1] == 10
                || matrix[box.getCurrentRow()][box.getCurrentCol()-1] == 12 && matrix[box.getCurrentRow()-1][box.getCurrentCol()] == 10
                || matrix[box.getCurrentRow()][box.getCurrentCol()-1] == 12 && matrix[box.getCurrentRow()+1][box.getCurrentCol()] == 10
                || matrix[box.getCurrentRow()][box.getCurrentCol()+1] == 12 && matrix[box.getCurrentRow()-1][box.getCurrentCol()] == 10
                || matrix[box.getCurrentRow()][box.getCurrentCol()+1] == 12 && matrix[box.getCurrentRow()+1][box.getCurrentCol()] == 10
                ||matrix[box.getCurrentRow()-1][box.getCurrentCol()] == 12 && matrix[box.getCurrentRow()][box.getCurrentCol()-1] == 12
                || matrix[box.getCurrentRow()-1][box.getCurrentCol()] == 12 && matrix[box.getCurrentRow()][box.getCurrentCol()+1] == 12
                || matrix[box.getCurrentRow()+1][box.getCurrentCol()] == 12 && matrix[box.getCurrentRow()][box.getCurrentCol()-1] == 12
                || matrix[box.getCurrentRow()+1][box.getCurrentCol()] == 12 && matrix[box.getCurrentRow()][box.getCurrentCol()+1] == 12
                || matrix[box.getCurrentRow()][box.getCurrentCol()-1] == 12 && matrix[box.getCurrentRow()-1][box.getCurrentCol()] == 12
                || matrix[box.getCurrentRow()][box.getCurrentCol()-1] == 12 && matrix[box.getCurrentRow()+1][box.getCurrentCol()] == 12
                || matrix[box.getCurrentRow()][box.getCurrentCol()+1] == 12 && matrix[box.getCurrentRow()-1][box.getCurrentCol()] == 12
                || matrix[box.getCurrentRow()][box.getCurrentCol()+1] == 12 && matrix[box.getCurrentRow()+1][box.getCurrentCol()] == 12){
           box.setMovable(false);
       }
    }

    public static boolean verifyVisitor() {
        return isVisitor;
    }

    public static void setIsVisitor(boolean is) {
        isVisitor = is;
    }

    public static String getNextLevel() {
        return switch (CurrentLevel) {
            case "Level1" -> "/Sokoban/Level2.fxml";
            case "Level2" -> "/Sokoban/Level3.fxml";
            case "Level3" -> "/Sokoban/Level4.fxml";
            default -> null; // switch现代化表达
        };
    }

    public static void setCurrentLevel(String level) {
        CurrentLevel = level;
    }
    public static int getCurrentLevel() {
        return currentLevel;
    }
    public static void setCurrentLevel(int currentLevel) {
        GameSystem.currentLevel = currentLevel;
    }
    public int getTime() {
        return time;
    }
    public int getSteps() {
        return steps;
    }

    public void reset(int InitRow1, int InitCol1,int InitRow2,int InitCol2) {
        isvictory = false;
        isfailed = false;
        time = 0;
        steps = 0;
        moveoutNiker(niker.getCurrentCol(), niker.getCurrentRow());
        moveinNiker(1, 1);
        setBox(1, InitRow1 , InitCol1);
        setBox(2, InitRow2, InitCol2);
    }

    public static void saveGameProgress(GameSystem progress) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("game_progress.ser"))) {
            out.writeObject(progress);
            System.out.println("Game progress saved successfully!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static GameSystem loadGameProgress() {
        GameSystem progress = null;
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("game_progress.ser"))) {
            progress = (GameSystem) in.readObject();
            System.out.println("Game progress loaded successfully!");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return progress;
    }




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
         * 1  wall
         * 0 free space
         * 2  target location
         * The Then digit number can be changed during one game.
         * 十位数 1 box
         * 十位数 2  player
         * So that 12 represents a box on the target location and
         * 22 represents the player on the target location.
         */
   //在controller的初始化方法中调用，量化矩阵
    public void addBoxPositons(){
        for(Box box : boxes){
             int x = box.getCurrentRow(); int y = box.getCurrentCol();
            matrix[x][y] =10;
        }
    }

    public void addTargetPositons(){
        for(Target target : targets){
            int x = target.getCurrentRow();
            int y = target.getCurrentCol();
            matrix[x][y] = 2;
        }
    }

    public void addBoardPositons(){
        for(Board board : boards){
            int x = board.getCurrentRow(); int y = board.getCurrentCol();
            matrix[x][y] = 1;
        }
    }
    public void addPlayerPositons(int row,int col){
        matrix[row][col] = 20;
    }
    public int getPlayerRow(){
        return niker.getCurrentRow();
    }
    public int getPlayerCol(){
        return niker.getCurrentCol();
    }
    public int getBoxRow(int i){
        return boxes[i-1].getCurrentRow();
    }
    public int getBoxCol(int i){
        return boxes[i-1].getCurrentCol();
    }


    //区分不同box的判断方法
    public boolean isBox1(int col, int row) {
        return boxes[0].getCurrentCol() == col && boxes[0].getCurrentRow() == row;
    }

    public boolean isBox2(int col, int row) {
        return boxes[1].getCurrentCol() == col && boxes[1].getCurrentRow() == row;
    }
    public boolean isWall(int col, int row) {
        return matrix[row][col] == 1;
    }

    public void moveoutNiker(int col, int row) {
        if(matrix[row][col] == 20){
            matrix[row][col] = 0;
        }
        else if(matrix[row][col] == 22){
            matrix[row][col] = 2;
        }
    }
    public void moveinNiker(int col, int row) {
        niker.setCurrentRow(row);
        niker.setCurrentCol(col);
        if(matrix[row][col] == 0||matrix[row][col] == 10){
            matrix[row][col] = 20;
        }
        else if(matrix[row][col] == 2||matrix[row][col] == 12){
            matrix[row][col] = 22;
        }
        steps++;
    }

    public void moveoutBox(int col,int row){
        if(matrix[row][col] == 10){
            matrix[row][col] = 0;
        }
        else if(matrix[row][col] == 12){
            matrix[row][col] = 2;
        }
    }
    public void moveinBox(int i,int col,int row){
        boxes[i-1].setCurrentRow(row);
        boxes[i-1].setCurrentCol(col);
        matrix[row][col] = 10;
    }
}
