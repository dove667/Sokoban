package Sokoban.Controller;

import Sokoban.Model.Account;
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

import static Sokoban.Login_Application.primaryStage;

public class FailedController {

    @FXML
    private Button Btn_TryAgain,Btn_Home;

    @FXML
    private Label Label_Failed;

    public void initialize() {
        Account account = Account.loadAccount();
        if (account.verifyVisitor()){
            Btn_Home.setDisable(true);
            Btn_Home.setVisible(false);
        }
    }
    @FXML
    void HomeBtnReleased(MouseEvent event) throws IOException {
        URL url = getClass().getResource("/Sokoban/LevelScene.fxml");
        Parent root = FXMLLoader.load(Objects.requireNonNull(url));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
    }

    @FXML
    void TryAgainBtnReleased(MouseEvent event) throws IOException {
        Account account = Account.loadAccount();
         if(account.getCurrentLevel()==1){
             URL url = getClass().getResource("/Sokoban/Level1.fxml");
             Parent root = FXMLLoader.load(Objects.requireNonNull(url));
             Scene scene = new Scene(root);
             primaryStage.setScene(scene);
         }
         else if(account.getCurrentLevel()==2){
             URL url = getClass().getResource("/Sokoban/Level2.fxml");
             Parent root = FXMLLoader.load(Objects.requireNonNull(url));
             Scene scene = new Scene(root);
             primaryStage.setScene(scene);
         }
         else if(account.getCurrentLevel()==3){
             URL url = getClass().getResource("/Sokoban/Level3.fxml");
             Parent root = FXMLLoader.load(Objects.requireNonNull(url));
             Scene scene = new Scene(root);
             primaryStage.setScene(scene);
         }
         else if(account.getCurrentLevel()==4){
             URL url = getClass().getResource("/Sokoban/Level4.fxml");
             Parent root = FXMLLoader.load(Objects.requireNonNull(url));
             Scene scene = new Scene(root);
             primaryStage.setScene(scene);
         }
         else if(account.getCurrentLevel()==5){
             URL url = getClass().getResource("/Sokoban/Level5.fxml");
             Parent root = FXMLLoader.load(Objects.requireNonNull(url));
             Scene scene = new Scene(root);
             primaryStage.setScene(scene);
         }


    }

}
