package Sokoban.Controller;

import Sokoban.Model.*;
import javafx.animation.*;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import java.io.IOException;
import java.net.URL;
import static Sokoban.Login_Application.primaryStage;
import java.util.Objects;

public class Level5Controller {

    @FXML
    private Button Btn_back;

    @FXML
    private Button Btn_down;

    @FXML
    private Button Btn_home;

    @FXML
    private Button Btn_left;

    @FXML
    private Button Btn_load;

    @FXML
    private Button Btn_right;

    @FXML
    private Button Btn_save;

    @FXML
    private Button Btn_up;

    @FXML
    private GridPane GridBoard;

    @FXML
    private ImageView Img_Back;

    @FXML
    private ImageView Img_Move;

    @FXML
    private ImageView Img_home;

    @FXML
    private ImageView Img_load;

    @FXML
    private ImageView Img_save;

    @FXML
    private Label Label_Level5;

    @FXML
    private Label Label_steps;

    @FXML
    private Label Label_timer;

    @FXML
    private GridPane Movement;

    @FXML
    private Circle Niker;

    @FXML
    private AnchorPane Pane;

    @FXML
    private Rectangle board1;

    @FXML
    private Rectangle board10;

    @FXML
    private Rectangle board11;

    @FXML
    private Rectangle board12;

    @FXML
    private Rectangle board13;

    @FXML
    private Rectangle board14;

    @FXML
    private Rectangle board15;

    @FXML
    private Rectangle board16;

    @FXML
    private Rectangle board17;

    @FXML
    private Rectangle board18;

    @FXML
    private Rectangle board19;

    @FXML
    private Rectangle board2;

    @FXML
    private Rectangle board20;

    @FXML
    private Rectangle board21;

    @FXML
    private Rectangle board22;

    @FXML
    private Rectangle board23;

    @FXML
    private Rectangle board24;

    @FXML
    private Rectangle board25;

    @FXML
    private Rectangle board3;

    @FXML
    private Rectangle board4;

    @FXML
    private Rectangle board5;

    @FXML
    private Rectangle board6;

    @FXML
    private Rectangle board7;

    @FXML
    private Rectangle board8;

    @FXML
    private Rectangle board9;

    @FXML
    private Rectangle box1;

    @FXML
    private Rectangle box2;

    @FXML
    private Rectangle box3;

    @FXML
    private Label myTime;

    @FXML
    private Label steps;

    @FXML
    private Polygon target1;

    @FXML
    private Polygon target2;

    @FXML
    private Polygon target3;




    GameSystem gameSystem = new GameSystem(3,3,25,8,6,30);

    public void initialize() {
        Platform.runLater(() -> {
            gameSystem.setBox(1, GridPane.getColumnIndex(box1),GridPane.getRowIndex(box1));
            gameSystem.setBox(2,GridPane.getColumnIndex(box2),GridPane.getRowIndex(box2));
            //设置好system中Box的坐标
            gameSystem.addBoxPositons();
            //将Box量化到system的矩阵中
            gameSystem.setTarget(0,GridPane.getColumnIndex(target1),GridPane.getRowIndex(target1));
            gameSystem.setTarget(1,GridPane.getColumnIndex(target2),GridPane.getRowIndex(target2));
            gameSystem.addTargetPositons();
            //同样操作target
            // 遍历操作Board。注意！Gridpane中0时默认位置，不会在fxml中显示标出，会导致Index.valueOf空指针异常。要手动标出坐标
            Rectangle[] boards = {board1,board2,board3,board4,board5,board6,board7,board8,board9,board10,board11,board12,board13,board14,board15,board16,board17,board18,board19,board20,board21,board22,board23};
            for (int i = 0; i < boards.length; i++) {
                gameSystem.setBoard(i, GridPane.getColumnIndex(boards[i]), GridPane.getRowIndex(boards[i]));
            }
            gameSystem.addBoardPositons();
            //操作player
            gameSystem.setPlayer(GridPane.getColumnIndex(Niker),GridPane.getRowIndex(Niker));
            gameSystem.addPlayerPositons(GridPane.getColumnIndex(Niker),GridPane.getRowIndex(Niker));
        });
        //判断是否为游客模式
        if (GameSystem.verifyVisitor()){
            Img_load.setVisible(false);
            Img_save.setVisible(false);
            Img_home.setVisible(false);
            Btn_load.setDisable(true);
            Btn_save.setDisable(true);
            Btn_home.setDisable(true);
        }
        GameSystem.setCurrentLevel(5);GameSystem.setCurrentLevel("5");
        Pane.requestFocus(); // 确保焦点设置

        //计时模式

        if (GameSystem.isTimeMode()) {
            Timeline timeline = new Timeline(
                    new KeyFrame(Duration.seconds(1), event -> {
                        gameSystem.setTimeRemaining(gameSystem.getTimeRemaining()-1); // 每秒减少 1
                        myTime.setText(String.valueOf(gameSystem.getTimeRemaining())); // 更新标签文字

                        // 检查倒计时是否结束
                        if (gameSystem.getTimeRemaining() <= 0) {
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
            timeline.setCycleCount(gameSystem.getTimeRemaining()); // 设置循环次数
            timeline.play(); // 开始计时
        }
        else {
            myTime.setVisible(false);
            Label_timer.setVisible(false);
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
                gameSystem.saveGameProgress(gameSystem);
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
        URL url = getClass().getResource("/Sokoban/Level5.fxml");
        Parent root = FXMLLoader.load(Objects.requireNonNull(url));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        gameSystem.reset(GridPane.getRowIndex(box1),GridPane.getColumnIndex(box1),GridPane.getRowIndex(box2),GridPane.getColumnIndex(box2));
    }

    @FXML
    void SaveBtnPressed() throws IOException {
        gameSystem.saveGameProgress(gameSystem);
    }
    @FXML
    void LoadBtnPressed() throws IOException {
        gameSystem = gameSystem.loadGameProgress(); Pane.requestFocus();
        Platform.runLater(() -> {
            // 更新界面，如更新玩家、盒子、步数等
            steps.setText(String.valueOf(gameSystem.getSteps()));
            GridPane.setRowIndex(Niker, gameSystem.getPlayerRow());
            GridPane.setColumnIndex(Niker, gameSystem.getPlayerCol());
            GridPane.setRowIndex(box1, gameSystem.getBoxRow(1));
            GridPane.setColumnIndex(box1, gameSystem.getBoxCol(1));
            GridPane.setRowIndex(box2, gameSystem.getBoxRow(2));
            GridPane.setColumnIndex(box2, gameSystem.getBoxCol(2));
            currentColumnIndex = gameSystem.getPlayerCol();
            currentRowIndex = gameSystem.getPlayerRow();
        });
    }


    void stepsUpdate() {
        steps.setText(gameSystem.getSteps() + "");
    }
    //像下面这样写isWall也行

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

    Integer currentColumnIndex = gameSystem.getPlayerCol();
    Integer currentRowIndex = gameSystem.getPlayerRow();

    @FXML
    void DownBtnPressed() throws IOException {
        int targetRow = currentRowIndex + 1;
        //纯移动
        if (!gameSystem.isWall(currentColumnIndex, targetRow)&&!gameSystem.isBox1(currentColumnIndex, targetRow)&&!gameSystem.isBox2(currentColumnIndex, targetRow)) {
            currentRowIndex = targetRow;
            GridPane.setRowIndex(Niker, currentRowIndex);
            gameSystem.moveoutNiker(currentColumnIndex, currentRowIndex-1);
            gameSystem.moveinNiker(currentColumnIndex, currentRowIndex);
            stepsUpdate();
        }
        //推箱子
        if (gameSystem.isBox1(currentColumnIndex, targetRow)) {
            if (!gameSystem.isWall(currentColumnIndex, targetRow+1)&&!gameSystem.isBox2(currentColumnIndex, targetRow+1)) {
                //可推
                currentRowIndex = targetRow;
                GridPane.setRowIndex(Niker, currentRowIndex);
                gameSystem.moveoutNiker(currentColumnIndex, currentRowIndex-1);
                gameSystem.moveinNiker(currentColumnIndex, currentRowIndex);
                GridPane.setRowIndex(box1, currentRowIndex+1);
                gameSystem.moveoutBox(currentColumnIndex, currentRowIndex);
                gameSystem.moveinBox(1,currentColumnIndex, currentRowIndex+1);
                stepsUpdate();
            }
        }
        if (gameSystem.isBox2(currentColumnIndex, targetRow)) {
            if (!gameSystem.isWall(currentColumnIndex, targetRow+1)&&!gameSystem.isBox1(currentColumnIndex, targetRow+1)) {
                //可推
                currentRowIndex = targetRow;
                GridPane.setRowIndex(Niker, currentRowIndex);
                gameSystem.moveoutNiker(currentColumnIndex, currentRowIndex-1);
                gameSystem.moveinNiker(currentColumnIndex, currentRowIndex);
                GridPane.setRowIndex(box2, currentRowIndex+1);
                gameSystem.moveoutBox(currentColumnIndex, currentRowIndex);
                gameSystem.moveinBox(2,currentColumnIndex, currentRowIndex+1);
                stepsUpdate();
            }
        }
        gameSystem.victoryJudge();
        gameSystem.failedJudge();
    }

    @FXML
    void LeftBtnPressed() throws IOException {
        int targetColumn = currentColumnIndex - 1;
        //纯移动
        if (!gameSystem.isWall(targetColumn, currentRowIndex)&&!gameSystem.isBox1(targetColumn, currentRowIndex)&&!gameSystem.isBox2(targetColumn, currentRowIndex)) {
            currentColumnIndex = targetColumn;
            GridPane.setColumnIndex(Niker, currentColumnIndex);
            gameSystem.moveoutNiker(currentColumnIndex+1, currentRowIndex);
            gameSystem.moveinNiker(currentColumnIndex, currentRowIndex);
            stepsUpdate();
        }
        //推箱子
        if (gameSystem.isBox1(targetColumn, currentRowIndex)) {
            if (!gameSystem.isWall(targetColumn-1, currentRowIndex)&&!gameSystem.isBox2(targetColumn-1, currentRowIndex)) {
                currentColumnIndex = targetColumn;
                GridPane.setColumnIndex(Niker, currentColumnIndex);
                gameSystem.moveoutNiker(currentColumnIndex+1, currentRowIndex);
                gameSystem.moveinNiker(currentColumnIndex, currentRowIndex);
                GridPane.setColumnIndex(box1, currentColumnIndex-1);
                gameSystem.moveoutBox(currentColumnIndex, currentRowIndex);
                gameSystem.moveinBox(1,currentColumnIndex-1, currentRowIndex);
                stepsUpdate();
            }
        }
        if (gameSystem.isBox2(targetColumn, currentRowIndex)) {
            if (!gameSystem.isWall(targetColumn-1, currentRowIndex)&&!gameSystem.isBox1(targetColumn-1, currentRowIndex)) {
                currentColumnIndex = targetColumn;
                GridPane.setColumnIndex(Niker, currentColumnIndex);
                gameSystem.moveoutNiker(currentColumnIndex+1, currentRowIndex);
                gameSystem.moveinNiker(currentColumnIndex, currentRowIndex);
                GridPane.setColumnIndex(box2, currentColumnIndex-1);
                gameSystem.moveoutBox(currentColumnIndex, currentRowIndex);
                gameSystem.moveinBox(2,currentColumnIndex-1, currentRowIndex);
                stepsUpdate();
            }
        }
        gameSystem.victoryJudge();
        gameSystem.failedJudge();
    }

    @FXML
    void RightBtnPressed() throws IOException {
        int targetColumn = currentColumnIndex + 1;
        if (!gameSystem.isWall(targetColumn, currentRowIndex)&&!gameSystem.isBox1(targetColumn, currentRowIndex)&&!gameSystem.isBox2(targetColumn, currentRowIndex)) {
            currentColumnIndex = targetColumn;
            GridPane.setColumnIndex(Niker, currentColumnIndex);
            gameSystem.moveoutNiker(currentColumnIndex-1, currentRowIndex);
            gameSystem.moveinNiker(currentColumnIndex, currentRowIndex);
            stepsUpdate();
        }
        //推箱子
        if (gameSystem.isBox1(targetColumn, currentRowIndex)) {
            if (!gameSystem.isWall(targetColumn+1, currentRowIndex)&&!gameSystem.isBox2(targetColumn+1, currentRowIndex)) {
                currentColumnIndex = targetColumn;
                GridPane.setColumnIndex(Niker, currentColumnIndex);
                gameSystem.moveoutNiker(currentColumnIndex-1, currentRowIndex);
                gameSystem.moveinNiker(currentColumnIndex, currentRowIndex);
                GridPane.setColumnIndex(box1, currentColumnIndex+1);
                gameSystem.moveoutBox(currentColumnIndex, currentRowIndex);
                gameSystem.moveinBox(1,currentColumnIndex+1, currentRowIndex);
                stepsUpdate();
            }
        }
        if (gameSystem.isBox2(targetColumn, currentRowIndex)) {
            if (!gameSystem.isWall(targetColumn+1, currentRowIndex)&&!gameSystem.isBox1(targetColumn+1, currentRowIndex)) {
                currentColumnIndex = targetColumn;
                GridPane.setColumnIndex(Niker, currentColumnIndex);
                gameSystem.moveoutNiker(currentColumnIndex-1, currentRowIndex);
                gameSystem.moveinNiker(currentColumnIndex, currentRowIndex);
                GridPane.setColumnIndex(box2, currentColumnIndex+1);
                gameSystem.moveoutBox(currentColumnIndex, currentRowIndex);
                gameSystem.moveinBox(2,currentColumnIndex+1, currentRowIndex);
                stepsUpdate();
            }
        }
        gameSystem.victoryJudge();
        gameSystem.failedJudge();
    }

    @FXML
    void UpBtnPressed() throws IOException {
        int targetRow = currentRowIndex - 1;
        //纯移动
        if (!gameSystem.isWall(currentColumnIndex, targetRow)&&!gameSystem.isBox1(currentColumnIndex, targetRow)&&!gameSystem.isBox2(currentColumnIndex, targetRow)) {
            currentRowIndex = targetRow;
            GridPane.setRowIndex(Niker, currentRowIndex);
            gameSystem.moveoutNiker(currentColumnIndex, currentRowIndex+1);
            gameSystem.moveinNiker(currentColumnIndex, currentRowIndex);
            stepsUpdate();
        }
        //推箱子
        if (gameSystem.isBox1(currentColumnIndex, targetRow)) {
            if (!gameSystem.isWall(currentColumnIndex, targetRow-1)&&!gameSystem.isBox2(currentColumnIndex, targetRow-1)) {
                currentRowIndex = targetRow;
                GridPane.setRowIndex(Niker, currentRowIndex);
                gameSystem.moveoutNiker(currentColumnIndex, currentRowIndex+1);
                gameSystem.moveinNiker(currentColumnIndex, currentRowIndex);
                GridPane.setRowIndex(box1, currentRowIndex-1);
                gameSystem.moveoutBox(currentColumnIndex, currentRowIndex);
                gameSystem.moveinBox(1,currentColumnIndex, currentRowIndex-1);
                stepsUpdate();
            }
        }
        if (gameSystem.isBox2(currentColumnIndex, targetRow)) {
            if (!gameSystem.isWall(currentColumnIndex, targetRow-1)&&!gameSystem.isBox1(currentColumnIndex, targetRow-1)) {
                currentRowIndex = targetRow;
                GridPane.setRowIndex(Niker, currentRowIndex);
                gameSystem.moveoutNiker(currentColumnIndex, currentRowIndex+1);
                gameSystem.moveinNiker(currentColumnIndex, currentRowIndex);
                GridPane.setRowIndex(box2, currentRowIndex-1);
                gameSystem.moveoutBox(currentColumnIndex, currentRowIndex);
                gameSystem.moveinBox(2,currentColumnIndex, currentRowIndex-1);
                stepsUpdate();
            }
        }
        gameSystem.victoryJudge();
        gameSystem.failedJudge();
    }

}
