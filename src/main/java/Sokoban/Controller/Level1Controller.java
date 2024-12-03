package Sokoban.Controller;

import Sokoban.Model.*;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
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
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import static Sokoban.Login_Application.primaryStage;
import java.util.Objects;
import static Sokoban.Model.GameSystem.*;

public class Level1Controller {
    @FXML
    public AnchorPane Pane;

    @FXML
    private Button Btn_back,Btn_down,Btn_up,Btn_left,Btn_right,Btn_home,Btn_load,Btn_save;
    @FXML
    private ImageView Img_Back,Img_home,Img_Move,Img_load,Img_save;
    @FXML
    private Label Label_Level1,Label_steps,Label_timer,steps,myTime;
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

    GameSystem gameSystem = new GameSystem(2,2,18,6,5);
    //在boxes，targets，boards，matrix数组中都规定大小并建立新引用，初始化全局变量
    //Gridpane静态方法不能再类体中调用，只能在initialize中调用，
    // 当 JavaFX 加载与控制器类相对应的 FXML 文件时，FXML 文件中定义的 UI 组件（如按钮、标签等）会被实例化并与控制器类中的相应字段进行绑定。
    //在 FXML 文件中定义的 UI 组件被完全创建并加入到场景图之后，initialize 方法被调用。此时，所有通过 @FXML 注解绑定的控件都已经可以使用。
    public void initialize() {
        Platform.runLater(() -> {
           gameSystem.setBox(1,GridPane.getColumnIndex(box1),GridPane.getRowIndex(box1));
           gameSystem.setBox(2,GridPane.getColumnIndex(box2),GridPane.getRowIndex(box2));
           //设置好system中Box的坐标
           gameSystem.addBoxPositons();
           //将Box量化到system的矩阵中
           gameSystem.setTarget(0,GridPane.getColumnIndex(target1),GridPane.getRowIndex(target1));
           gameSystem.setTarget(1,GridPane.getColumnIndex(target2),GridPane.getRowIndex(target2));
           gameSystem.addTargetPositons();
          //同样操作target
           // 遍历操作Board。注意！Gridpane中0时默认位置，不会在fxml中显示标出，会导致Index.valueOf空指针异常。要手动标出坐标
           Rectangle[] boards = {board1,board2,board3,board4,board5,board6,board7,board8,board9,board10,board11,board12,board13,board14,board15,board16,board17,board18};
           for (int i = 0; i < boards.length; i++) {
            gameSystem.setBoard(i, GridPane.getColumnIndex(boards[i]), GridPane.getRowIndex(boards[i]));
           }
           gameSystem.addBoardPositons();
           //操作player
           gameSystem.setPlayer(GridPane.getColumnIndex(Niker),GridPane.getRowIndex(Niker));
           gameSystem.addPlayerPositons(GridPane.getColumnIndex(Niker),GridPane.getRowIndex(Niker));
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
        setCurrentLevel(1);setCurrentLevel("1");
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
                Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
                alert2.setTitle("Quit");
                alert2.setHeaderText("Your progress has been saved.");
                alert2.setContentText("Your can load your progress next time.");
                alert2.showAndWait();
                saveGameProgress(gameSystem);
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
            }
        });
    }
    @FXML
    void BackBtnPressed() throws IOException {
        URL url = getClass().getResource("/Sokoban/Level1.fxml");
        Parent root = FXMLLoader.load(Objects.requireNonNull(url));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        gameSystem.reset(GridPane.getRowIndex(box1),GridPane.getColumnIndex(box1),GridPane.getRowIndex(box2),GridPane.getColumnIndex(box2));
    }

    @FXML
    void SaveBtnPressed() throws IOException {
        saveGameProgress(gameSystem);
    }
    @FXML
    void LoadBtnPressed() throws IOException {
        gameSystem = loadGameProgress(); Pane.requestFocus();
        Platform.runLater(() -> {
            // 更新界面，如更新玩家、盒子、步数等
            steps.setText(String.valueOf(gameSystem.getSteps()));
            myTime.setText(String.valueOf(gameSystem.getTime()));
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
