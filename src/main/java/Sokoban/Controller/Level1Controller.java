package Sokoban.Controller;

import Sokoban.Model.GameSystem;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Level1Controller {
    @FXML
    private Label Label_clock;

    @FXML
    private Button Btn_back,Btn_down,Btn_up,Btn_left,Btn_right,Btn_home;

    @FXML
    private ImageView Img_Back,Img_home,Img_Move;

    @FXML
    private Label Label_Level1,Label_steps,Label_timer,steps,time;

    @FXML
    private Circle Niker;

    @FXML
    private Rectangle board1, board2, board3, board4, board5, board6, board7, board8, board9,
            board10, board11, board12, board13, board14, board15, board16, board17, board18,
            box1, box2;

    @FXML
    private Polygon target1,target2;

    @FXML
    private GridPane Movement,GridBoard;

    @FXML
    void HomeBtnPressed(MouseEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Are you sure?");
        alert.setHeaderText("Are you sure to exit?");
        alert.setContentText("Your progress will be recorded.");
        alert.showAndWait();
        //保存进度


        //切换场景
        Stage primaryStage = (Stage) Btn_back.getScene().getWindow();
        URL url = getClass().getResource("/Sokoban/LevelScene.fxml");
        //加载完fxml文件后，获取其中的root
        Parent root = FXMLLoader.load(Objects.requireNonNull(url));
        //设置场景
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
    }
    @FXML
    void BackBtnPressed(MouseEvent event) throws IOException {
        Stage primaryStage = (Stage) Btn_back.getScene().getWindow();
        URL url = getClass().getResource("/Sokoban/Level1.fxml");
        Parent root = FXMLLoader.load(Objects.requireNonNull(url));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        initialize();
    }

    GameSystem gameSystem = new GameSystem(2,2,6,5);

    //Gridpane静态方法不能再类体中调用，只能在initialize中调用
    public void initialize() {
        if (GridPane.getColumnIndex(Niker) == null) {
            GridPane.setColumnIndex(Niker, 1); // 设置默认列
        }
        if (GridPane.getRowIndex(Niker) == null) {
            GridPane.setRowIndex(Niker, 1); // 设置默认行
        }
        gameSystem.setBox(0,GridPane.getColumnIndex(box1),GridPane.getRowIndex(box1));
        gameSystem.setBox(1,GridPane.getColumnIndex(box2),GridPane.getRowIndex(box2));
        gameSystem.setTarget(0,GridPane.getColumnIndex(target1),GridPane.getRowIndex(target1));
        gameSystem.setTarget(1,GridPane.getColumnIndex(target2),GridPane.getRowIndex(target2));
        setWallPositions();
        gameSystem.setBoxPositons();
        gameSystem.setPlayerPositons(1,1);
        Movement.requestFocus(); // 确保焦点设置

    }

    void stepsUpdate() {
        steps.setText(gameSystem.getSteps() + "");
    }

    /*setWallPositions用来初始化walls，以便通过isWall判断Player是否碰到墙
    * 这里的检查方法是，用集合类存储所有walls的横纵坐标（String），然后用contains方法判断Player的下一步的坐标是非包含在walls的集合中
    * 这样做的好处是，不用每次都检查所有墙壁，只要检查Player下一步的“坐标”即可，效率高（重点）
    *     集合类是 Java 中用于存储和操作一组对象的类的统称，这些对象可以是任意类型（基本数据类型需要装箱成对象），大小是动态的
    *     List：有序集合，可以包含重复的元素（ArrayList，LinkedList）
    *     Set：不允许重复元素的集合（HashSet，LinkedHashSet，TreeSet）
    *     Map：存储键值对的集合，键是唯一的（HashMap，LinkedHashMap，TreeMa）
    * 集合类通过addWallToSet方法获取wall的坐标，具体由GridPane.get***Index(board)方法实现
    * 将每个wall节点都addWallToSet储存坐标，也就是setWallPositions方法*/
    private final Set<String> walls = new HashSet<>();//集合类，单一性，无序，null允许
    // 获取所有墙壁的位置，并存储在集合中
    private void setWallPositions() {
        // 将所有墙壁的坐标添加到集合中
        addWallToSet(board1);
        addWallToSet(board2);
        addWallToSet(board3);
        addWallToSet(board4);
        addWallToSet(board5);
        addWallToSet(board6);
        addWallToSet(board7);
        addWallToSet(board8);
        addWallToSet(board9);
        addWallToSet(board10);
        addWallToSet(board11);
        addWallToSet(board12);
        addWallToSet(board13);
        addWallToSet(board14);
        addWallToSet(board15);
        addWallToSet(board16);
        addWallToSet(board17);
        addWallToSet(board18);
    }

    // 辅助方法，将每个墙壁的位置添加到集合中
    private void addWallToSet(Rectangle board) {
        Integer column = GridPane.getColumnIndex(board);
        Integer row = GridPane.getRowIndex(board);
        // 如果坐标为null，假设为0
        column = (column == null) ? 0 : column;
        row = (row == null) ? 0 : row;
        // 将坐标存储为字符串（"row-column"的形式）
        walls.add(row + "-" + column);
    }

    // 检查目标位置是否为墙壁
    private boolean isWall(int column, int row) {
        // 使用 "row-column" 形式检查是否是墙壁
        return walls.contains(row + "-" + column);//contains方法，判断是否包含元素
    }
    //像下面这样些isWall也行
    private boolean isBox1(int column, int row) {
        return column == GridPane.getColumnIndex(box1) && row == GridPane.getRowIndex(box1);
    }

    private boolean isBox2(int column, int row) {
        return column == GridPane.getColumnIndex(box2) && row == GridPane.getRowIndex(box2);
    }

    @FXML
    void MovePlayer(KeyEvent event) throws IOException {
        if (event.getCode() == KeyCode.UP || event.getCode() == KeyCode.W) {
            UpBtnPressed();
        } else if (event.getCode() == KeyCode.DOWN || event.getCode() == KeyCode.S) {
            DownBtnPressed();
        } else if (event.getCode() == KeyCode.LEFT || event.getCode() == KeyCode.A) {
            LeftBtnPressed();
        } else if (event.getCode() == KeyCode.RIGHT || event.getCode() == KeyCode.D) {
            RightBtnPressed();
        }
        event.consume();  // 确保事件不会被其他地方消费
    }

    Integer currentColumnIndex = 1;
    Integer currentRowIndex = 1;
    @FXML
    void DownBtnPressed() throws IOException {
        int targetRow = currentRowIndex + 1;
        //纯移动
        if (!isWall(currentColumnIndex, targetRow)&&!isBox1(currentColumnIndex, targetRow)&&!isBox2(currentColumnIndex, targetRow)) {
            currentRowIndex = targetRow;
            GridPane.setRowIndex(Niker, currentRowIndex);
            gameSystem.moveNiker(currentColumnIndex, currentRowIndex);
            stepsUpdate();
        }
        //推箱子
        if (isBox1(currentColumnIndex, targetRow)) {
            if (!isWall(currentColumnIndex, targetRow+1)&&!isBox2(currentColumnIndex, targetRow+1)) {
                currentRowIndex = targetRow;
                GridPane.setRowIndex(Niker, currentRowIndex);
                gameSystem.moveNiker(currentColumnIndex, currentRowIndex);
                GridPane.setRowIndex(box1, currentRowIndex+1);
                gameSystem.moveBox(0, currentColumnIndex, currentRowIndex+1);
                stepsUpdate();
            }
        }
        if (isBox2(currentColumnIndex, targetRow)) {
            if (!isWall(currentColumnIndex, targetRow+1)&&!isBox1(currentColumnIndex, targetRow+1)) {
                currentRowIndex = targetRow;
                GridPane.setRowIndex(Niker, currentRowIndex);
                gameSystem.moveNiker(currentColumnIndex, currentRowIndex);
                GridPane.setRowIndex(box2, currentRowIndex+1);
                gameSystem.moveBox(1, currentColumnIndex, currentRowIndex+1);
                stepsUpdate();
            }
        }
        victoryJudge();
    }

    @FXML
    void LeftBtnPressed() throws IOException {
        int targetColumn = currentColumnIndex - 1;
        //纯移动
        if (!isWall(targetColumn, currentRowIndex)&&!isBox1(targetColumn, currentRowIndex)&&!isBox2(targetColumn, currentRowIndex)) {
            currentColumnIndex = targetColumn;
            GridPane.setColumnIndex(Niker, currentColumnIndex);
            gameSystem.moveNiker(currentColumnIndex, currentRowIndex);
            stepsUpdate();
        }
        //推箱子
        if (isBox1(targetColumn, currentRowIndex)) {
            if (!isWall(targetColumn-1, currentRowIndex)&&!isBox2(targetColumn-1, currentRowIndex)) {
                currentColumnIndex = targetColumn;
                GridPane.setColumnIndex(Niker, currentColumnIndex);
                gameSystem.moveNiker(currentColumnIndex, currentRowIndex);
                GridPane.setColumnIndex(box1, currentColumnIndex-1);
                gameSystem.moveBox(0, currentColumnIndex-1, currentRowIndex);
                stepsUpdate();
            }
        }
        if (isBox2(targetColumn, currentRowIndex)) {
            if (!isWall(targetColumn-1, currentRowIndex)&&!isBox1(targetColumn-1, currentRowIndex)) {
                currentColumnIndex = targetColumn;
                GridPane.setColumnIndex(Niker, currentColumnIndex);
                gameSystem.moveNiker(currentColumnIndex, currentColumnIndex);
                GridPane.setColumnIndex(box2, currentColumnIndex-1);
                gameSystem.moveBox(1, currentColumnIndex-1, currentRowIndex);
                stepsUpdate();
            }
        }
        victoryJudge();
    }

    @FXML
    void RightBtnPressed() throws IOException {
        int targetColumn = currentColumnIndex + 1;
        if (!isWall(targetColumn, currentRowIndex)&&!isBox1(targetColumn, currentRowIndex)&&!isBox2(targetColumn, currentRowIndex)) {
            currentColumnIndex = targetColumn;
            GridPane.setColumnIndex(Niker, currentColumnIndex);
            gameSystem.moveNiker(currentColumnIndex, currentRowIndex);
            stepsUpdate();
        }
        //推箱子
        if (isBox1(targetColumn, currentRowIndex)) {
            if (!isWall(targetColumn+1, currentRowIndex)&&!isBox2(targetColumn+1, currentRowIndex)) {
                currentColumnIndex = targetColumn;
                GridPane.setColumnIndex(Niker, currentColumnIndex);
                gameSystem.moveNiker(currentColumnIndex, currentRowIndex);
                GridPane.setColumnIndex(box1, currentColumnIndex+1);
                gameSystem.moveBox(0, currentColumnIndex+1, currentRowIndex);
                stepsUpdate();
            }
        }
        if (isBox2(targetColumn, currentRowIndex)) {
            if (!isWall(targetColumn+1, currentRowIndex)&&!isBox1(targetColumn+1, currentRowIndex)) {
                currentColumnIndex = targetColumn;
                GridPane.setColumnIndex(Niker, currentColumnIndex);
                gameSystem.moveNiker(currentColumnIndex, currentColumnIndex);
                GridPane.setColumnIndex(box2, currentColumnIndex+1);
                gameSystem.moveBox(1, currentColumnIndex+1, currentRowIndex);
                stepsUpdate();
            }
        }
        victoryJudge();
    }

    @FXML
    void UpBtnPressed() throws IOException {
        int targetRow = currentRowIndex - 1;
        //纯移动
        if (!isWall(currentColumnIndex, targetRow)&&!isBox1(currentColumnIndex, targetRow)&&!isBox2(currentColumnIndex, targetRow)) {
            currentRowIndex = targetRow;
            GridPane.setRowIndex(Niker, currentRowIndex);
            gameSystem.moveNiker(currentColumnIndex, currentRowIndex);
            stepsUpdate();
        }
        //推箱子
        if (isBox1(currentColumnIndex, targetRow)) {
            if (!isWall(currentColumnIndex, targetRow-1)&&!isBox2(currentColumnIndex, targetRow-1)) {
                currentRowIndex = targetRow;
                GridPane.setRowIndex(Niker, currentRowIndex);
                gameSystem.moveNiker(currentColumnIndex, currentRowIndex);
                GridPane.setRowIndex(box1, currentRowIndex-1);
                gameSystem.moveBox(0, currentColumnIndex, currentRowIndex-1);
                stepsUpdate();
            }
        }
        if (isBox2(currentColumnIndex, targetRow)) {
            if (!isWall(currentColumnIndex, targetRow-1)&&!isBox1(currentColumnIndex, targetRow-1)) {
                currentRowIndex = targetRow;
                GridPane.setRowIndex(Niker, currentRowIndex);
                gameSystem.moveNiker(currentColumnIndex, currentRowIndex);
                GridPane.setRowIndex(box2, currentRowIndex-1);
                gameSystem.moveBox(1, currentColumnIndex, currentRowIndex-1);
                stepsUpdate();
            }
        }
        victoryJudge();
    }

    public void victoryJudge() throws IOException {
        if(gameSystem.isVictory()){
            Stage primaryStage = (Stage) Btn_up.getScene().getWindow();
            URL url = getClass().getResource("/Sokoban/Victory.fxml");
            Parent root = FXMLLoader.load(Objects.requireNonNull(url));
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
        }
    }


}
