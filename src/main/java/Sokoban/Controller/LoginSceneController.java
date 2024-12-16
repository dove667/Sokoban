package Sokoban.Controller;

import Sokoban.Model.Account;
import Sokoban.Model.AccountsSystem;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;

import static Sokoban.Login_Application.primaryStage;

public class LoginSceneController {

    @FXML
    private Button Btn_login, Btn_SignUp, Btn_visitor;

    @FXML
    private AnchorPane Pane;
    @FXML
    private PasswordField Input_passwd;

    @FXML
    private TextField Input_username;

    @FXML
    private ImageView Img_SUST;

    @FXML
    private Label Label_Sokoban;

    public void initialize() {
        Platform.runLater(AudioManager::playbackgroundPeace);
    }

    @FXML
    void LoginBtnReleased() throws IOException {

        //开始一定要有一个accounts.ser，不然会admin空指针
        //不能在这里loadAccounts，ser会被新的空AccountsSystem覆盖
        //如果没有accounts就不要load
        AccountsSystem.loadAccounts();
        String username = Input_username.getText();
        String passwd = Input_passwd.getText();

        if (AccountsSystem.checkAccount(AccountsSystem.getNames(), username, AccountsSystem.getPasswords(), passwd)) {

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Reminder");
            alert.setHeaderText(String.format("Welcome, Niker %s!", username));
            alert.setContentText("Log in successfully.");
            alert.showAndWait();
            //切换场景
            URL url = getClass().getResource("/Sokoban/Fxml/LevelScene.fxml");
            //加载完fxml文件后，获取其中的root
            Parent root = FXMLLoader.load(Objects.requireNonNull(url));
            //设置场景
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
        } else if (username.equals("admin") && passwd.equals("admin")) {
            Account admin = new Account(false, true);
            Account.saveAccount(admin);
            //切换场景
            URL url = getClass().getResource("/Sokoban/Fxml/LevelScene.fxml");
            //加载完fxml文件后，获取其中的root
            Parent root = FXMLLoader.load(Objects.requireNonNull(url));
            //设置场景
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Failed to log in");
            alert.setContentText("Incorrect username or password.");
            alert.showAndWait();
        }
    }

    @FXML
    void SignUpReleased() throws IOException {
        URL url = getClass().getResource("/Sokoban/Fxml/SignUp.fxml");
        //加载完fxml文件后，获取其中的root
        Parent root = FXMLLoader.load(Objects.requireNonNull(url));
        //设置场景
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
    }


    @FXML
    void VisitorBtnReleased() throws IOException {
        Account visitor = new Account(true, false);
        Account.saveAccount(visitor);
        Platform.runLater(AudioManager::stop);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("caution");
        alert.setHeaderText("visitor mode");
        alert.setContentText("Your game progress will not be saved.");
        alert.showAndWait();
        //切换场景
        URL url = getClass().getResource("/Sokoban/Fxml/Level1.fxml");
        //加载完fxml文件后，获取其中的root
        Parent root = FXMLLoader.load(Objects.requireNonNull(url));
        //设置场景
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
    }
    //执行顺序总结
    //FXMLLoader.load()
    //
    //加载 FXML 文件。
    //创建对应的 JavaFX 控件。
    //实例化控制器类（如果 fx:controller 被指定）
    //
    //控制器类实例化，并注入 FXML 中的控件。
    //注入 @FXML 字段：
    //
    //根据 fx:id 将 FXML 中的组件注入到控制器中的对应字段。
    //调用 @FXML 注解的方法（如事件处理方法）。
    //
    //执行 FXML 文件中定义的事件（如 onAction）对应的控制器方法。
    //调用 initialize() 方法：
    //
    //如果控制器实现了 Initializable 接口，或者有带 @FXML 注解的 initialize() 方法，在控件注入完成后调用。
    //设置 Scene 并显示在 Stage 上：
    //
    //将加载的 FXML 根节点设置为 Scene 的根节点，并显示窗口。
    //重要注意点
    //initialize() 方法在 FXMLLoader.load() 之后、事件方法之前执行。
    //@FXML 注解的方法会在相应的事件触发时调用。
    //initialize() 方法不一定每次都被调用，只有在 FXMLLoader 完成控件注入后才会调用。


}
