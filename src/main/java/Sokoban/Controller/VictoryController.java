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


public class VictoryController {

    @FXML
    private Button Btn_Home;

    @FXML
    private Button Btn_NextLevel;

    @FXML
    private Label Label_Victory;

    public void initialize() {
        Account account = Account.loadAccount();
        if (account.verifyVisitor()){
            Btn_Home.setDisable(true);
            Btn_NextLevel.setDisable(true);
            Btn_NextLevel.setVisible(false);
            Btn_Home.setVisible(false);
        }
        account.getNextLevel();//更新L win 信息
    }

    @FXML
    void HomeBtnReleased(MouseEvent event) throws IOException {
        Stage primaryStage = (Stage) Btn_Home.getScene().getWindow();
        URL url = getClass().getResource("/Sokoban/LevelScene.fxml");
        Parent root = FXMLLoader.load(Objects.requireNonNull(url));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);

    }

    @FXML
    void NextLevelBtnReleased(MouseEvent event) throws IOException {
        Account account = Account.loadAccount();
        String nextLevelPath = account.getNextLevel();
        if (nextLevelPath != null) {
            URL url = getClass().getResource(nextLevelPath);
            Parent root = FXMLLoader.load(Objects.requireNonNull(url));
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            // 更新当前关卡信息
            if(account.getCurrentLevel() ==1){
                account.setL1win(true);
                account.setCurrentLevel(2);
            }
            else if(account.getCurrentLevel() ==2){
                account.setL2win(true);
                account.setCurrentLevel(3);
            }
            else if(account.getCurrentLevel() ==3){
                account.setL3win(true);
                account.setCurrentLevel(4);
            }
            else if(account.getCurrentLevel() ==4){
                account.setL4win(true);
                account.setCurrentLevel(5);
            }
            Account.saveAccount(account);
        }
        else {
            System.out.println("没有更多的关卡可加载");
        }
    }


}
