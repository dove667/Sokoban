package Sokoban.Controller;

import Sokoban.Model.*;
import javafx.animation.*;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.net.URL;
import static Sokoban.Login_Application.primaryStage;
import static Sokoban.Model.GameSystem.verifyVisitor;

import java.util.Objects;

public class Level3Controller {

    @FXML
    private Button Btn_back,Btn_down,Btn_home,Btn_left,Btn_right,Btn_up,Btn_load,Btn_save;

    @FXML
    private GridPane GridBoard,Movement;

    @FXML
    private ImageView Img_Back,Img_Move,Img_home,Img_load,Img_save;

    @FXML
    private Label Label_Level2,Label_steps,Label_timer,myTime,steps;

    @FXML
    private Circle Niker;

    @FXML
    private AnchorPane Pane;

    @FXML
    private Rectangle box1,box2,board1,board2,board3,board4,board5,board6,board7,board8,board9,board10,board11,board12,board13,board14,board15,board16,board17,board18,board19,board20,board21,board22,board23,board24,board25;

    @FXML
    private Polygon target1,target2;

    private Timeline timeline;


    GameSystem gameSystem3 = new GameSystem(2,2,25,7,7,30);
    public void initialize() {
        Platform.runLater(() -> {
            gameSystem3.setBox(1,GridPane.getColumnIndex(box1),GridPane.getRowIndex(box1));
            gameSystem3.setBox(2,GridPane.getColumnIndex(box2),GridPane.getRowIndex(box2));
            //设置好system中Box的坐标
            gameSystem3.addBoxPositons(); gameSystem3.setBoxNum(2);
            //将Box量化到system的矩阵中
            gameSystem3.setTarget(0,GridPane.getColumnIndex(target1),GridPane.getRowIndex(target1));
            gameSystem3.setTarget(1,GridPane.getColumnIndex(target2),GridPane.getRowIndex(target2));
            gameSystem3.addTargetPositons();gameSystem3.setTargNum(2);
            //同样操作target
            // 遍历操作Board。注意！Gridpane中0时默认位置，不会在fxml中显示标出，会导致Index.valueOf空指针异常。要手动标出坐标
            Rectangle[] boards = {board1,board2,board3,board4,board5,board6,board7,board8,board9,board10,board11,board12,board13,board14,board15,board16,board17,board18,board19,board20,board21,board22,board23,board24,board25};
            for (int i = 0; i < boards.length; i++) {
                gameSystem3.setBoard(i, GridPane.getColumnIndex(boards[i]), GridPane.getRowIndex(boards[i]));
            }
            gameSystem3.addBoardPositons();
            //操作player
            gameSystem3.setPlayer(GridPane.getColumnIndex(Niker),GridPane.getRowIndex(Niker));
            gameSystem3.addPlayerPositons(GridPane.getColumnIndex(Niker),GridPane.getRowIndex(Niker));
            gameSystem3.setPlayeriniCol(GridPane.getColumnIndex(Niker));
            gameSystem3.setPlayeriniRow(GridPane.getRowIndex(Niker));
        });

        //判断是否为游客模式
        if (verifyVisitor()){
            Img_load.setVisible(false);
            Img_save.setVisible(false);
            Img_home.setVisible(false);
            Btn_load.setDisable(true);
            Btn_save.setDisable(true);
            Btn_home.setDisable(true);
        }
        GameSystem.setCurrentLevel(3);GameSystem.setCurrentLevel("Level3");
        Pane.requestFocus(); // 确保焦点设置


        if (GameSystem.isTimeMode()) {
            timeline = new Timeline(
                    new KeyFrame(Duration.seconds(1), event -> {
                        gameSystem3.setTimeRemaining(gameSystem3.getTimeRemaining()-1); // 每秒减少 1
                        myTime.setText(String.valueOf(gameSystem3.getTimeRemaining())); // 更新标签文字

                        // 检查倒计时是否结束
                        if (gameSystem3.getTimeRemaining() <= 0) {
                            myTime.setText("time's up");
                            URL url = getClass().getResource("/Sokoban/Failed.fxml");
                            Parent root = null;
                            try {
                                root = FXMLLoader.load(Objects.requireNonNull(url));
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                            Scene scene = new Scene(root);
                            primaryStage.setScene(scene);
                            // 切换场景
                        }
                    })
            );
            timeline.setCycleCount(Timeline.INDEFINITE);// 设置循环次数
            timeline.play(); // 开始计时
        }
        else {
            myTime.setVisible(false);
            Label_timer.setVisible(false);
        }


        //图形初始化
        Image SUST =new Image("file:src/main/resources/Sokoban/Sokoban/pictures/SUST.jpeg");
        Niker.setFill(new ImagePattern(SUST));
    }
    public void stopTimeline() {
        if (timeline != null) {
            timeline.stop();  // 停止Timeline
            timeline.getKeyFrames().clear();  // 清除所有关键帧
            timeline = null;  // 解除对Timeline的引用
        }
    }

    @FXML
    void HomeBtnPressed(MouseEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Comfirm");
        alert.setHeaderText("Are you sure to quit?");
        alert.setContentText("Choose OK to quit or Cancel to continue.");
        // 显示对话框并等待用户操作
        alert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                gameSystem3.setGameOver(true);stopTimeline();
                gameSystem3.saveGameProgress(gameSystem3);
                //切换场景
                URL url = getClass().getResource("/Sokoban/LevelScene.fxml");
                Parent root;
                try {
                    root = FXMLLoader.load(Objects.requireNonNull(url));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                //设置场景
                Scene scene = new Scene(root);
                primaryStage.setScene(scene);
            }
            else {
                System.out.println("Operation cancelled.");
                Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
                alert1.setTitle("Cancel");
                alert1.setHeaderText(null);
                alert1.setContentText("Operation cancelled.");
                alert1.showAndWait();
            }
        });
    }
    @FXML
    void BackBtnPressed() throws IOException {
        stopTimeline();
        Platform.runLater(() -> {
            URL url = getClass().getResource("/Sokoban/Level3.fxml");
            Parent root = null;
            try {
                root = FXMLLoader.load(Objects.requireNonNull(url));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            gameSystem3.reset(GridPane.getRowIndex(box1),GridPane.getColumnIndex(box1),GridPane.getRowIndex(box2),GridPane.getColumnIndex(box2));
        });
    }

    @FXML
    void SaveBtnPressed() throws IOException {
        gameSystem3.saveGameProgress(gameSystem3);
    }
    @FXML
    void LoadBtnPressed() throws IOException {
        Btn_load.setDisable(true);

        Task<Void> loadTask = new Task<>() {
            @Nullable
            @Override
            protected Void call() throws Exception {
                stopTimeline();
                gameSystem3 = gameSystem3.loadGameProgress();
                return null;
            }

            @Override
            protected void succeeded() {
                Platform.runLater(() -> {
                    try {
                        // 更新界面
                        // javafx位置变化和css动态变化是叠加的,先设偏移量为0
                        AnimationController.resetNodePosition(Niker);
                        AnimationController.resetNodePosition(box1);
                        AnimationController.resetNodePosition(box2);
                        steps.setText(String.valueOf(gameSystem3.getSteps()));
                        GridPane.setRowIndex(Niker, gameSystem3.getPlayerRow());
                        GridPane.setColumnIndex(Niker, gameSystem3.getPlayerCol());
                        GridPane.setRowIndex(box1, gameSystem3.getBoxRow(1));
                        GridPane.setColumnIndex(box1, gameSystem3.getBoxCol(1));
                        GridPane.setRowIndex(box2, gameSystem3.getBoxRow(2));
                        GridPane.setColumnIndex(box2, gameSystem3.getBoxCol(2));
                        currentColumnIndex = gameSystem3.getPlayerCol();
                        currentRowIndex = gameSystem3.getPlayerRow();
                        if (GameSystem.isTimeMode()) {
                            timeline = new Timeline(
                                    new KeyFrame(Duration.seconds(1), event -> {
                                        gameSystem3.setTimeRemaining(gameSystem3.getTimeRemaining()-1); // 每秒减少 1
                                        myTime.setText(String.valueOf(gameSystem3.getTimeRemaining())); // 更新标签文字

                                        // 检查倒计时是否结束
                                        if (gameSystem3.getTimeRemaining() <= 0) {
                                            myTime.setText("time's up");
                                            URL url = getClass().getResource("/Sokoban/Failed.fxml");
                                            Parent root = null;
                                            try {
                                                root = FXMLLoader.load(Objects.requireNonNull(url));
                                            } catch (IOException e) {
                                                throw new RuntimeException(e);
                                            }
                                            Scene scene = new Scene(root);
                                            primaryStage.setScene(scene);
                                            // 切换场景
                                        }
                                    })
                            );
                            timeline.setCycleCount(Timeline.INDEFINITE); // 设置循环次数
                            timeline.play(); // 开始计时
                        }
                        else {
                            myTime.setVisible(false);
                            Label_timer.setVisible(false);
                        }
                    } finally {
                        Btn_load.setDisable(false);
                    }
                });
            }

            @Override
            protected void failed() {
                Platform.runLater(() -> {
                    Btn_load.setDisable(false);
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText(null);
                    alert.setContentText("Failed to load game progress.");
                    alert.showAndWait();
                });
            }
        };
        new Thread(loadTask).start();
    }


    void stepsUpdate() {
        steps.setText(gameSystem3.getSteps() + "");
    }
    //像下面这样写isWall也行

    @FXML
    void MovePlayer(@NotNull KeyEvent event) throws IOException {
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
    Integer currentRowIndex =2;

    @FXML
    void DownBtnPressed() throws IOException {
        int targetRow = currentRowIndex + 1;
        //纯移动
        if (gameSystem3.notWall(currentColumnIndex, targetRow) &&!gameSystem3.isBox1(currentColumnIndex, targetRow)&&!gameSystem3.isBox2(currentColumnIndex, targetRow)) {
            currentRowIndex = targetRow;
            AnimationController.MoveDown(Niker, currentColumnIndex, currentRowIndex);//动画
            gameSystem3.moveoutNiker(currentColumnIndex, currentRowIndex-1);
            gameSystem3.moveinNiker(currentColumnIndex, currentRowIndex);
            stepsUpdate();
        }
        //推箱子
        if (gameSystem3.isBox1(currentColumnIndex, targetRow)) {
            if (gameSystem3.notWall(currentColumnIndex, targetRow + 1) &&!gameSystem3.isBox2(currentColumnIndex, targetRow+1)) {
                //可推
                currentRowIndex = targetRow;
                AnimationController.MoveDown(Niker, currentColumnIndex, currentRowIndex);//动画
                gameSystem3.moveoutNiker(currentColumnIndex, currentRowIndex-1);
                gameSystem3.moveinNiker(currentColumnIndex, currentRowIndex);
                AnimationController.MoveDown(box1, currentColumnIndex, currentRowIndex+1);//动画
                gameSystem3.moveoutBox(currentColumnIndex, currentRowIndex);
                gameSystem3.moveinBox(1,currentColumnIndex, currentRowIndex+1);
                stepsUpdate();
            }
        }
        if (gameSystem3.isBox2(currentColumnIndex, targetRow)) {
            if (gameSystem3.notWall(currentColumnIndex, targetRow + 1) &&!gameSystem3.isBox1(currentColumnIndex, targetRow+1)) {
                //可推
                currentRowIndex = targetRow;
                AnimationController.MoveDown(Niker, currentColumnIndex, currentRowIndex);//动画
                gameSystem3.moveoutNiker(currentColumnIndex, currentRowIndex-1);
                gameSystem3.moveinNiker(currentColumnIndex, currentRowIndex);
                AnimationController.MoveDown(box2, currentColumnIndex, currentRowIndex+1);//动画
                gameSystem3.moveoutBox(currentColumnIndex, currentRowIndex);
                gameSystem3.moveinBox(2,currentColumnIndex, currentRowIndex+1);
                stepsUpdate();
            }
        }
        gameSystem3.victoryJudge();
        gameSystem3.failedJudge();
        if (gameSystem3.isGameOver()) {
            stopTimeline();
        }
    }

    @FXML
    void LeftBtnPressed() throws IOException {
        int targetColumn = currentColumnIndex - 1;
        //纯移动
        if (gameSystem3.notWall(targetColumn, currentRowIndex) &&!gameSystem3.isBox1(targetColumn, currentRowIndex)&&!gameSystem3.isBox2(targetColumn, currentRowIndex)) {
            currentColumnIndex = targetColumn;
            AnimationController.MoveLeft(Niker, currentColumnIndex, currentRowIndex);//动画
            gameSystem3.moveoutNiker(currentColumnIndex+1, currentRowIndex);
            gameSystem3.moveinNiker(currentColumnIndex, currentRowIndex);
            stepsUpdate();
        }
        //推箱子
        if (gameSystem3.isBox1(targetColumn, currentRowIndex)) {
            if (gameSystem3.notWall(targetColumn - 1, currentRowIndex) &&!gameSystem3.isBox2(targetColumn-1, currentRowIndex)) {
                currentColumnIndex = targetColumn;
                AnimationController.MoveLeft(Niker, currentColumnIndex, currentRowIndex);//动画
                gameSystem3.moveoutNiker(currentColumnIndex+1, currentRowIndex);
                gameSystem3.moveinNiker(currentColumnIndex, currentRowIndex);
                AnimationController.MoveLeft(box1, currentColumnIndex-1, currentRowIndex);//动画
                gameSystem3.moveoutBox(currentColumnIndex, currentRowIndex);
                gameSystem3.moveinBox(1,currentColumnIndex-1, currentRowIndex);
                stepsUpdate();
            }
        }
        if (gameSystem3.isBox2(targetColumn, currentRowIndex)) {
            if (gameSystem3.notWall(targetColumn - 1, currentRowIndex) &&!gameSystem3.isBox1(targetColumn-1, currentRowIndex)) {
                currentColumnIndex = targetColumn;
                AnimationController.MoveLeft(Niker, currentColumnIndex, currentRowIndex);//动画
                gameSystem3.moveoutNiker(currentColumnIndex+1, currentRowIndex);
                gameSystem3.moveinNiker(currentColumnIndex, currentRowIndex);
                AnimationController.MoveLeft(box2, currentColumnIndex-1, currentRowIndex);//动画
                gameSystem3.moveoutBox(currentColumnIndex, currentRowIndex);
                gameSystem3.moveinBox(2,currentColumnIndex-1, currentRowIndex);
                stepsUpdate();
            }
        }
        gameSystem3.victoryJudge();
        gameSystem3.failedJudge();
        if (gameSystem3.isGameOver()) {
            stopTimeline();
        }
    }

    @FXML
    void RightBtnPressed() throws IOException {
        int targetColumn = currentColumnIndex + 1;
        if (gameSystem3.notWall(targetColumn, currentRowIndex) &&!gameSystem3.isBox1(targetColumn, currentRowIndex)&&!gameSystem3.isBox2(targetColumn, currentRowIndex)) {
            currentColumnIndex = targetColumn;
            AnimationController.MoveRight(Niker, currentColumnIndex, currentRowIndex);//动画

            gameSystem3.moveoutNiker(currentColumnIndex-1, currentRowIndex);
            gameSystem3.moveinNiker(currentColumnIndex, currentRowIndex);
            stepsUpdate();
        }
        //推箱子
        if (gameSystem3.isBox1(targetColumn, currentRowIndex)) {
            if (gameSystem3.notWall(targetColumn + 1, currentRowIndex) &&!gameSystem3.isBox2(targetColumn+1, currentRowIndex)) {
                currentColumnIndex = targetColumn;
                AnimationController.MoveRight(Niker, currentColumnIndex, currentRowIndex);//动画
                gameSystem3.moveoutNiker(currentColumnIndex-1, currentRowIndex);
                gameSystem3.moveinNiker(currentColumnIndex, currentRowIndex);
                AnimationController.MoveRight(box1, currentColumnIndex+1, currentRowIndex);
                gameSystem3.moveoutBox(currentColumnIndex, currentRowIndex);
                gameSystem3.moveinBox(1,currentColumnIndex+1, currentRowIndex);
                stepsUpdate();
            }
        }
        if (gameSystem3.isBox2(targetColumn, currentRowIndex)) {
            if (gameSystem3.notWall(targetColumn + 1, currentRowIndex) &&!gameSystem3.isBox1(targetColumn+1, currentRowIndex)) {
                currentColumnIndex = targetColumn;
                AnimationController.MoveRight(Niker, currentColumnIndex, currentRowIndex);
                gameSystem3.moveoutNiker(currentColumnIndex-1, currentRowIndex);
                gameSystem3.moveinNiker(currentColumnIndex, currentRowIndex);
                AnimationController.MoveRight(box2, currentColumnIndex+1, currentRowIndex);
                gameSystem3.moveoutBox(currentColumnIndex, currentRowIndex);
                gameSystem3.moveinBox(2,currentColumnIndex+1, currentRowIndex);
                stepsUpdate();
            }
        }
        gameSystem3.victoryJudge();
        gameSystem3.failedJudge();
        if (gameSystem3.isGameOver()) {
            stopTimeline();
        }
    }

    @FXML
    void UpBtnPressed() throws IOException {
        int targetRow = currentRowIndex - 1;
        //纯移动
        if (gameSystem3.notWall(currentColumnIndex, targetRow) &&!gameSystem3.isBox1(currentColumnIndex, targetRow)&&!gameSystem3.isBox2(currentColumnIndex, targetRow)) {
            currentRowIndex = targetRow;
            AnimationController.MoveUp(Niker, currentColumnIndex, currentRowIndex);//动画
            gameSystem3.moveoutNiker(currentColumnIndex, currentRowIndex+1);
            gameSystem3.moveinNiker(currentColumnIndex, currentRowIndex);
            stepsUpdate();
        }
        //推箱子
        if (gameSystem3.isBox1(currentColumnIndex, targetRow)) {
            if (gameSystem3.notWall(currentColumnIndex, targetRow - 1) &&!gameSystem3.isBox2(currentColumnIndex, targetRow-1)) {
                currentRowIndex = targetRow;
                AnimationController.MoveUp(Niker, currentColumnIndex, currentRowIndex);//动画
                gameSystem3.moveoutNiker(currentColumnIndex, currentRowIndex+1);
                gameSystem3.moveinNiker(currentColumnIndex, currentRowIndex);
                AnimationController.MoveUp(box1, currentColumnIndex, currentRowIndex-1);//动画
                gameSystem3.moveoutBox(currentColumnIndex, currentRowIndex);
                gameSystem3.moveinBox(1,currentColumnIndex, currentRowIndex-1);
                stepsUpdate();
            }
        }
        if (gameSystem3.isBox2(currentColumnIndex, targetRow)) {
            if (gameSystem3.notWall(currentColumnIndex, targetRow - 1) &&!gameSystem3.isBox1(currentColumnIndex, targetRow-1)) {
                currentRowIndex = targetRow;
                AnimationController.MoveUp(Niker, currentColumnIndex, currentRowIndex);//动画
                gameSystem3.moveoutNiker(currentColumnIndex, currentRowIndex+1);
                gameSystem3.moveinNiker(currentColumnIndex, currentRowIndex);
                AnimationController.MoveUp(box2, currentColumnIndex, currentRowIndex-1);//动画
                gameSystem3.moveoutBox(currentColumnIndex, currentRowIndex);
                gameSystem3.moveinBox(2,currentColumnIndex, currentRowIndex-1);
                stepsUpdate();
            }
        }
        gameSystem3.victoryJudge();
        gameSystem3.failedJudge();
        if (gameSystem3.isGameOver()) {
            stopTimeline();
        }
    }
}
