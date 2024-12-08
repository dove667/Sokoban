package Sokoban.Controller;

import Sokoban.Model.GameSystem;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;

public class LevelSceneController {
    @FXML
    private Button Btn_Level1, Btn_Level2, Btn_Level3,Btn_Level4, Btn_Level5, Btn_TimeMode;

    @FXML
    private Label Label_Mode;

    @FXML
    private AnchorPane Pane,Bottom;

    @FXML
    private ImageView Img_SUST2;

    public void initialize() {
        if(GameSystem.isTimeMode()) {
            Label_Mode.setText("open");
        }
        else {
            Label_Mode.setText("closed");
        }
       /* if(!GameSystem.isL1win()){
            Btn_Level2.setDisable(true); Btn_Level2.setVisible(false);
            Btn_Level3.setDisable(true);Btn_Level3.setVisible(false);
            Btn_Level4.setDisable(true);Btn_Level4.setVisible(false);
            Btn_Level5.setDisable(true);Btn_Level5.setVisible(false);
        }
        else if(!GameSystem.isL2win()){
            Btn_Level3.setDisable(true);Btn_Level3.setVisible(false);
            Btn_Level4.setDisable(true);Btn_Level4.setVisible(false);
            Btn_Level5.setDisable(true);Btn_Level5.setVisible(false);
        }
        else if(!GameSystem.isL3win()){
            Btn_Level4.setDisable(true);Btn_Level4.setVisible(false);
            Btn_Level5.setDisable(true);Btn_Level5.setVisible(false);
        }
        else if(!GameSystem.isL4win()){
            Btn_Level5.setDisable(true);Btn_Level5.setVisible(false);
        }*/
        Btn_TimeMode.setFont(Font.font("Century", 20));
        Label_Mode.setFont(Font.font("Century", FontWeight.BOLD, 20));
        Btn_TimeMode.setBackground(new Background(new BackgroundFill(Color.GREEN, cornerRadii, Insets.EMPTY)));
        Btn_TimeMode.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, cornerRadii, BorderWidths.DEFAULT)));
    }

    CornerRadii cornerRadii = new CornerRadii(50,50,50,50, false); // false 表示不使用默认的圆角

    @FXML
    void Level1BtnReleased() throws IOException {

        Stage primaryStage = (Stage) Btn_Level1.getScene().getWindow();
        URL url = getClass().getResource("/Sokoban/Level1.fxml");
        Parent root = FXMLLoader.load(Objects.requireNonNull(url));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        GameSystem.setCurrentLevel(1);
        GameSystem.setCurrentLevel("Level1");
        GameSystem.setTimeMode(setMode());
    }

    @FXML
    void Level2BtnReleased() throws IOException {

        Stage primaryStage = (Stage) Btn_Level2.getScene().getWindow();
        URL url = getClass().getResource("/Sokoban/Level2.fxml");
        Parent root = FXMLLoader.load(Objects.requireNonNull(url));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        GameSystem.setCurrentLevel(2);
        GameSystem.setCurrentLevel("Level2");
        GameSystem.setTimeMode(setMode());
    }

    @FXML
    void Level3BtnReleased() throws IOException {

        Stage primaryStage = (Stage) Btn_Level3.getScene().getWindow();
        URL url = getClass().getResource("/Sokoban/Level3.fxml");
        Parent root = FXMLLoader.load(Objects.requireNonNull(url));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        GameSystem.setCurrentLevel(3);
        GameSystem.setCurrentLevel("Level3");
        GameSystem.setTimeMode(setMode());
    }
    @FXML
    void Level4BtnReleased() throws IOException {

        Stage primaryStage = (Stage) Btn_Level4.getScene().getWindow();
        URL url = getClass().getResource("/Sokoban/Level4.fxml");
        Parent root = FXMLLoader.load(Objects.requireNonNull(url));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        GameSystem.setCurrentLevel(4);
        GameSystem.setCurrentLevel("Level4");
        GameSystem.setTimeMode(setMode());
    }

    @FXML
    void Level5BtnReleased() throws IOException {

        Stage primaryStage = (Stage) Btn_Level5.getScene().getWindow();
        URL url = getClass().getResource("/Sokoban/Level5.fxml");
        Parent root = FXMLLoader.load(Objects.requireNonNull(url));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        GameSystem.setCurrentLevel(5);
        GameSystem.setCurrentLevel("Level5");
        GameSystem.setTimeMode(setMode());
    }

    @FXML
    void TimeModeBtnReleased() {
         if(Label_Mode.getText().equals("closed")) {
             Label_Mode.setText("open");
             GameSystem.setTimeMode(true);
             Btn_TimeMode.setBackground(new Background(new BackgroundFill(Color.RED, cornerRadii, Insets.EMPTY)));
         }
         else {
             Label_Mode.setText("closed");
             GameSystem.setTimeMode(false);
             Btn_TimeMode.setBackground(new Background(new BackgroundFill(Color.GREEN, cornerRadii, Insets.EMPTY)));
         }
    }


    public boolean setMode(){
        if(Label_Mode.getText().equals("open")) {
            return true;
        }
        else {
            return false;
        }
    }
}
