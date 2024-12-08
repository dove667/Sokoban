package Sokoban.Model;

import java.io.*;
import java.net.URL;
import java.util.Arrays;
import java.util.Objects;

import Sokoban.Controller.AnimationController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static Sokoban.Login_Application.primaryStage;

public class GameSystem implements Serializable {

    private int timeRemaining;
    private int steps;
    Player niker ;
    private Box[]boxes;
    private Board[] boards;
    private Target[]targets;
    private int[][] matrix;
    private static int currentLevel;
    private static String CurrentLevel ;
    private static boolean isVisitor;
    private static boolean isTimeMode;
    //静态字段允许在没有实例创建时使用(选择关卡时),但仍会在构造方法中被赋予默认值
    private static String[] password = new String[10];
    private static String[] name = new String[10];
    private int i=0;
    private int j=0;
    private static boolean L1win;
    private static boolean L2win;
    private static boolean L3win;
    private static boolean L4win;
    private static boolean L5win;
    private int targNum;
    private int boxNum;
    private  boolean isGameOver;

    public boolean isGameOver() {
        return isGameOver;
    }

    public  void setGameOver(boolean b) {
        this.isGameOver = b;
    }

    public void setTargNum(int targNum) {
        this.targNum = targNum;
    }

    public void setBoxNum(int boxNum) {
        this.boxNum = boxNum;
    }

    public static boolean isL1win() {
        return L1win;
    }

    public static boolean isL2win() {
        return L2win;
    }

    public static boolean isL3win() {
        return L3win;
    }

    public static boolean isL4win() {
        return L4win;
    }

    public static boolean isL5win() {
        return L5win;
    }

    public String[] addName(String a) {
        for(;i<name.length;i++){
            name[i]=a;
        }
        return name;
    }

    public static String[] getName() {
        return name;
    }
    public static String[] getPassword() {
        return password;
    }
    public String[] addPassword(String a) {
        for(;j<password.length;j++){
            password[j]=a;
        }
        return password;
    }
    public static boolean checkMatch(String[]name,String[]password,String a,String b){
        if (name == null || password == null) {
            return false;
        }//防止空指针异常
        int nameIndex = Arrays.asList(name).indexOf(a);
        int passwordIndex = Arrays.asList(password).indexOf(b);
        return nameIndex == passwordIndex;
    }


    //constructor
    public GameSystem() {}
    public GameSystem(int boxNumber, int targetNumber,int boardNumber, int width, int height,int time) {
        timeRemaining = time;
        steps = 0;
        boxNum = boxNumber;
        boxes = new Box[boxNumber];
        for (int i = 0; i < boxNumber; i++) {
            boxes[i] = new Box();
            boxes[i].setMovable(true);
        }
        targNum = targetNumber;
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
        boxes[i-1].setMovable(true);
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
    public static boolean verifyVisitor() {
        return isVisitor;
    }

    public void victoryJudge() throws IOException {

        for(int i=0;i<matrix.length;i++){
            for(int j=0;j<matrix.length;j++){
                if(matrix[i][j] == 12){
                    for (int m = 0; m < matrix.length; m++){
                        for (int n = 0; n < matrix.length; n++){
                            if(matrix[m][n] == 12 && !(m==i && n==j)){
                                //同时有两个box达到target了
                                if(boxNum==2){
                                    URL url = getClass().getResource("/Sokoban/Victory.fxml");
                                    Parent root = FXMLLoader.load(Objects.requireNonNull(url));
                                    Scene scene = new Scene(root);
                                    primaryStage.setScene(scene);
                                    isGameOver = true;
                                }
                                else if(boxNum==3){
                                    for(int p=0;p<matrix.length;p++){
                                        for(int q=0;q<matrix.length;q++){
                                            if(matrix[p][q] == 12  && !(p==i && q==j) && !(p==m && q==n)){
                                                URL url = getClass().getResource("/Sokoban/Victory.fxml");
                                                Parent root = FXMLLoader.load(Objects.requireNonNull(url));
                                                Scene scene = new Scene(root);
                                                primaryStage.setScene(scene);
                                                isGameOver = true;
                                            }
                                        }
                                    }
                                }



                            }
                        }
                    }
                }
            }
        }
    }

    public void failedJudge() throws IOException {
        for(Box box:boxes){
            judgeBoxMovable(box);
        }

        if(boxNum==2){
            if(!boxes[0].isMovable() && !boxes[1].isMovable()){
                URL url = getClass().getResource("/Sokoban/Failed.fxml");
                Parent root = FXMLLoader.load(Objects.requireNonNull(url));
                Scene scene = new Scene(root);
                primaryStage.setScene(scene);
                isGameOver = true;
            }
        }
        else if(boxNum==3){
            if(!boxes[0].isMovable() && !boxes[1].isMovable() && !boxes[2].isMovable()){
                URL url = getClass().getResource("/Sokoban/Failed.fxml");
                Parent root = FXMLLoader.load(Objects.requireNonNull(url));
                Scene scene = new Scene(root);
                primaryStage.setScene(scene);
                isGameOver = true;
            }
        }
    }

    public void judgeBoxMovable(@NotNull Box box) throws IOException {
        //左右，上下各有一面时board时
        if((matrix[box.getCurrentRow()-1][box.getCurrentCol()] == 1 && matrix[box.getCurrentRow()][box.getCurrentCol()-1] == 1)
        || (matrix[box.getCurrentRow()-1][box.getCurrentCol()] == 1 && matrix[box.getCurrentRow()][box.getCurrentCol()+1] == 1)
        || (matrix[box.getCurrentRow()+1][box.getCurrentCol()] == 1 && matrix[box.getCurrentRow()][box.getCurrentCol()-1] == 1)
        || (matrix[box.getCurrentRow()+1][box.getCurrentCol()] == 1 && matrix[box.getCurrentRow()][box.getCurrentCol()+1] == 1)){
            box.setMovable(false);
        }
        //左右，上下一面board一面box时(1--10,12)4
        else if(matrix[box.getCurrentRow()-1][box.getCurrentCol()] == 1 && matrix[box.getCurrentRow()][box.getCurrentCol()-1] == 10
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
        else if(matrix[box.getCurrentRow()-1][box.getCurrentCol()] == 10 && matrix[box.getCurrentRow()][box.getCurrentCol()-1] == 10
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
       else{
           box.setMovable(true);
        }
    }



    public static void setIsVisitor(boolean is) {
        isVisitor = is;
    }
    //实现victory的nextLevel按钮多态
    @Nullable
    public static String getNextLevel() {
        switch (currentLevel) {
            case 1 -> {
                L1win = true;
                return "/Sokoban/Level2.fxml";
            }

            case 2 -> {
                L2win = true;
                return "/Sokoban/Level3.fxml";
            }
            case 3 -> {
                L3win = true;
                return "/Sokoban/Level4.fxml";
            }
            case 4 -> {
                L4win = true;
                return "/Sokoban/Level5.fxml";
            }
            default -> {
                return "/Sokoban/LevelScene.fxml";
            }
        }

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


    public int getSteps() {
        return steps;
    }
    public  int getTimeRemaining() {
        return timeRemaining;
    }
    public void setSteps(int steps) {
        this.steps = steps;
    }
    public  void setTimeRemaining(int timeRemaining) {
        this.timeRemaining = timeRemaining;
    }

    public void saveGameProgress(GameSystem progress) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("game_progress.ser"))) {
            out.writeObject(progress);
            System.out.println("Game progress saved successfully!");
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText("Game progress saved successfully!");
            alert.setContentText("You can load it in the next time.");
            alert.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //这里的load可以自动实现计时模式转换
    public GameSystem loadGameProgress() {
        GameSystem progress = null;
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("game_progress.ser"))) {
            progress = (GameSystem) in.readObject();
            System.out.println("Game progress loaded successfully!");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Failed to load game progress");
            alert.setContentText("The file might be corrupted");
            alert.showAndWait();
        }
        return progress;
    }
    public static boolean isTimeMode() {
        return isTimeMode;
    }

    public static void setTimeMode(boolean a) {
        isTimeMode = a;
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
    public int getPlayeriniCol(){
        return niker.getInitialcol();
    }
    public int getPlayeriniRow(){
        return niker.getInitialrow();
    }
    public void setPlayeriniCol(int col){
        niker.setInitialcol(col);
    }
    public void setPlayeriniRow(int row ){
        niker.setInitialrow(row);
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

    public boolean isBox3(int col, int row) {
        return boxes[2].getCurrentCol() == col && boxes[2].getCurrentRow() == row;
    }
    public boolean notWall(int col, int row) {
        return matrix[row][col] != 1;
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
        if (matrix[row][col] == 0){
            matrix[row][col] = 10;
        }
        else if(matrix[row][col] == 2){
            matrix[row][col] = 12;
        }
    }

    public void reset(int InitRow1, int InitCol1,int InitRow2,int InitCol2) {
        steps = 0;
        moveoutNiker(niker.getCurrentCol(), niker.getCurrentRow());
        moveinNiker(niker.getInitialcol(), niker.getInitialrow());
        setBox(1, InitRow1 , InitCol1);
        setBox(2, InitRow2, InitCol2);

    }

    public void reset(int InitRow1, int InitCol1,int InitRow2,int InitCol2,int InitRow3,int InitCol3){
        steps = 0;
        moveoutNiker(niker.getCurrentCol(), niker.getCurrentRow());
        moveinNiker(niker.getInitialcol(), niker.getInitialrow());
        setBox(1, InitRow1 , InitCol1);
        setBox(2, InitRow2, InitCol2);
        setBox(3, InitRow3, InitCol3);
//希望为player加上初始坐标属性以便重设时正常
    }
}
