package Sokoban.Controller;

import Sokoban.Model.Account;
import Sokoban.Model.GameSystem;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
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
import java.util.Objects;

import static Sokoban.Login_Application.primaryStage;

public class Level6Controller {

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
    private ImageView Img_home;

    @FXML
    private ImageView Img_load;

    @FXML
    private ImageView Img_save;

    @FXML
    private Label Label_Level1;

    @FXML
    private Label Label_steps;

    @FXML
    private Label Label_timer;

    @FXML
    private GridPane Movement;

    @FXML
    private Circle Niker;

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
    private Rectangle board26;

    @FXML
    private Rectangle board27;

    @FXML
    private Rectangle board28;

    @FXML
    private Rectangle board29;

    @FXML
    private Rectangle board3;

    @FXML
    private Rectangle board30;

    @FXML
    private Rectangle board31;

    @FXML
    private Rectangle board32;

    @FXML
    private Rectangle board33;

    @FXML
    private Rectangle board34;

    @FXML
    private Rectangle board35;

    @FXML
    private Rectangle board36;

    @FXML
    private Rectangle board37;

    @FXML
    private Rectangle board38;

    @FXML
    private Rectangle board39;

    @FXML
    private Rectangle board4;

    @FXML
    private Rectangle board40;

    @FXML
    private Rectangle board41;

    @FXML
    private Rectangle board42;

    @FXML
    private Rectangle board43;

    @FXML
    private Rectangle board44;

    @FXML
    private Rectangle board45;

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
    private Rectangle box4;

    @FXML
    private Rectangle box5;

    @FXML
    private Rectangle ground1;

    @FXML
    private Rectangle ground11;

    @FXML
    private Rectangle ground110;

    @FXML
    private Rectangle ground111;

    @FXML
    private Rectangle ground112;

    @FXML
    private Rectangle ground113;

    @FXML
    private Rectangle ground114;

    @FXML
    private Rectangle ground115;

    @FXML
    private Rectangle ground116;

    @FXML
    private Rectangle ground117;

    @FXML
    private Rectangle ground118;

    @FXML
    private Rectangle ground119;

    @FXML
    private Rectangle ground12;

    @FXML
    private Rectangle ground120;

    @FXML
    private Rectangle ground121;

    @FXML
    private Rectangle ground122;

    @FXML
    private Rectangle ground123;

    @FXML
    private Rectangle ground124;

    @FXML
    private Rectangle ground125;

    @FXML
    private Rectangle ground126;

    @FXML
    private Rectangle ground127;

    @FXML
    private Rectangle ground128;

    @FXML
    private Rectangle ground129;

    @FXML
    private Rectangle ground13;

    @FXML
    private Rectangle ground130;

    @FXML
    private Rectangle ground131;

    @FXML
    private Rectangle ground132;

    @FXML
    private Rectangle ground133;

    @FXML
    private Rectangle ground134;

    @FXML
    private Rectangle ground135;

    @FXML
    private Rectangle ground136;

    @FXML
    private Rectangle ground137;

    @FXML
    private Rectangle ground138;

    @FXML
    private Rectangle ground139;

    @FXML
    private Rectangle ground14;

    @FXML
    private Rectangle ground140;

    @FXML
    private Rectangle ground141;

    @FXML
    private Rectangle ground142;

    @FXML
    private Rectangle ground143;

    @FXML
    private Rectangle ground144;

    @FXML
    private Rectangle ground145;

    @FXML
    private Rectangle ground146;

    @FXML
    private Rectangle ground147;

    @FXML
    private Rectangle ground148;

    @FXML
    private Rectangle ground149;

    @FXML
    private Rectangle ground15;

    @FXML
    private Rectangle ground150;

    @FXML
    private Rectangle ground151;

    @FXML
    private Rectangle ground152;

    @FXML
    private Rectangle ground153;

    @FXML
    private Rectangle ground154;

    @FXML
    private Rectangle ground155;

    @FXML
    private Rectangle ground156;

    @FXML
    private Rectangle ground157;

    @FXML
    private Rectangle ground158;

    @FXML
    private Rectangle ground159;

    @FXML
    private Rectangle ground16;

    @FXML
    private Rectangle ground160;

    @FXML
    private Rectangle ground161;

    @FXML
    private Rectangle ground162;

    @FXML
    private Rectangle ground163;

    @FXML
    private Rectangle ground164;

    @FXML
    private Rectangle ground165;

    @FXML
    private Rectangle ground166;

    @FXML
    private Rectangle ground167;

    @FXML
    private Rectangle ground168;

    @FXML
    private Rectangle ground169;

    @FXML
    private Rectangle ground17;

    @FXML
    private Rectangle ground170;

    @FXML
    private Rectangle ground171;

    @FXML
    private Rectangle ground172;

    @FXML
    private Rectangle ground173;

    @FXML
    private Rectangle ground174;

    @FXML
    private Rectangle ground175;

    @FXML
    private Rectangle ground176;

    @FXML
    private Rectangle ground177;

    @FXML
    private Rectangle ground178;

    @FXML
    private Rectangle ground179;

    @FXML
    private Rectangle ground18;

    @FXML
    private Rectangle ground180;

    @FXML
    private Rectangle ground181;

    @FXML
    private Rectangle ground182;

    @FXML
    private Rectangle ground183;

    @FXML
    private Rectangle ground184;

    @FXML
    private Rectangle ground185;

    @FXML
    private Rectangle ground186;

    @FXML
    private Rectangle ground187;

    @FXML
    private Rectangle ground188;

    @FXML
    private Rectangle ground189;

    @FXML
    private Rectangle ground19;

    @FXML
    private Rectangle ground190;

    @FXML
    private Rectangle ground191;

    @FXML
    private Rectangle ground192;

    @FXML
    private Rectangle ground193;

    @FXML
    private Label myTime;

    @FXML
    private AnchorPane pane;

    @FXML
    private Label space;

    @FXML
    private Label steps;

    @FXML
    private Polygon target1;

    @FXML
    private Polygon target2;

    @FXML
    private Polygon target3;

    @FXML
    private Polygon target4;

    @FXML
    private Polygon target5;

    private Timeline timeline;


    GameSystem gameSystem6=new GameSystem(5,5,45,12,10,200);

    public void initialize() {
        Platform.runLater(() -> {
            //图形初始化
            Image SUST = new Image("file:src/main/resources/Sokoban/Sokoban/pictures/SUST.jpeg");
            Niker.setFill(new ImagePattern(SUST));

            Image imageUp = new Image(Objects.requireNonNull(getClass().getResource("/Sokoban/Sokoban/pictures/up.png")).toExternalForm());
            ImageView imageViewUp = new ImageView(imageUp);
            imageViewUp.setFitHeight(60);
            imageViewUp.setFitWidth(60);
            Btn_up.setGraphic(imageViewUp);

            Image imageDown = new Image(Objects.requireNonNull(getClass().getResource("/Sokoban/Sokoban/pictures/down.png")).toExternalForm());
            ImageView imageViewDown = new ImageView(imageDown);
            imageViewDown.setFitHeight(60);
            imageViewDown.setFitWidth(60);
            Btn_down.setGraphic(imageViewDown);

            Image imageLeft = new Image(Objects.requireNonNull(getClass().getResource("/Sokoban/Sokoban/pictures/left.png")).toExternalForm());
            ImageView imageViewLeft = new ImageView(imageLeft);
            imageViewLeft.setFitHeight(60);
            imageViewLeft.setFitWidth(60);
            Btn_left.setGraphic(imageViewLeft);

            Image imageRight = new Image(Objects.requireNonNull(getClass().getResource("/Sokoban/Sokoban/pictures/right.png")).toExternalForm());
            ImageView imageViewRight = new ImageView(imageRight);
            imageViewRight.setFitHeight(60);
            imageViewRight.setFitWidth(60);
            Btn_right.setGraphic(imageViewRight);
            gameSystem6.setBox(1, GridPane.getColumnIndex(box1), GridPane.getRowIndex(box1));
            gameSystem6.setBox(2, GridPane.getColumnIndex(box2), GridPane.getRowIndex(box2));
            gameSystem6.setBox(3, GridPane.getColumnIndex(box3), GridPane.getRowIndex(box3));
            gameSystem6.setBox(4, GridPane.getColumnIndex(box4), GridPane.getRowIndex(box4));
            gameSystem6.setBox(5, GridPane.getColumnIndex(box5), GridPane.getRowIndex(box5));
            //设置好system中Box的坐标
            gameSystem6.addBoxPositons();
            gameSystem6.setBoxNum(5);
            //将Box量化到system的矩阵中
            gameSystem6.setTarget(0, GridPane.getColumnIndex(target1), GridPane.getRowIndex(target1));
            gameSystem6.setTarget(1, GridPane.getColumnIndex(target2), GridPane.getRowIndex(target2));
            gameSystem6.setTarget(2, GridPane.getColumnIndex(target3), GridPane.getRowIndex(target3));
            gameSystem6.setTarget(3, GridPane.getColumnIndex(target4), GridPane.getRowIndex(target4));
            gameSystem6.setTarget(4, GridPane.getColumnIndex(target5), GridPane.getRowIndex(target5));
            gameSystem6.addTargetPositons();
            //同样操作target
            // 遍历操作Board。注意！Gridpane中0时默认位置，不会在fxml中显示标出，会导致Index.valueOf空指针异常。要手动标出坐标
            Rectangle[] boards = {board1, board2, board3, board4, board5, board6, board7, board8, board9, board10, board11, board12, board13, board14, board15, board16, board17, board18, board19, board20, board21, board22, board23,board24, board25, board26, board27, board28, board29, board30, board31, board32, board33, board34, board35, board36, board37, board38, board39, board40, board41, board42, board43, board44, board45};
            for (int i = 0; i < boards.length; i++) {
                gameSystem6.setBoard(i, GridPane.getColumnIndex(boards[i]), GridPane.getRowIndex(boards[i]));
            }
            gameSystem6.addBoardPositons();
            //操作player

            gameSystem6.setPlayer(GridPane.getColumnIndex(Niker), GridPane.getRowIndex(Niker));
            gameSystem6.addPlayerPositons(GridPane.getColumnIndex(Niker), GridPane.getRowIndex(Niker));
            gameSystem6.setPlayeriniCol(GridPane.getColumnIndex(Niker));
            gameSystem6.setPlayeriniRow(GridPane.getRowIndex(Niker));
            Account account = Account.loadAccount();
            account.setCurrentLevel(6);Account.saveAccount(account);
            pane.requestFocus(); // 确保焦点设置

            //计时模式
            gameSystem6.setTimeMode(LevelSceneController.isL6isTimeMode());
            if (gameSystem6.isTimeMode()) {
                myTime.setVisible(true);
                Label_timer.setVisible(true);
                timeline = new Timeline(
                        new KeyFrame(Duration.seconds(1), event -> {
                            gameSystem6.setTimeRemaining(gameSystem6.getTimeRemaining() - 1); // 每秒减少 1
                            myTime.setText(String.valueOf(gameSystem6.getTimeRemaining())); // 更新标签文字

                            // 检查倒计时是否结束
                            if (gameSystem6.getTimeRemaining() <= 0) {
                                myTime.setText("time's up");
                                URL url = getClass().getResource("/Sokoban/Fxml/Failed.fxml");
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
            } else {
                myTime.setVisible(false);
                Label_timer.setVisible(false);
            }


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
                AudioManager.playbackgroundPeace();
                gameSystem6.setGameOver(true);
                stopTimeline();
                gameSystem6.saveGame6Progress(gameSystem6);
                //切换场景
                URL url = getClass().getResource("/Sokoban/Fxml/LevelScene.fxml");
                Parent root;
                try {
                    root = FXMLLoader.load(Objects.requireNonNull(url));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                //设置场景
                Scene scene = new Scene(root);
                primaryStage.setScene(scene);
            } else {
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
            URL url = getClass().getResource("/Sokoban/Fxml/Level6.fxml");
            Parent root = null;
            try {
                root = FXMLLoader.load(Objects.requireNonNull(url));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            gameSystem6.reset(GridPane.getRowIndex(box1), GridPane.getColumnIndex(box1), GridPane.getRowIndex(box2), GridPane.getColumnIndex(box2), GridPane.getRowIndex(box3), GridPane.getColumnIndex(box3), GridPane.getRowIndex(box4), GridPane.getColumnIndex(box4), GridPane.getRowIndex(box5), GridPane.getColumnIndex(box5));
        });
    }

    @FXML
    void SaveBtnPressed() throws IOException {
        gameSystem6.saveGame6Progress(gameSystem6);
    }

    @FXML
    void LoadBtnPressed() throws IOException {
        Btn_load.setDisable(true);

        Task<Void> loadTask = new Task<>() {
            @Nullable
            @Override
            protected Void call() throws Exception {
                stopTimeline();
                gameSystem6 = gameSystem6.loadGame6Progress();
                return null;
            }

            @Override
            protected void succeeded() {
                Platform.runLater(() -> {
                    try {
                        // 更新界面
                        // javafx位置变化和css动态变化是叠加的,先设偏移量为0
                        AnimationManager.resetNodePosition(Niker);
                        AnimationManager.resetNodePosition(box1);
                        AnimationManager.resetNodePosition(box2);
                        AnimationManager.resetNodePosition(box3);
                        AnimationManager.resetNodePosition(box4);
                        AnimationManager.resetNodePosition(box5);
                        steps.setText(String.valueOf(gameSystem6.getSteps()));
                        GridPane.setRowIndex(Niker, gameSystem6.getPlayerRow());
                        GridPane.setColumnIndex(Niker, gameSystem6.getPlayerCol());
                        GridPane.setRowIndex(box1, gameSystem6.getBoxRow(1));
                        GridPane.setColumnIndex(box1, gameSystem6.getBoxCol(1));
                        GridPane.setRowIndex(box2, gameSystem6.getBoxRow(2));
                        GridPane.setColumnIndex(box2, gameSystem6.getBoxCol(2));
                        GridPane.setRowIndex(box3, gameSystem6.getBoxRow(3));
                        GridPane.setColumnIndex(box3, gameSystem6.getBoxCol(3));
                        GridPane.setRowIndex(box4, gameSystem6.getBoxRow(4));
                        GridPane.setColumnIndex(box4, gameSystem6.getBoxCol(4));
                        GridPane.setRowIndex(box5, gameSystem6.getBoxRow(5));
                        GridPane.setColumnIndex(box5, gameSystem6.getBoxCol(5));
                        currentColumnIndex = gameSystem6.getPlayerCol();
                        currentRowIndex = gameSystem6.getPlayerRow();
                        gameSystem6.setTimeMode(LevelSceneController.isL5isTimeMode());
                        if (gameSystem6.isTimeMode()) {
                            myTime.setVisible(true);
                            Label_timer.setVisible(true);
                            timeline = new Timeline(
                                    new KeyFrame(Duration.seconds(1), event -> {
                                        gameSystem6.setTimeRemaining(gameSystem6.getTimeRemaining() - 1); // 每秒减少 1
                                        myTime.setText(String.valueOf(gameSystem6.getTimeRemaining())); // 更新标签文字

                                        // 检查倒计时是否结束
                                        if (gameSystem6.getTimeRemaining() <= 0) {
                                            myTime.setText("time's up");
                                            URL url = getClass().getResource("/Sokoban/Fxml/Failed.fxml");
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
                        } else {
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

    Integer currentColumnIndex = 4;
    Integer currentRowIndex = 8;
    AudioManager moveAudio = new AudioManager();


    void stepsUpdate() {
        steps.setText(gameSystem6.getSteps() + "");
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



    @FXML
    void DownBtnPressed() throws IOException {
        int targetRow = currentRowIndex + 1;
        //纯移动
        if (gameSystem6.notWall(currentColumnIndex, targetRow) &&!gameSystem6.isBox1(currentColumnIndex, targetRow)&&!gameSystem6.isBox2(currentColumnIndex, targetRow)&&!gameSystem6.isBox3(currentColumnIndex, targetRow)&&!gameSystem6.isBox4(currentColumnIndex, targetRow)&&!gameSystem6.isBox5(currentColumnIndex, targetRow)) {

            moveAudio.playmoveSound();
            currentRowIndex = targetRow;
            AnimationManager.MoveDown(Niker, currentColumnIndex, currentRowIndex);//动画
            gameSystem6.moveoutNiker(currentColumnIndex, currentRowIndex-1);
            gameSystem6.moveinNiker(currentColumnIndex, currentRowIndex);
            stepsUpdate();
        }
        //推箱子
        if (gameSystem6.isBox1(currentColumnIndex, targetRow)) {
            if (gameSystem6.notWall(currentColumnIndex, targetRow + 1) &&!gameSystem6.isBox2(currentColumnIndex, targetRow+1)&&!gameSystem6.isBox3(currentColumnIndex, targetRow+1)&&!gameSystem6.isBox4(currentColumnIndex, targetRow+1)&&!gameSystem6.isBox5(currentColumnIndex, targetRow+1)) {
                //可推
                moveAudio.playpushBox();
                moveAudio.playmoveSound();
                currentRowIndex = targetRow;
                AnimationManager.MoveDown(Niker, currentColumnIndex, currentRowIndex);//动画
                gameSystem6.moveoutNiker(currentColumnIndex, currentRowIndex-1);
                gameSystem6.moveinNiker(currentColumnIndex, currentRowIndex);
                AnimationManager.MoveDown(box1, currentColumnIndex, currentRowIndex+1);//动画
                gameSystem6.moveoutBox(currentColumnIndex, currentRowIndex);
                gameSystem6.moveinBox(1,currentColumnIndex, currentRowIndex+1);
                stepsUpdate();
            }
        }
        if (gameSystem6.isBox2(currentColumnIndex, targetRow)) {
            if (gameSystem6.notWall(currentColumnIndex, targetRow + 1) &&!gameSystem6.isBox1(currentColumnIndex, targetRow+1)&&!gameSystem6.isBox3(currentColumnIndex, targetRow+1)&&!gameSystem6.isBox4(currentColumnIndex, targetRow+1)&&!gameSystem6.isBox5(currentColumnIndex, targetRow+1)) {
                //可推
                moveAudio.playpushBox();
                moveAudio.playmoveSound();
                currentRowIndex = targetRow;
                AnimationManager.MoveDown(Niker, currentColumnIndex, currentRowIndex);//动画
                gameSystem6.moveoutNiker(currentColumnIndex, currentRowIndex-1);
                gameSystem6.moveinNiker(currentColumnIndex, currentRowIndex);
                AnimationManager.MoveDown(box2, currentColumnIndex, currentRowIndex+1);//动画
                gameSystem6.moveoutBox(currentColumnIndex, currentRowIndex);
                gameSystem6.moveinBox(2,currentColumnIndex, currentRowIndex+1);
                stepsUpdate();
            }
        }
        if (gameSystem6.isBox3(currentColumnIndex, targetRow)) {
            if (gameSystem6.notWall(currentColumnIndex, targetRow + 1) &&!gameSystem6.isBox2(currentColumnIndex, targetRow+1)&&!gameSystem6.isBox1(currentColumnIndex, targetRow+1)&&!gameSystem6.isBox4(currentColumnIndex, targetRow+1)&&!gameSystem6.isBox5(currentColumnIndex, targetRow+1)) {
                //可推
                moveAudio.playpushBox();
                moveAudio.playmoveSound();
                currentRowIndex = targetRow;
                AnimationManager.MoveDown(Niker, currentColumnIndex, currentRowIndex);//动画
                gameSystem6.moveoutNiker(currentColumnIndex, currentRowIndex-1);
                gameSystem6.moveinNiker(currentColumnIndex, currentRowIndex);
                AnimationManager.MoveDown(box3, currentColumnIndex, currentRowIndex+1);//动画
                gameSystem6.moveoutBox(currentColumnIndex, currentRowIndex);
                gameSystem6.moveinBox(3,currentColumnIndex, currentRowIndex+1);
                stepsUpdate();
            }
        }
        if (gameSystem6.isBox4(currentColumnIndex, targetRow)) {
            if (gameSystem6.notWall(currentColumnIndex, targetRow + 1) &&!gameSystem6.isBox2(currentColumnIndex, targetRow+1)&&!gameSystem6.isBox1(currentColumnIndex, targetRow+1)&&!gameSystem6.isBox3(currentColumnIndex, targetRow+1)&&!gameSystem6.isBox5(currentColumnIndex, targetRow+1)) {
                //可推
                moveAudio.playpushBox();
                moveAudio.playmoveSound();
                currentRowIndex = targetRow;
                AnimationManager.MoveDown(Niker, currentColumnIndex, currentRowIndex);//动画
                gameSystem6.moveoutNiker(currentColumnIndex, currentRowIndex-1);
                gameSystem6.moveinNiker(currentColumnIndex, currentRowIndex);
                AnimationManager.MoveDown(box4, currentColumnIndex, currentRowIndex+1);//动画
                gameSystem6.moveoutBox(currentColumnIndex, currentRowIndex);
                gameSystem6.moveinBox(4,currentColumnIndex, currentRowIndex+1);
                stepsUpdate();
            }
        }
        if (gameSystem6.isBox5(currentColumnIndex, targetRow)) {
            if (gameSystem6.notWall(currentColumnIndex, targetRow + 1) &&!gameSystem6.isBox2(currentColumnIndex, targetRow+1)&&!gameSystem6.isBox1(currentColumnIndex, targetRow+1)&&!gameSystem6.isBox4(currentColumnIndex, targetRow+1)&&!gameSystem6.isBox3(currentColumnIndex, targetRow+1)) {
                //可推
                moveAudio.playpushBox();
                moveAudio.playmoveSound();
                currentRowIndex = targetRow;
                AnimationManager.MoveDown(Niker, currentColumnIndex, currentRowIndex);//动画
                gameSystem6.moveoutNiker(currentColumnIndex, currentRowIndex-1);
                gameSystem6.moveinNiker(currentColumnIndex, currentRowIndex);
                AnimationManager.MoveDown(box5, currentColumnIndex, currentRowIndex+1);//动画
                gameSystem6.moveoutBox(currentColumnIndex, currentRowIndex);
                gameSystem6.moveinBox(5,currentColumnIndex, currentRowIndex+1);
                stepsUpdate();
            }
        }
        gameSystem6.victoryJudge();
        gameSystem6.failedJudge();
        if (gameSystem6.isGameOver()) {
            stopTimeline();
        }
    }

    @FXML
    void LeftBtnPressed() throws IOException {
        int targetColumn = currentColumnIndex - 1;
        //纯移动
        if (gameSystem6.notWall(targetColumn, currentRowIndex) &&!gameSystem6.isBox1(targetColumn, currentRowIndex)&&!gameSystem6.isBox2(targetColumn, currentRowIndex)&&!gameSystem6.isBox3(targetColumn, currentRowIndex)&&!gameSystem6.isBox4(targetColumn, currentRowIndex)&&!gameSystem6.isBox5(targetColumn, currentRowIndex)) {

            moveAudio.playmoveSound();
            currentColumnIndex = targetColumn;
            AnimationManager.MoveLeft(Niker, currentColumnIndex, currentRowIndex);//动画
            gameSystem6.moveoutNiker(currentColumnIndex+1, currentRowIndex);
            gameSystem6.moveinNiker(currentColumnIndex, currentRowIndex);
            stepsUpdate();
        }
        //推箱子
        if (gameSystem6.isBox1(targetColumn, currentRowIndex)) {
            if (gameSystem6.notWall(targetColumn - 1, currentRowIndex) &&!gameSystem6.isBox2(targetColumn-1, currentRowIndex)&&!gameSystem6.isBox3(targetColumn-1, currentRowIndex)&&!gameSystem6.isBox4(targetColumn-1, currentRowIndex)&&!gameSystem6.isBox5(targetColumn-1, currentRowIndex)) {
                moveAudio.playpushBox();
                moveAudio.playmoveSound();
                currentColumnIndex = targetColumn;
                AnimationManager.MoveLeft(Niker, currentColumnIndex, currentRowIndex);//动画
                gameSystem6.moveoutNiker(currentColumnIndex+1, currentRowIndex);
                gameSystem6.moveinNiker(currentColumnIndex, currentRowIndex);
                AnimationManager.MoveLeft(box1, currentColumnIndex-1, currentRowIndex);//动画
                gameSystem6.moveoutBox(currentColumnIndex, currentRowIndex);
                gameSystem6.moveinBox(1,currentColumnIndex-1, currentRowIndex);
                stepsUpdate();
            }
        }
        if (gameSystem6.isBox2(targetColumn, currentRowIndex)) {
            if (gameSystem6.notWall(targetColumn - 1, currentRowIndex) &&!gameSystem6.isBox1(targetColumn-1, currentRowIndex)&&!gameSystem6.isBox3(targetColumn-1, currentRowIndex)&&!gameSystem6.isBox4(targetColumn-1, currentRowIndex)&&!gameSystem6.isBox5(targetColumn-1, currentRowIndex)) {
                moveAudio.playpushBox();
                moveAudio.playmoveSound();
                currentColumnIndex = targetColumn;
                AnimationManager.MoveLeft(Niker, currentColumnIndex, currentRowIndex);//动画
                gameSystem6.moveoutNiker(currentColumnIndex+1, currentRowIndex);
                gameSystem6.moveinNiker(currentColumnIndex, currentRowIndex);
                AnimationManager.MoveLeft(box2, currentColumnIndex-1, currentRowIndex);//动画
                gameSystem6.moveoutBox(currentColumnIndex, currentRowIndex);
                gameSystem6.moveinBox(2,currentColumnIndex-1, currentRowIndex);
                stepsUpdate();
            }
        }
        if (gameSystem6.isBox3(targetColumn, currentRowIndex)) {
            if (gameSystem6.notWall(targetColumn - 1, currentRowIndex) &&!gameSystem6.isBox1(targetColumn-1, currentRowIndex)&&!gameSystem6.isBox2(targetColumn-1, currentRowIndex)&&!gameSystem6.isBox4(targetColumn-1, currentRowIndex)&&!gameSystem6.isBox5(targetColumn-1, currentRowIndex)) {
                moveAudio.playpushBox();
                moveAudio.playmoveSound();
                currentColumnIndex = targetColumn;
                AnimationManager.MoveLeft(Niker, currentColumnIndex, currentRowIndex);//动画
                gameSystem6.moveoutNiker(currentColumnIndex+1, currentRowIndex);
                gameSystem6.moveinNiker(currentColumnIndex, currentRowIndex);
                AnimationManager.MoveLeft(box3, currentColumnIndex-1, currentRowIndex);//动画
                gameSystem6.moveoutBox(currentColumnIndex, currentRowIndex);
                gameSystem6.moveinBox(3,currentColumnIndex-1, currentRowIndex);
                stepsUpdate();
            }
        }
        if (gameSystem6.isBox4(targetColumn, currentRowIndex)) {
            if (gameSystem6.notWall(targetColumn - 1, currentRowIndex) &&!gameSystem6.isBox1(targetColumn-1, currentRowIndex)&&!gameSystem6.isBox2(targetColumn-1, currentRowIndex)&&!gameSystem6.isBox3(targetColumn-1, currentRowIndex)&&!gameSystem6.isBox5(targetColumn-1, currentRowIndex)) {
                moveAudio.playpushBox();
                moveAudio.playmoveSound();
                currentColumnIndex = targetColumn;
                AnimationManager.MoveLeft(Niker, currentColumnIndex, currentRowIndex);//动画
                gameSystem6.moveoutNiker(currentColumnIndex+1, currentRowIndex);
                gameSystem6.moveinNiker(currentColumnIndex, currentRowIndex);
                AnimationManager.MoveLeft(box4, currentColumnIndex-1, currentRowIndex);//动画
                gameSystem6.moveoutBox(currentColumnIndex, currentRowIndex);
                gameSystem6.moveinBox(4,currentColumnIndex-1, currentRowIndex);
                stepsUpdate();
            }
        }
        if (gameSystem6.isBox5(targetColumn, currentRowIndex)) {
            if (gameSystem6.notWall(targetColumn - 1, currentRowIndex) &&!gameSystem6.isBox1(targetColumn-1, currentRowIndex)&&!gameSystem6.isBox2(targetColumn-1, currentRowIndex)&&!gameSystem6.isBox4(targetColumn-1, currentRowIndex)&&!gameSystem6.isBox3(targetColumn-1, currentRowIndex)) {
                moveAudio.playpushBox();
                moveAudio.playmoveSound();
                currentColumnIndex = targetColumn;
                AnimationManager.MoveLeft(Niker, currentColumnIndex, currentRowIndex);//动画
                gameSystem6.moveoutNiker(currentColumnIndex+1, currentRowIndex);
                gameSystem6.moveinNiker(currentColumnIndex, currentRowIndex);
                AnimationManager.MoveLeft(box5, currentColumnIndex-1, currentRowIndex);//动画
                gameSystem6.moveoutBox(currentColumnIndex, currentRowIndex);
                gameSystem6.moveinBox(5,currentColumnIndex-1, currentRowIndex);
                stepsUpdate();
            }
        }
        gameSystem6.victoryJudge();
        gameSystem6.failedJudge();
        if (gameSystem6.isGameOver()) {
            stopTimeline();
        }
    }

    @FXML
    void RightBtnPressed() throws IOException {
        int targetColumn = currentColumnIndex + 1;
        //纯移动
        if (gameSystem6.notWall(targetColumn, currentRowIndex) &&!gameSystem6.isBox1(targetColumn, currentRowIndex)&&!gameSystem6.isBox2(targetColumn, currentRowIndex)&&!gameSystem6.isBox3(targetColumn, currentRowIndex)&&!gameSystem6.isBox4(targetColumn, currentRowIndex)&&!gameSystem6.isBox5(targetColumn, currentRowIndex)) {

            moveAudio.playmoveSound();
            currentColumnIndex = targetColumn;
            AnimationManager.MoveRight(Niker, currentColumnIndex, currentRowIndex);//动画
            gameSystem6.moveoutNiker(currentColumnIndex-1, currentRowIndex);
            gameSystem6.moveinNiker(currentColumnIndex, currentRowIndex);
            stepsUpdate();
        }
        //推箱子
        if (gameSystem6.isBox1(targetColumn, currentRowIndex)) {
            if (gameSystem6.notWall(targetColumn + 1, currentRowIndex) &&!gameSystem6.isBox2(targetColumn+1, currentRowIndex)&&!gameSystem6.isBox3(targetColumn+1, currentRowIndex)&&!gameSystem6.isBox4(targetColumn+1, currentRowIndex)&&!gameSystem6.isBox5(targetColumn+1, currentRowIndex)) {
                moveAudio.playpushBox();
                moveAudio.playmoveSound();
                currentColumnIndex = targetColumn;
                AnimationManager.MoveRight(Niker, currentColumnIndex, currentRowIndex);//动画
                gameSystem6.moveoutNiker(currentColumnIndex-1, currentRowIndex);
                gameSystem6.moveinNiker(currentColumnIndex, currentRowIndex);
                AnimationManager.MoveRight(box1, currentColumnIndex+1, currentRowIndex);//动画
                gameSystem6.moveoutBox(currentColumnIndex, currentRowIndex);
                gameSystem6.moveinBox(1,currentColumnIndex+1, currentRowIndex);
                stepsUpdate();
            }
        }
        if (gameSystem6.isBox2(targetColumn, currentRowIndex)) {
            if (gameSystem6.notWall(targetColumn + 1, currentRowIndex) &&!gameSystem6.isBox1(targetColumn+1, currentRowIndex)&&!gameSystem6.isBox3(targetColumn+1, currentRowIndex)&&!gameSystem6.isBox4(targetColumn+1, currentRowIndex)&&!gameSystem6.isBox5(targetColumn+1, currentRowIndex)) {
                moveAudio.playpushBox();
                moveAudio.playmoveSound();
                currentColumnIndex = targetColumn;
                AnimationManager.MoveRight(Niker, currentColumnIndex, currentRowIndex);//动画
                gameSystem6.moveoutNiker(currentColumnIndex-1, currentRowIndex);
                gameSystem6.moveinNiker(currentColumnIndex, currentRowIndex);
                AnimationManager.MoveRight(box2, currentColumnIndex+1, currentRowIndex);//动画
                gameSystem6.moveoutBox(currentColumnIndex, currentRowIndex);
                gameSystem6.moveinBox(2,currentColumnIndex+1, currentRowIndex);
                stepsUpdate();
            }
        }
        if (gameSystem6.isBox3(targetColumn, currentRowIndex)) {
            if (gameSystem6.notWall(targetColumn + 1, currentRowIndex) &&!gameSystem6.isBox1(targetColumn+1, currentRowIndex)&&!gameSystem6.isBox2(targetColumn+1, currentRowIndex)&&!gameSystem6.isBox4(targetColumn+1, currentRowIndex)&&!gameSystem6.isBox5(targetColumn+1, currentRowIndex)) {
                moveAudio.playpushBox();
                moveAudio.playmoveSound();
                currentColumnIndex = targetColumn;
                AnimationManager.MoveRight(Niker, currentColumnIndex, currentRowIndex);//动画
                gameSystem6.moveoutNiker(currentColumnIndex-1, currentRowIndex);
                gameSystem6.moveinNiker(currentColumnIndex, currentRowIndex);
                AnimationManager.MoveRight(box3, currentColumnIndex+1, currentRowIndex);//动画
                gameSystem6.moveoutBox(currentColumnIndex, currentRowIndex);
                gameSystem6.moveinBox(3,currentColumnIndex+1, currentRowIndex);
                stepsUpdate();
            }
        }
        if (gameSystem6.isBox4(targetColumn, currentRowIndex)) {
            if (gameSystem6.notWall(targetColumn + 1, currentRowIndex) &&!gameSystem6.isBox1(targetColumn+1, currentRowIndex)&&!gameSystem6.isBox2(targetColumn+1, currentRowIndex)&&!gameSystem6.isBox3(targetColumn+1, currentRowIndex)&&!gameSystem6.isBox5(targetColumn+1, currentRowIndex)) {
                moveAudio.playpushBox();
                moveAudio.playmoveSound();
                currentColumnIndex = targetColumn;
                AnimationManager.MoveRight(Niker, currentColumnIndex, currentRowIndex);//动画
                gameSystem6.moveoutNiker(currentColumnIndex-1, currentRowIndex);
                gameSystem6.moveinNiker(currentColumnIndex, currentRowIndex);
                AnimationManager.MoveRight(box4, currentColumnIndex+1, currentRowIndex);//动画
                gameSystem6.moveoutBox(currentColumnIndex, currentRowIndex);
                gameSystem6.moveinBox(4,currentColumnIndex+1, currentRowIndex);
                stepsUpdate();
            }
        }
        if (gameSystem6.isBox5(targetColumn, currentRowIndex)) {
            if (gameSystem6.notWall(targetColumn + 1, currentRowIndex) &&!gameSystem6.isBox1(targetColumn+1, currentRowIndex)&&!gameSystem6.isBox2(targetColumn+1, currentRowIndex)&&!gameSystem6.isBox4(targetColumn+1, currentRowIndex)&&!gameSystem6.isBox3(targetColumn+1, currentRowIndex)) {
                moveAudio.playpushBox();
                moveAudio.playmoveSound();
                currentColumnIndex = targetColumn;
                AnimationManager.MoveRight(Niker, currentColumnIndex, currentRowIndex);//动画
                gameSystem6.moveoutNiker(currentColumnIndex-1, currentRowIndex);
                gameSystem6.moveinNiker(currentColumnIndex, currentRowIndex);
                AnimationManager.MoveRight(box5, currentColumnIndex+1, currentRowIndex);//动画
                gameSystem6.moveoutBox(currentColumnIndex, currentRowIndex);
                gameSystem6.moveinBox(5,currentColumnIndex+1, currentRowIndex);
                stepsUpdate();
            }
        }
        gameSystem6.victoryJudge();
        gameSystem6.failedJudge();
        if (gameSystem6.isGameOver()) {
            stopTimeline();
        }
    }

    @FXML
    void UpBtnPressed() throws IOException {
        int targetRow = currentRowIndex - 1;
        //纯移动
        if (gameSystem6.notWall(currentColumnIndex, targetRow) &&!gameSystem6.isBox1(currentColumnIndex, targetRow)&&!gameSystem6.isBox2(currentColumnIndex, targetRow)&&!gameSystem6.isBox3(currentColumnIndex, targetRow)&&!gameSystem6.isBox4(currentColumnIndex, targetRow)&&!gameSystem6.isBox5(currentColumnIndex, targetRow)) {

            moveAudio.playmoveSound();
            currentRowIndex = targetRow;
            AnimationManager.MoveUp(Niker, currentColumnIndex, currentRowIndex);//动画
            gameSystem6.moveoutNiker(currentColumnIndex, currentRowIndex+1);
            gameSystem6.moveinNiker(currentColumnIndex, currentRowIndex);
            stepsUpdate();
        }
        //推箱子
        if (gameSystem6.isBox1(currentColumnIndex, targetRow)) {
            if (gameSystem6.notWall(currentColumnIndex, targetRow - 1) &&!gameSystem6.isBox2(currentColumnIndex, targetRow-1)&&!gameSystem6.isBox3(currentColumnIndex, targetRow-1)&&!gameSystem6.isBox4(currentColumnIndex, targetRow-1)&&!gameSystem6.isBox5(currentColumnIndex, targetRow-1)) {
                //可推
                moveAudio.playpushBox();
                moveAudio.playmoveSound();
                currentRowIndex = targetRow;
                AnimationManager.MoveUp(Niker, currentColumnIndex, currentRowIndex);//动画
                gameSystem6.moveoutNiker(currentColumnIndex, currentRowIndex+1);
                gameSystem6.moveinNiker(currentColumnIndex, currentRowIndex);
                AnimationManager.MoveUp(box1, currentColumnIndex, currentRowIndex-1);//动画
                gameSystem6.moveoutBox(currentColumnIndex, currentRowIndex);
                gameSystem6.moveinBox(1,currentColumnIndex, currentRowIndex-1);
                stepsUpdate();
            }
        }
        if (gameSystem6.isBox2(currentColumnIndex, targetRow)) {
            if (gameSystem6.notWall(currentColumnIndex, targetRow - 1) &&!gameSystem6.isBox1(currentColumnIndex, targetRow-1)&&!gameSystem6.isBox3(currentColumnIndex, targetRow-1)&&!gameSystem6.isBox4(currentColumnIndex, targetRow-1)&&!gameSystem6.isBox5(currentColumnIndex, targetRow-1)) {
                //可推
                moveAudio.playpushBox();
                moveAudio.playmoveSound();
                currentRowIndex = targetRow;
                AnimationManager.MoveUp(Niker, currentColumnIndex, currentRowIndex);//动画
                gameSystem6.moveoutNiker(currentColumnIndex, currentRowIndex+1);
                gameSystem6.moveinNiker(currentColumnIndex, currentRowIndex);
                AnimationManager.MoveUp(box2, currentColumnIndex, currentRowIndex-1);//动画
                gameSystem6.moveoutBox(currentColumnIndex, currentRowIndex);
                gameSystem6.moveinBox(2,currentColumnIndex, currentRowIndex-1);
                stepsUpdate();
            }
        }
        if (gameSystem6.isBox3(currentColumnIndex, targetRow)) {
            if (gameSystem6.notWall(currentColumnIndex, targetRow - 1) &&!gameSystem6.isBox2(currentColumnIndex, targetRow-1)&&!gameSystem6.isBox1(currentColumnIndex, targetRow-1)&&!gameSystem6.isBox4(currentColumnIndex, targetRow-1)&&!gameSystem6.isBox5(currentColumnIndex, targetRow-1)) {
                //可推
                moveAudio.playpushBox();
                moveAudio.playmoveSound();
                currentRowIndex = targetRow;
                AnimationManager.MoveUp(Niker, currentColumnIndex, currentRowIndex);//动画
                gameSystem6.moveoutNiker(currentColumnIndex, currentRowIndex+1);
                gameSystem6.moveinNiker(currentColumnIndex, currentRowIndex);
                AnimationManager.MoveUp(box3, currentColumnIndex, currentRowIndex-1);//动画
                gameSystem6.moveoutBox(currentColumnIndex, currentRowIndex);
                gameSystem6.moveinBox(3,currentColumnIndex, currentRowIndex-1);
                stepsUpdate();
            }
        }
        if (gameSystem6.isBox4(currentColumnIndex, targetRow)) {
            if (gameSystem6.notWall(currentColumnIndex, targetRow - 1) &&!gameSystem6.isBox2(currentColumnIndex, targetRow-1)&&!gameSystem6.isBox1(currentColumnIndex, targetRow-1)&&!gameSystem6.isBox3(currentColumnIndex, targetRow-1)&&!gameSystem6.isBox5(currentColumnIndex, targetRow-1)) {
                //可推
                moveAudio.playpushBox();
                moveAudio.playmoveSound();
                currentRowIndex = targetRow;
                AnimationManager.MoveUp(Niker, currentColumnIndex, currentRowIndex);//动画
                gameSystem6.moveoutNiker(currentColumnIndex, currentRowIndex+1);
                gameSystem6.moveinNiker(currentColumnIndex, currentRowIndex);
                AnimationManager.MoveUp(box4, currentColumnIndex, currentRowIndex-1);//动画
                gameSystem6.moveoutBox(currentColumnIndex, currentRowIndex);
                gameSystem6.moveinBox(4,currentColumnIndex, currentRowIndex-1);
                stepsUpdate();
            }
        }
        if (gameSystem6.isBox5(currentColumnIndex, targetRow)) {
            if (gameSystem6.notWall(currentColumnIndex, targetRow - 1) &&!gameSystem6.isBox2(currentColumnIndex, targetRow-1)&&!gameSystem6.isBox1(currentColumnIndex, targetRow-1)&&!gameSystem6.isBox4(currentColumnIndex, targetRow-1)&&!gameSystem6.isBox3(currentColumnIndex, targetRow-1)) {
                //可推
                moveAudio.playpushBox();
                moveAudio.playmoveSound();
                currentRowIndex = targetRow;
                AnimationManager.MoveUp(Niker, currentColumnIndex, currentRowIndex);//动画
                gameSystem6.moveoutNiker(currentColumnIndex, currentRowIndex+1);
                gameSystem6.moveinNiker(currentColumnIndex, currentRowIndex);
                AnimationManager.MoveUp(box5, currentColumnIndex, currentRowIndex-1);//动画
                gameSystem6.moveoutBox(currentColumnIndex, currentRowIndex);
                gameSystem6.moveinBox(5,currentColumnIndex, currentRowIndex-1);
                stepsUpdate();
            }
        }
        gameSystem6.victoryJudge();
        gameSystem6.failedJudge();
        if (gameSystem6.isGameOver()) {
            stopTimeline();
        }
    }

}