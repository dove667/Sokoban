package Sokoban.Controller;

import Sokoban.Model.GameSystem;
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

    public void initialize() {
        if (GameSystem.verifyVisitor()){

            Btn_NextLevel.setDisable(true);
            Btn_NextLevel.setVisible(false);

        }
        getNextLevel();
    }

    @FXML
    void HomeBtnReleased(MouseEvent event) throws IOException {
        Stage primaryStage = (Stage) Btn_Home.getScene().getWindow();
        if (GameSystem.verifyVisitor()){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Congratulations");
            alert.setHeaderText("You have completed the game!");
            alert.setContentText("if you want to play more levels, please register and login ");
            alert.showAndWait();
            URL url = getClass().getResource("/Sokoban/LoginScene.fxml");
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
        else{
            URL url = getClass().getResource("/Sokoban/LevelScene.fxml");
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
            System.out.println("没有更多的关卡可加载");
        }
    }


}
