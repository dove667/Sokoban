package Sokoban.Controller;

import Sokoban.Model.GameSystem;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;

import static Sokoban.Login_Application.primaryStage;

public class FailedController {

    @FXML
    private Button Btn_TryAgain,Btn_Home;

    @FXML
    private Label Label_Failed;


    @FXML
    void HomeBtnReleased(MouseEvent event) throws IOException {
      if(GameSystem.verifyVisitor()){
            URL url = getClass().getResource("/Sokoban/LoginScene.fxml");
            Parent root = FXMLLoader.load(Objects.requireNonNull(url));
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
      }
      else {
          URL url = getClass().getResource("/Sokoban/LevelScene.fxml");
          Parent root = FXMLLoader.load(Objects.requireNonNull(url));
          Scene scene = new Scene(root);
          primaryStage.setScene(scene);
      }
    }

    @FXML
    void TryAgainBtnReleased(MouseEvent event) throws IOException {
         if(GameSystem.getCurrentLevel()==1){
             URL url = getClass().getResource("/Sokoban/Level1.fxml");
             Parent root = FXMLLoader.load(Objects.requireNonNull(url));
             Scene scene = new Scene(root);
             primaryStage.setScene(scene);
         }
         else if(GameSystem.getCurrentLevel()==2){
             URL url = getClass().getResource("/Sokoban/Level2.fxml");
             Parent root = FXMLLoader.load(Objects.requireNonNull(url));
             Scene scene = new Scene(root);
             primaryStage.setScene(scene);
         }
         else if(GameSystem.getCurrentLevel()==3){
             URL url = getClass().getResource("/Sokoban/Level3.fxml");
             Parent root = FXMLLoader.load(Objects.requireNonNull(url));
             Scene scene = new Scene(root);
             primaryStage.setScene(scene);
         }
         else if(GameSystem.getCurrentLevel()==4){
             URL url = getClass().getResource("/Sokoban/Level4.fxml");
             Parent root = FXMLLoader.load(Objects.requireNonNull(url));
             Scene scene = new Scene(root);
             primaryStage.setScene(scene);
         }
         else if(GameSystem.getCurrentLevel()==5){
             URL url = getClass().getResource("/Sokoban/Level5.fxml");
             Parent root = FXMLLoader.load(Objects.requireNonNull(url));
             Scene scene = new Scene(root);
             primaryStage.setScene(scene);
         }


    }

}
