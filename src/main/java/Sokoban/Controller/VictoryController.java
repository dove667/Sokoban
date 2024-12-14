package Sokoban.Controller;

import Sokoban.Model.GameSystem;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;

import static Sokoban.Login_Application.primaryStage;
import static Sokoban.Model.GameSystem.getNextLevel;
import static Sokoban.Model.GameSystem.setCurrentLevel;

public class VictoryController {

    @FXML
    private Button Btn_Home;

    @FXML
    private Button Btn_NextLevel;

    @FXML
    private Label Label_Victory;

    AudioManager win = new AudioManager();

    public void initialize() {
        if (GameSystem.verifyVisitor()) {
            Btn_NextLevel.setDisable(true);
            Btn_NextLevel.setVisible(false);
        }
        getNextLevel();//更新Lnwin状态
        Platform.runLater(() -> {
            win.playWin();
        });
    }

    @FXML
    void HomeBtnReleased(MouseEvent event) throws IOException {
        Stage primaryStage = (Stage) Btn_Home.getScene().getWindow();
        if (GameSystem.verifyVisitor()) {
            AudioManager.playbackgroundPeace();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Congratulations");
            alert.setHeaderText("You have completed the game!");
            alert.setContentText("if you want to play more levels, please register and login ");
            alert.showAndWait();
            URL url = getClass().getResource("/Sokoban/Fxml/LoginScene.fxml");
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
            AudioManager.playbackgroundPeace();
            URL url = getClass().getResource("/Sokoban/Fxml/LevelScene.fxml");
            Parent root = FXMLLoader.load(Objects.requireNonNull(url));
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
        }
    }

    @FXML
    void NextLevelBtnReleased(MouseEvent event) throws IOException {
        String nextLevelPath = getNextLevel();
        if (nextLevelPath != null) {
            URL url = getClass().getResource(nextLevelPath);
            Parent root = FXMLLoader.load(Objects.requireNonNull(url));
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            // 更新当前关卡信息
            setCurrentLevel(nextLevelPath);
        } else {
            System.out.println("No more levels");
        }
    }


}
