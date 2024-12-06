package Sokoban.Controller;

import Sokoban.Model.*;
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
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.net.URL;
import static Sokoban.Login_Application.primaryStage;
import java.util.Objects;

public class Level4Controller {

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
    private Label Label_Level4;

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

    GameSystem gameSystem4 = new GameSystem(3,3,25,7,7,30);
    public void initialize() {
        Platform.runLater(() -> {
            gameSystem4.setBox(1,GridPane.getColumnIndex(box1),GridPane.getRowIndex(box1));
            gameSystem4.setBox(2,GridPane.getColumnIndex(box2),GridPane.getRowIndex(box2));
            gameSystem4.setBox(3,GridPane.getColumnIndex(box3),GridPane.getRowIndex(box3));
            //设置好system中Box的坐标
            gameSystem4.addBoxPositons();
            gameSystem4.setBoxNum(3);
            //将Box量化到system的矩阵中
            gameSystem4.setTarget(0,GridPane.getColumnIndex(target1),GridPane.getRowIndex(target1));
            gameSystem4.setTarget(1,GridPane.getColumnIndex(target2),GridPane.getRowIndex(target2));
            gameSystem4.setTarget(2,GridPane.getColumnIndex(target3),GridPane.getRowIndex(target3));
            gameSystem4.addTargetPositons();
            gameSystem4.setTargNum(3);
            //同样操作target
            // 遍历操作Board。注意！Gridpane中0时默认位置，不会在fxml中显示标出，会导致Index.valueOf空指针异常。要手动标出坐标
            Rectangle[] boards = {board1,board2,board3,board4,board5,board6,board7,board8,board9,board10,board11,board12,board13,board14,board15,board16,board17,board18,board19,board20,board21,board22,board23,board24,board25};
            for (int i = 0; i < boards.length; i++) {
                gameSystem4.setBoard(i, GridPane.getColumnIndex(boards[i]), GridPane.getRowIndex(boards[i]));
            }
            gameSystem4.addBoardPositons();
            //操作player
            gameSystem4.setPlayer(GridPane.getColumnIndex(Niker),GridPane.getRowIndex(Niker));
            gameSystem4.addPlayerPositons(GridPane.getColumnIndex(Niker),GridPane.getRowIndex(Niker));
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
        GameSystem.setCurrentLevel(4);GameSystem.setCurrentLevel("4");
        Pane.requestFocus(); // 确保焦点设置
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
                gameSystem4.saveGameProgress(gameSystem4);
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
        URL url = getClass().getResource("/Sokoban/Level4.fxml");
        Parent root = FXMLLoader.load(Objects.requireNonNull(url));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        gameSystem4.reset(GridPane.getRowIndex(box1),GridPane.getColumnIndex(box1),GridPane.getRowIndex(box2),GridPane.getColumnIndex(box2));
    }
    @FXML
    void SaveBtnPressed() throws IOException {
        gameSystem4.saveGameProgress(gameSystem4);
    }
    @FXML
    void LoadBtnPressed() throws IOException {
        gameSystem4 = gameSystem4.loadGameProgress(); Pane.requestFocus();
        Platform.runLater(() -> {
            // 更新界面，如更新玩家、盒子、步数等
            steps.setText(String.valueOf(gameSystem4.getSteps()));
            GridPane.setRowIndex(Niker, gameSystem4.getPlayerRow());
            GridPane.setColumnIndex(Niker, gameSystem4.getPlayerCol());
            GridPane.setRowIndex(box1, gameSystem4.getBoxRow(1));
            GridPane.setColumnIndex(box1, gameSystem4.getBoxCol(1));
            GridPane.setRowIndex(box2, gameSystem4.getBoxRow(2));
            GridPane.setColumnIndex(box2, gameSystem4.getBoxCol(2));
            currentColumnIndex = gameSystem4.getPlayerCol();
            currentRowIndex = gameSystem4.getPlayerRow();
        });
    }


    void stepsUpdate() {
        steps.setText(gameSystem4.getSteps() + "");
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

    Integer currentColumnIndex = gameSystem4.getPlayerCol();
    Integer currentRowIndex = gameSystem4.getPlayerRow();

    @FXML
    void DownBtnPressed() throws IOException {
        int targetRow = currentRowIndex + 1;
        //纯移动
        if (gameSystem4.notWall(currentColumnIndex, targetRow) &&!gameSystem4.isBox1(currentColumnIndex, targetRow)&&!gameSystem4.isBox2(currentColumnIndex, targetRow)) {
            currentRowIndex = targetRow;
            GridPane.setRowIndex(Niker, currentRowIndex);
            gameSystem4.moveoutNiker(currentColumnIndex, currentRowIndex-1);
            gameSystem4.moveinNiker(currentColumnIndex, currentRowIndex);
            stepsUpdate();
        }
        //推箱子
        if (gameSystem4.isBox1(currentColumnIndex, targetRow)) {
            if (gameSystem4.notWall(currentColumnIndex, targetRow + 1) &&!gameSystem4.isBox2(currentColumnIndex, targetRow+1)) {
                //可推
                currentRowIndex = targetRow;
                GridPane.setRowIndex(Niker, currentRowIndex);
                gameSystem4.moveoutNiker(currentColumnIndex, currentRowIndex-1);
                gameSystem4.moveinNiker(currentColumnIndex, currentRowIndex);
                GridPane.setRowIndex(box1, currentRowIndex+1);
                gameSystem4.moveoutBox(currentColumnIndex, currentRowIndex);
                gameSystem4.moveinBox(1,currentColumnIndex, currentRowIndex+1);
                stepsUpdate();
            }
        }
        if (gameSystem4.isBox2(currentColumnIndex, targetRow)) {
            if (gameSystem4.notWall(currentColumnIndex, targetRow + 1) &&!gameSystem4.isBox1(currentColumnIndex, targetRow+1)) {
                //可推
                currentRowIndex = targetRow;
                GridPane.setRowIndex(Niker, currentRowIndex);
                gameSystem4.moveoutNiker(currentColumnIndex, currentRowIndex-1);
                gameSystem4.moveinNiker(currentColumnIndex, currentRowIndex);
                GridPane.setRowIndex(box2, currentRowIndex+1);
                gameSystem4.moveoutBox(currentColumnIndex, currentRowIndex);
                gameSystem4.moveinBox(2,currentColumnIndex, currentRowIndex+1);
                stepsUpdate();
            }
        }
        gameSystem4.victoryJudge();
        gameSystem4.failedJudge();
    }

    @FXML
    void LeftBtnPressed() throws IOException {
        int targetColumn = currentColumnIndex - 1;
        //纯移动
        if (gameSystem4.notWall(targetColumn, currentRowIndex) &&!gameSystem4.isBox1(targetColumn, currentRowIndex)&&!gameSystem4.isBox2(targetColumn, currentRowIndex)) {
            currentColumnIndex = targetColumn;
            GridPane.setColumnIndex(Niker, currentColumnIndex);
            gameSystem4.moveoutNiker(currentColumnIndex+1, currentRowIndex);
            gameSystem4.moveinNiker(currentColumnIndex, currentRowIndex);
            stepsUpdate();
        }
        //推箱子
        if (gameSystem4.isBox1(targetColumn, currentRowIndex)) {
            if (gameSystem4.notWall(targetColumn - 1, currentRowIndex) &&!gameSystem4.isBox2(targetColumn-1, currentRowIndex)) {
                currentColumnIndex = targetColumn;
                GridPane.setColumnIndex(Niker, currentColumnIndex);
                gameSystem4.moveoutNiker(currentColumnIndex+1, currentRowIndex);
                gameSystem4.moveinNiker(currentColumnIndex, currentRowIndex);
                GridPane.setColumnIndex(box1, currentColumnIndex-1);
                gameSystem4.moveoutBox(currentColumnIndex, currentRowIndex);
                gameSystem4.moveinBox(1,currentColumnIndex-1, currentRowIndex);
                stepsUpdate();
            }
        }
        if (gameSystem4.isBox2(targetColumn, currentRowIndex)) {
            if (gameSystem4.notWall(targetColumn - 1, currentRowIndex) &&!gameSystem4.isBox1(targetColumn-1, currentRowIndex)) {
                currentColumnIndex = targetColumn;
                GridPane.setColumnIndex(Niker, currentColumnIndex);
                gameSystem4.moveoutNiker(currentColumnIndex+1, currentRowIndex);
                gameSystem4.moveinNiker(currentColumnIndex, currentRowIndex);
                GridPane.setColumnIndex(box2, currentColumnIndex-1);
                gameSystem4.moveoutBox(currentColumnIndex, currentRowIndex);
                gameSystem4.moveinBox(2,currentColumnIndex-1, currentRowIndex);
                stepsUpdate();
            }
        }
        gameSystem4.victoryJudge();
        gameSystem4.failedJudge();
    }

    @FXML
    void RightBtnPressed() throws IOException {
        int targetColumn = currentColumnIndex + 1;
        if (gameSystem4.notWall(targetColumn, currentRowIndex) &&!gameSystem4.isBox1(targetColumn, currentRowIndex)&&!gameSystem4.isBox2(targetColumn, currentRowIndex)) {
            currentColumnIndex = targetColumn;
            GridPane.setColumnIndex(Niker, currentColumnIndex);
            gameSystem4.moveoutNiker(currentColumnIndex-1, currentRowIndex);
            gameSystem4.moveinNiker(currentColumnIndex, currentRowIndex);
            stepsUpdate();
        }
        //推箱子
        if (gameSystem4.isBox1(targetColumn, currentRowIndex)) {
            if (gameSystem4.notWall(targetColumn + 1, currentRowIndex) &&!gameSystem4.isBox2(targetColumn+1, currentRowIndex)) {
                currentColumnIndex = targetColumn;
                GridPane.setColumnIndex(Niker, currentColumnIndex);
                gameSystem4.moveoutNiker(currentColumnIndex-1, currentRowIndex);
                gameSystem4.moveinNiker(currentColumnIndex, currentRowIndex);
                GridPane.setColumnIndex(box1, currentColumnIndex+1);
                gameSystem4.moveoutBox(currentColumnIndex, currentRowIndex);
                gameSystem4.moveinBox(1,currentColumnIndex+1, currentRowIndex);
                stepsUpdate();
            }
        }
        if (gameSystem4.isBox2(targetColumn, currentRowIndex)) {
            if (gameSystem4.notWall(targetColumn + 1, currentRowIndex) &&!gameSystem4.isBox1(targetColumn+1, currentRowIndex)) {
                currentColumnIndex = targetColumn;
                GridPane.setColumnIndex(Niker, currentColumnIndex);
                gameSystem4.moveoutNiker(currentColumnIndex-1, currentRowIndex);
                gameSystem4.moveinNiker(currentColumnIndex, currentRowIndex);
                GridPane.setColumnIndex(box2, currentColumnIndex+1);
                gameSystem4.moveoutBox(currentColumnIndex, currentRowIndex);
                gameSystem4.moveinBox(2,currentColumnIndex+1, currentRowIndex);
                stepsUpdate();
            }
        }
        gameSystem4.victoryJudge();
        gameSystem4.failedJudge();
    }

    @FXML
    void UpBtnPressed() throws IOException {
        int targetRow = currentRowIndex - 1;
        //纯移动
        if (gameSystem4.notWall(currentColumnIndex, targetRow) &&!gameSystem4.isBox1(currentColumnIndex, targetRow)&&!gameSystem4.isBox2(currentColumnIndex, targetRow)) {
            currentRowIndex = targetRow;
            GridPane.setRowIndex(Niker, currentRowIndex);
            gameSystem4.moveoutNiker(currentColumnIndex, currentRowIndex+1);
            gameSystem4.moveinNiker(currentColumnIndex, currentRowIndex);
            stepsUpdate();
        }
        //推箱子
        if (gameSystem4.isBox1(currentColumnIndex, targetRow)) {
            if (gameSystem4.notWall(currentColumnIndex, targetRow - 1) &&!gameSystem4.isBox2(currentColumnIndex, targetRow-1)) {
                currentRowIndex = targetRow;
                GridPane.setRowIndex(Niker, currentRowIndex);
                gameSystem4.moveoutNiker(currentColumnIndex, currentRowIndex+1);
                gameSystem4.moveinNiker(currentColumnIndex, currentRowIndex);
                GridPane.setRowIndex(box1, currentRowIndex-1);
                gameSystem4.moveoutBox(currentColumnIndex, currentRowIndex);
                gameSystem4.moveinBox(1,currentColumnIndex, currentRowIndex-1);
                stepsUpdate();
            }
        }
        if (gameSystem4.isBox2(currentColumnIndex, targetRow)) {
            if (gameSystem4.notWall(currentColumnIndex, targetRow - 1) &&!gameSystem4.isBox1(currentColumnIndex, targetRow-1)) {
                currentRowIndex = targetRow;
                GridPane.setRowIndex(Niker, currentRowIndex);
                gameSystem4.moveoutNiker(currentColumnIndex, currentRowIndex+1);
                gameSystem4.moveinNiker(currentColumnIndex, currentRowIndex);
                GridPane.setRowIndex(box2, currentRowIndex-1);
                gameSystem4.moveoutBox(currentColumnIndex, currentRowIndex);
                gameSystem4.moveinBox(2,currentColumnIndex, currentRowIndex-1);
                stepsUpdate();
            }
        }
        gameSystem4.victoryJudge();
        gameSystem4.failedJudge();
    }}

