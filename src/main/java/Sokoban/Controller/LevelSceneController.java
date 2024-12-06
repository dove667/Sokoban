package Sokoban.Controller;

import Sokoban.Model.GameSystem;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;

public class LevelSceneController {
    @FXML
    private Button Btn_Level1;

    @FXML
    private Button Btn_Level2;

    @FXML
    private Button Btn_Level3;

    @FXML
    private Button Btn_Level4;

    @FXML
    private Button Btn_Level5;

    @FXML
    private Button Btn_TimeMode;

    @FXML
    private Label Label_Mode;

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
         }
         else {
             Label_Mode.setText("closed");
             GameSystem.setTimeMode(false);
         }
    }

    public void initialize() {
        if(GameSystem.isTimeMode()) {
            Label_Mode.setText("open");
        }
        else {
            Label_Mode.setText("closed");
        }
        if(!GameSystem.isL1win()){
            Btn_Level2.setDisable(true); Btn_Level2.setVisible(false);

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
