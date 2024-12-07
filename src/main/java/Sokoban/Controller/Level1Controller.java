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

import java.io.IOException;
import java.net.URL;
import static Sokoban.Login_Application.primaryStage;
import java.util.Objects;


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

    private Timeline timeline;


    GameSystem gameSystem = new GameSystem(2,2,18,6,5,30);
    //在boxes，targets，boards，matrix数组中都规定大小并建立新引用，初始化全局变量
    //Gridpane静态方法不能再类体中调用，只能在initialize中调用，
    // 当 JavaFX 加载与控制器类相对应的 FXML 文件时，FXML 文件中定义的 UI 组件（如按钮、标签等）会被实例化并与控制器类中的相应字段进行绑定。
    //在 FXML 文件中定义的 UI 组件被完全创建并加入到场景图之后，initialize 方法被调用。此时，所有通过 @FXML 注解绑定的控件都已经可以使用。
    public void initialize() {
        //JavaFX是单线程模型，所有的UI更新都必须在JavaFX线程上完成，否则会导致不一致性或错误
        //任何需要在JavaFX线程上执行的代码（例如，修改UI组件、更新标签内容等）都应该放在这个方法里面。
        Platform.runLater(() -> {
           gameSystem.setBox(1,GridPane.getColumnIndex(box1),GridPane.getRowIndex(box1));
           gameSystem.setBox(2,GridPane.getColumnIndex(box2),GridPane.getRowIndex(box2));
           //设置好system中Box的坐标
           gameSystem.addBoxPositons();
            gameSystem.setBoxNum(2);
           //将Box量化到system的矩阵中
           gameSystem.setTarget(0,GridPane.getColumnIndex(target1),GridPane.getRowIndex(target1));
           gameSystem.setTarget(1,GridPane.getColumnIndex(target2),GridPane.getRowIndex(target2));
           gameSystem.addTargetPositons();
           gameSystem.setTargNum(2);
          //同样操作target
           // 遍历操作Board。注意！Gridpane中0时默认位置，不会在fxml中显示标出，会导致Index.valueOf空指针异常。要手动标出坐标
           Rectangle[] boards = {board1,board2,board3,board4,board5,board6,board7,board8,board9,board10,board11,board12,board13,board14,board15,board16,board17,board18};
           for (int i = 0; i < boards.length; i++) {
            gameSystem.setBoard(i, GridPane.getColumnIndex(boards[i]), GridPane.getRowIndex(boards[i]));
           }
           gameSystem.addBoardPositons();
           //操作player
           gameSystem.setPlayer(1,1);
           gameSystem.addPlayerPositons(1,1);

           //判断是否为游客模式
            if (GameSystem.verifyVisitor()){
                Img_load.setVisible(false);
                Img_save.setVisible(false);
                Img_home.setVisible(false);
                Btn_load.setDisable(true);
                Btn_save.setDisable(true);
                Btn_home.setDisable(true);
            }
            GameSystem.setCurrentLevel(1);GameSystem.setCurrentLevel("Level1");
            Pane.requestFocus(); // 确保焦点设置

            //计时模式

            if (GameSystem.isTimeMode()) {
                timeline = new Timeline(
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
                timeline.setCycleCount(Timeline.INDEFINITE); // 设置循环次数
                timeline.play(); // 开始计时
            }
            else {
                myTime.setVisible(false);
                Label_timer.setVisible(false);
            }

            //图形初始化
            Image SUST =new Image("file:src/main/resources/Sokoban/Sokoban/pictures/SUST.jpeg");
            Niker.setFill(new ImagePattern(SUST));
        });




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
                gameSystem.setGameOver(true);stopTimeline();
                gameSystem.saveGameProgress(gameSystem);
                //切换场景
                Platform.runLater(() -> {
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
                });
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
        URL url = getClass().getResource("/Sokoban/Level1.fxml");
            Parent root = null;
            try {
                root = FXMLLoader.load(Objects.requireNonNull(url));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        gameSystem.reset(GridPane.getRowIndex(box1),GridPane.getColumnIndex(box1),GridPane.getRowIndex(box2),GridPane.getColumnIndex(box2));
        });
    }

    @FXML
    void SaveBtnPressed() throws IOException {
        gameSystem.saveGameProgress(gameSystem);
    }
    /*  控件偏移量不准确
        如果动画尚未完成，或者控件的 translateX 和 translateY 尚未被正确重置，频繁点击会叠加或打断原有的动画，导致偏移不准确。
        文件加载来不及：
        文件加载操作是 I/O 密集型任务，如果不在单独的线程中运行，可能会阻塞 JavaFX 应用线程，导致界面卡顿或无法及时响应。
        事件冲突：
        重复触发的事件可能导致多个任务竞争执行，出现逻辑错误。例如，两个文件加载或位置更新任务可能同时进行，造成状态紊乱。*/
    @FXML
    void LoadBtnPressed() throws IOException {
        Btn_load.setDisable(true);

        Task<Void> loadTask = new Task<>() {
            @Override
            protected Void call() throws Exception {
                stopTimeline();
                gameSystem = gameSystem.loadGameProgress();
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
                        steps.setText(String.valueOf(gameSystem.getSteps()));
                        GridPane.setRowIndex(Niker, gameSystem.getPlayerRow());
                        GridPane.setColumnIndex(Niker, gameSystem.getPlayerCol());
                        GridPane.setRowIndex(box1, gameSystem.getBoxRow(1));
                        GridPane.setColumnIndex(box1, gameSystem.getBoxCol(1));
                        GridPane.setRowIndex(box2, gameSystem.getBoxRow(2));
                        GridPane.setColumnIndex(box2, gameSystem.getBoxCol(2));
                        currentColumnIndex = gameSystem.getPlayerCol();
                        currentRowIndex = gameSystem.getPlayerRow();
                        if (GameSystem.isTimeMode()) {
                            timeline = new Timeline(
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
        steps.setText(gameSystem.getSteps() + "");
    }


    @FXML
    void MovePlayer(@NotNull KeyEvent event) throws IOException, InterruptedException {
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


    //以下是移动事件
    Integer currentColumnIndex = 1;
    Integer currentRowIndex = 1;




    @FXML
    void DownBtnPressed() throws IOException, InterruptedException {
        int targetRow = currentRowIndex + 1;
        //纯移动
        if (gameSystem.notWall(currentColumnIndex, targetRow) &&!gameSystem.isBox1(currentColumnIndex, targetRow)&&!gameSystem.isBox2(currentColumnIndex, targetRow)) {
            try {
            Btn_down.setDisable(true);
            currentRowIndex = targetRow;
            AnimationController.MoveDown(Niker, currentColumnIndex, currentRowIndex);//动画
            gameSystem.moveoutNiker(currentColumnIndex, currentRowIndex-1);
            gameSystem.moveinNiker(currentColumnIndex, currentRowIndex);
            stepsUpdate();}
            finally {
                Btn_down.setDisable(false);
            }

        }
        //推箱子
        if (gameSystem.isBox1(currentColumnIndex, targetRow)) {
            if (gameSystem.notWall(currentColumnIndex, targetRow + 1) &&!gameSystem.isBox2(currentColumnIndex, targetRow+1)) {
                //可推
                try {Btn_down.setDisable(true);
                currentRowIndex = targetRow;
                AnimationController.MoveDown(Niker, currentColumnIndex, currentRowIndex);//动画
                gameSystem.moveoutNiker(currentColumnIndex, currentRowIndex-1);
                gameSystem.moveinNiker(currentColumnIndex, currentRowIndex);
                AnimationController.MoveDown(box1, currentColumnIndex, currentRowIndex+1);//动画
                gameSystem.moveoutBox(currentColumnIndex, currentRowIndex);
                gameSystem.moveinBox(1,currentColumnIndex, currentRowIndex+1);
                stepsUpdate();
                }
                finally {
                Btn_down.setDisable(false);}
            }
        }
        if (gameSystem.isBox2(currentColumnIndex, targetRow)) {
            if (gameSystem.notWall(currentColumnIndex, targetRow + 1) &&!gameSystem.isBox1(currentColumnIndex, targetRow+1)) {
                //可推
                try {Btn_down.setDisable(true);
                currentRowIndex = targetRow;
                AnimationController.MoveDown(Niker, currentColumnIndex, currentRowIndex);//动画
                gameSystem.moveoutNiker(currentColumnIndex, currentRowIndex-1);
                gameSystem.moveinNiker(currentColumnIndex, currentRowIndex);
                AnimationController.MoveDown(box2, currentColumnIndex, currentRowIndex+1);//动画
                gameSystem.moveoutBox(currentColumnIndex, currentRowIndex);
                gameSystem.moveinBox(2,currentColumnIndex, currentRowIndex+1);
                stepsUpdate();
                }
                finally {Btn_down.setDisable(false);}

            }
        }
        gameSystem.victoryJudge();
        gameSystem.failedJudge();
        if (gameSystem.isGameOver()) {
            stopTimeline();
        }
    }

    @FXML
    void LeftBtnPressed() throws IOException, InterruptedException {
        int targetColumn = currentColumnIndex - 1;
        //纯移动
        if (gameSystem.notWall(targetColumn, currentRowIndex) &&!gameSystem.isBox1(targetColumn, currentRowIndex)&&!gameSystem.isBox2(targetColumn, currentRowIndex)) {
            try{Btn_left.setDisable(true);
            currentColumnIndex = targetColumn;
            AnimationController.MoveLeft(Niker, currentColumnIndex, currentRowIndex);//动画
            gameSystem.moveoutNiker(currentColumnIndex+1, currentRowIndex);
            gameSystem.moveinNiker(currentColumnIndex, currentRowIndex);
            stepsUpdate();
            }
            finally {Btn_left.setDisable(false);}

        }
        //推箱子
        if (gameSystem.isBox1(targetColumn, currentRowIndex)) {
            if (gameSystem.notWall(targetColumn - 1, currentRowIndex) &&!gameSystem.isBox2(targetColumn-1, currentRowIndex)) {
                try{Btn_left.setDisable(true);
                currentColumnIndex = targetColumn;
                AnimationController.MoveLeft(Niker, currentColumnIndex, currentRowIndex);//动画
                gameSystem.moveoutNiker(currentColumnIndex+1, currentRowIndex);
                gameSystem.moveinNiker(currentColumnIndex, currentRowIndex);
                AnimationController.MoveLeft(box1, currentColumnIndex-1, currentRowIndex);//动画
                gameSystem.moveoutBox(currentColumnIndex, currentRowIndex);
                gameSystem.moveinBox(1,currentColumnIndex-1, currentRowIndex);
                stepsUpdate();
                }
                finally {Btn_left.setDisable(false);}

            }
        }
        if (gameSystem.isBox2(targetColumn, currentRowIndex)) {
            if (gameSystem.notWall(targetColumn - 1, currentRowIndex) &&!gameSystem.isBox1(targetColumn-1, currentRowIndex)) {
                try{Btn_left.setDisable(true);
                currentColumnIndex = targetColumn;
                AnimationController.MoveLeft(Niker, currentColumnIndex, currentRowIndex);//动画
                gameSystem.moveoutNiker(currentColumnIndex+1, currentRowIndex);
                gameSystem.moveinNiker(currentColumnIndex, currentRowIndex);
                AnimationController.MoveLeft(box2, currentColumnIndex-1, currentRowIndex);//动画
                gameSystem.moveoutBox(currentColumnIndex, currentRowIndex);
                gameSystem.moveinBox(2,currentColumnIndex-1, currentRowIndex);
                stepsUpdate();
                }
                finally {Btn_left.setDisable(false);}

            }
        }
        gameSystem.victoryJudge();
        gameSystem.failedJudge();
        if (gameSystem.isGameOver()) {
            stopTimeline();
        }
    }


    @FXML
    void RightBtnPressed() throws IOException, InterruptedException {
        int targetColumn = currentColumnIndex + 1;
        if (gameSystem.notWall(targetColumn, currentRowIndex) &&!gameSystem.isBox1(targetColumn, currentRowIndex)&&!gameSystem.isBox2(targetColumn, currentRowIndex)) {
            try {
                Btn_right.setDisable(true);
                currentColumnIndex = targetColumn;
                AnimationController.MoveRight(Niker, currentColumnIndex, currentRowIndex);//动画
                gameSystem.moveoutNiker(currentColumnIndex - 1, currentRowIndex);
                gameSystem.moveinNiker(currentColumnIndex, currentRowIndex);
                stepsUpdate();
            }
            finally {Btn_right.setDisable(false);}

        }
        //推箱子
        if (gameSystem.isBox1(targetColumn, currentRowIndex)) {
            if (gameSystem.notWall(targetColumn + 1, currentRowIndex) &&!gameSystem.isBox2(targetColumn+1, currentRowIndex)) {
                try {
                    Btn_right.setDisable(true);
                    currentColumnIndex = targetColumn;
                    AnimationController.MoveRight(Niker, currentColumnIndex, currentRowIndex);//动画
                    gameSystem.moveoutNiker(currentColumnIndex - 1, currentRowIndex);
                    gameSystem.moveinNiker(currentColumnIndex, currentRowIndex);
                    AnimationController.MoveRight(box1, currentColumnIndex + 1, currentRowIndex);
                    gameSystem.moveoutBox(currentColumnIndex, currentRowIndex);
                    gameSystem.moveinBox(1, currentColumnIndex + 1, currentRowIndex);
                    stepsUpdate();
                }
                finally {Btn_right.setDisable(false);}
            }
        }
        if (gameSystem.isBox2(targetColumn, currentRowIndex)) {
            if (gameSystem.notWall(targetColumn + 1, currentRowIndex) &&!gameSystem.isBox1(targetColumn+1, currentRowIndex)) {
                try {
                    Btn_right.setDisable(true);
                    currentColumnIndex = targetColumn;
                    AnimationController.MoveRight(Niker, currentColumnIndex, currentRowIndex);
                    gameSystem.moveoutNiker(currentColumnIndex - 1, currentRowIndex);
                    gameSystem.moveinNiker(currentColumnIndex, currentRowIndex);
                    AnimationController.MoveRight(box2, currentColumnIndex + 1, currentRowIndex);
                    gameSystem.moveoutBox(currentColumnIndex, currentRowIndex);
                    gameSystem.moveinBox(2, currentColumnIndex + 1, currentRowIndex);
                    stepsUpdate();
                }
                finally {Btn_right.setDisable(false);}

            }
        }
        gameSystem.victoryJudge();
        gameSystem.failedJudge();
        if (gameSystem.isGameOver()) {
            stopTimeline();
        }

    }

    @FXML
    void UpBtnPressed() throws IOException, InterruptedException {
        int targetRow = currentRowIndex - 1;
        //纯移动
        if (gameSystem.notWall(currentColumnIndex, targetRow) &&!gameSystem.isBox1(currentColumnIndex, targetRow)&&!gameSystem.isBox2(currentColumnIndex, targetRow)) {
            try {
                Btn_up.setDisable(true);
                currentRowIndex = targetRow;
                AnimationController.MoveUp(Niker, currentColumnIndex, currentRowIndex);//动画
                gameSystem.moveoutNiker(currentColumnIndex, currentRowIndex + 1);
                gameSystem.moveinNiker(currentColumnIndex, currentRowIndex);
                stepsUpdate();
            }
            finally {Btn_up.setDisable(false);}
        }
        //推箱子
        if (gameSystem.isBox1(currentColumnIndex, targetRow)) {
            if (gameSystem.notWall(currentColumnIndex, targetRow - 1) &&!gameSystem.isBox2(currentColumnIndex, targetRow-1)) {
                try {
                    Btn_up.setDisable(true);
                    currentRowIndex = targetRow;
                    AnimationController.MoveUp(Niker, currentColumnIndex, currentRowIndex);//动画
                    gameSystem.moveoutNiker(currentColumnIndex, currentRowIndex + 1);
                    gameSystem.moveinNiker(currentColumnIndex, currentRowIndex);
                    AnimationController.MoveUp(box1, currentColumnIndex, currentRowIndex - 1);//动画
                    gameSystem.moveoutBox(currentColumnIndex, currentRowIndex);
                    gameSystem.moveinBox(1, currentColumnIndex, currentRowIndex - 1);
                    stepsUpdate();

                }
                finally {Btn_up.setDisable(false);}

            }
        }
        if (gameSystem.isBox2(currentColumnIndex, targetRow)) {
            if (gameSystem.notWall(currentColumnIndex, targetRow - 1) &&!gameSystem.isBox1(currentColumnIndex, targetRow-1)) {
                try {
                    Btn_up.setDisable(true);
                    currentRowIndex = targetRow;
                    AnimationController.MoveUp(Niker, currentColumnIndex, currentRowIndex);//动画
                    gameSystem.moveoutNiker(currentColumnIndex, currentRowIndex + 1);
                    gameSystem.moveinNiker(currentColumnIndex, currentRowIndex);
                    AnimationController.MoveUp(box2, currentColumnIndex, currentRowIndex - 1);//动画
                    gameSystem.moveoutBox(currentColumnIndex, currentRowIndex);
                    gameSystem.moveinBox(2, currentColumnIndex, currentRowIndex - 1);
                    stepsUpdate();
                }
                finally {Btn_up.setDisable(false);}

            }
        }
        gameSystem.victoryJudge();
        gameSystem.failedJudge();
        if (gameSystem.isGameOver()) {
            stopTimeline();
        }
    }
}
