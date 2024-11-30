package Sokoban.Controller;

import Sokoban.Model.GameSystem;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;

public class FailedController {

    @FXML
    private Button Btn_TryAgain,Btn_Home;

    @FXML
    private Label Label_Failed;

    @FXML
    void HomeBtnReleased(MouseEvent event) throws IOException {
        Stage primaryStage = (Stage) Btn_Home.getScene().getWindow();
        URL url = getClass().getResource("/Sokoban/LevelScene.fxml");
        Parent root = FXMLLoader.load(Objects.requireNonNull(url));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
    }

    @FXML
    void TryAgainBtnReleased(MouseEvent event) throws IOException {
         if(GameSystem.getCurrentLevel()==1){
             Level1Controller level1Controller = new Level1Controller();
             level1Controller.BackBtnPressed();
         }
    }  //补充其他level的try again按钮事件

}
