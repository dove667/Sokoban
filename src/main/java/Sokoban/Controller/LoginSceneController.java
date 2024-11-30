package Sokoban.Controller;

import Sokoban.Model.GameSystem;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;


public class LoginSceneController {

    @FXML
    private Button Btn_login;

    @FXML
    private Button Btn_visitor;

    @FXML
    private PasswordField Input_passwd;

    @FXML
    private TextField Input_username;

    @FXML
    private Label Label_passwd;

    @FXML
    private Label Label_username;



    @FXML
    void LoginBtnReleased() throws IOException {
        String username = Input_username.getText();
        String passwd = Input_passwd.getText();
        if (username.equals("admin") && passwd.equals("admin")) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("提示");
            alert.setHeaderText("登录成功");
            alert.setContentText("登陆成功");
            alert.showAndWait();
            //切换场景
            Stage primaryStage = (Stage) Btn_login.getScene().getWindow();
            URL url = getClass().getResource("/Sokoban/LevelScene.fxml");
            //加载完fxml文件后，获取其中的root
            Parent root = FXMLLoader.load(Objects.requireNonNull(url));
            //设置场景
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("提示");
            alert.setHeaderText("登录失败");
            alert.setContentText("用户名或密码错误");
            alert.showAndWait();
        }
    }

    @FXML
    void VisitorBtnReleased() throws IOException {
        GameSystem.setIsVisitor(true);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("提示");
        alert.setHeaderText("游客模式");
        alert.setContentText("游客模式下的游戏进度不会被保存！");
        alert.showAndWait();
        //切换场景
        Stage primaryStage = (Stage) Btn_visitor.getScene().getWindow();
        GameSystem gameSystem = new GameSystem(2,2,18,6,5);
        URL url = getClass().getResource("/Sokoban/Level1.fxml");
        //加载完fxml文件后，获取其中的root
        Parent root = FXMLLoader.load(Objects.requireNonNull(url));
        //设置场景
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);

    }


}
