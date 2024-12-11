package Sokoban.Controller;

import Sokoban.Model.GameSystem;
import javafx.animation.TranslateTransition;
import javafx.scene.Node;
import javafx.util.Duration;



/*将动画效果封装到 AnimationController 类中，实现动态界面控制与交互事件控制分离
，fxml初始化静态界面，动画效果由该类控制*/
public class AnimationController {
    static int L1 = 100;
    static int L2 = 80;
    static int L345 = 70;
    public static void resetNodePosition(Node node) {
        node.setTranslateX(0); // 重置水平偏移
        node.setTranslateY(0); // 重置垂直偏移
    }

    public static void MoveRight(Node node, int col, int row) {
        TranslateTransition MoveRight = new TranslateTransition();
        MoveRight.setDuration(Duration.seconds(0.25));
        MoveRight.setNode(node);// 应用到的控件
        if (GameSystem.getCurrentLevel() == 1) MoveRight.setByX(L1);
        else if (GameSystem.getCurrentLevel() == 2) MoveRight.setByX(L2);
        else MoveRight.setByX(L345);
        MoveRight.setCycleCount(1);  // 动画循环次数
        MoveRight.setAutoReverse(false);  // 不反转
        MoveRight.play();
    }

    public static void MoveLeft(Node node, int col, int row) {
        TranslateTransition MoveRight = new TranslateTransition();
        MoveRight.setDuration(Duration.seconds(0.25));
        MoveRight.setNode(node);  // 应用到的控件
        if (GameSystem.getCurrentLevel() == 1) MoveRight.setByX(-L1);
        else if (GameSystem.getCurrentLevel() == 2) MoveRight.setByX(-L2);
        else MoveRight.setByX(-L345);
        MoveRight.setCycleCount(1);  // 动画循环次数
        MoveRight.setAutoReverse(false);  // 不反转
        MoveRight.play();
    }

    public static void MoveUp(Node node, int col, int row) {
        TranslateTransition MoveRight = new TranslateTransition();
        MoveRight.setDuration(Duration.seconds(0.25));
        MoveRight.setNode(node);  // 应用到的控件
        if (GameSystem.getCurrentLevel() == 1) MoveRight.setByY(-L1);
        else if (GameSystem.getCurrentLevel() == 2) MoveRight.setByY(-L2);
        else MoveRight.setByY(-L345);
        MoveRight.setCycleCount(1);  // 动画循环次数
        MoveRight.setAutoReverse(false);  // 不反转
        MoveRight.play();
    }

    public static void MoveDown(Node node, int col, int row) {
        TranslateTransition MoveRight = new TranslateTransition();
        MoveRight.setDuration(Duration.seconds(0.25));
        MoveRight.setNode(node);  // 应用到的控件
        if (GameSystem.getCurrentLevel() == 1) MoveRight.setByY(L1);
        else if (GameSystem.getCurrentLevel() == 2) MoveRight.setByY(L2);
        else MoveRight.setByY(L345);
        MoveRight.setCycleCount(1);  // 动画循环次数
        MoveRight.setAutoReverse(false);  // 不反转
        MoveRight.play();
    }
}

