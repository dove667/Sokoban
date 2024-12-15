package Sokoban;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;

public class Login_Application extends Application {
    public static Stage primaryStage;

    //class Login_Application 不是抽象的，并且不会覆盖 Application 中的抽象方法 start（Stage），重写 start 方法
    @Override
    public void start(Stage primaryStage) throws IOException {
        Login_Application.primaryStage = primaryStage;
        URL url = getClass().getResource("/Sokoban/Fxml/LoginScene.fxml");
        Parent root = FXMLLoader.load(Objects.requireNonNull(url));
        /*  资源文件夹：如果您使用 Maven 或 Gradle 构建项目，src/main/resources 是默认的资源目录。
        在代码中，getClass().getResource() 方法的路径以资源目录为根。路径需要以 / 开头，表示从类路径的根目录查找.
        如果不用/开头，会被解析为相对路径  */
        /*FXMLLoader.load() 返回的是 FXML 文件的根节点（通常是 AnchorPane、VBox 或其他布局容器）。
         如果直接将返回的根节点强制转换为 Scene，会抛出 ClassCastException。
         正确的使用方式：
         应该先将 FXMLLoader.load() 返回的根节点设置为 Scene 的根，然后创建一个 Scene 对象。*/
        Scene scene = new Scene(root); // 用 FXML 文件的根节点创建 Scene
        primaryStage.setTitle("登录界面");
        primaryStage.setScene(scene); // 设置场景
        primaryStage.show(); // 显示窗口

    }

    public static void main(String[] args) {
        launch(args);
    }

}