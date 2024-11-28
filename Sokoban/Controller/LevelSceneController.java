package Sokoban.Controller;

import Sokoban.Model.GameSystem;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
    void Level1BtnReleased() throws IOException {

        Stage primaryStage = (Stage) Btn_Level1.getScene().getWindow();
        URL url = getClass().getResource("/Sokoban/Level1.fxml");
        Parent root = FXMLLoader.load(Objects.requireNonNull(url));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);

    }

    @FXML
    void Level2BtnReleased() throws IOException {

        Stage primaryStage = (Stage) Btn_Level2.getScene().getWindow();
        URL url = getClass().getResource("/Sokoban/Level2.fxml");
        Parent root = FXMLLoader.load(Objects.requireNonNull(url));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
    }

    @FXML
    void Level3BtnReleased() throws IOException {

        Stage primaryStage = (Stage) Btn_Level3.getScene().getWindow();
        URL url = getClass().getResource("/Sokoban/Level3.fxml");
        Parent root = FXMLLoader.load(Objects.requireNonNull(url));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
    }
    @FXML
    void Level4BtnReleased() throws IOException {

        Stage primaryStage = (Stage) Btn_Level2.getScene().getWindow();
        URL url = getClass().getResource("/Sokoban/Level4.fxml");
        Parent root = FXMLLoader.load(Objects.requireNonNull(url));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
    }

    @FXML
    void Level5BtnReleased() throws IOException {

        Stage primaryStage = (Stage) Btn_Level2.getScene().getWindow();
        URL url = getClass().getResource("/Sokoban/Level5.fxml");
        Parent root = FXMLLoader.load(Objects.requireNonNull(url));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
    }

}
