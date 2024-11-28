package Sokoban.Controller;

import javafx.beans.property.SimpleObjectProperty;

public class LevelManager {
    //可以定义一个全局变量（比如存储在某个管理类中）来跟踪当前的关卡：
    private static String currentLevel = "Level1";
    private boolean isGameOver ;
    private boolean isVisitor ;
    //如果是visitor，没有游戏进度保存功能



    public LevelManager(boolean isGameOver, boolean isVisitor) {
        this.isGameOver = isGameOver;
        this.isVisitor = isVisitor;
    }

    public static String getNextLevel() {
        return switch (currentLevel) {
            case "Level1" -> "/Sokoban/Level2.fxml";
            case "Level2" -> "/Sokoban/Level3.fxml";
            case "Level3" -> "/Sokoban/Level4.fxml";
            default -> null; // switch现代化表达
        };
    }

    public static void setCurrentLevel(String level) {
        currentLevel = level;
    }


}
