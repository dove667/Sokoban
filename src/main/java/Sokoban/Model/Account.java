package Sokoban.Model;

import org.jetbrains.annotations.Nullable;

import java.io.*;

public class Account implements Serializable {
//储存Level win信息，currentLevel信息，visitor信息

    public static void saveAccount(Account account) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("account.ser"))) {
            out.writeObject(account);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Account loadAccount() {
        Account account = null;
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("account.ser"))) {
            account = (Account) in.readObject();

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();

        }
        return account;
    }

    private  boolean L1win;
    private  boolean L2win;
    private  boolean L3win;
    private  boolean L4win;
    private  boolean L5win;

    public boolean isL1win() {
        return L1win;
    }

    public  boolean isL2win() {
        return L2win;
    }

    public boolean isL3win() {
        return L3win;
    }

    public boolean isL4win() {
        return L4win;
    }

    public  boolean isL5win() {
        return L5win;
    }

    public void setL1win(boolean l1win) {
        L1win = l1win;
    }

    public void setL2win(boolean l2win) {
        L2win = l2win;
    }

    public void setL3win(boolean l3win) {
        L3win = l3win;
    }

    public void setL4win(boolean l4win) {
        L4win = l4win;
    }

    public void setL5win(boolean l5win) {
        L5win = l5win;
    }

    private int currentLevel;

    //实现victory的nextLevel按钮多态
    public String getNextLevel() {
        if (currentLevel == 1) {
            L1win = true;
            return "/Sokoban/Level2.fxml";
        } else if (currentLevel == 2) {
            L2win = true;
            return "/Sokoban/Level3.fxml";
        } else if (currentLevel == 3) {
            L3win = true;
            return "/Sokoban/Level4.fxml";
        } else if (currentLevel == 4) {
            L4win = true;
            return "/Sokoban/Level5.fxml";
        } else {
            return "/Sokoban/LevelScene.fxml";
        }
    }

    public  int getCurrentLevel() {
        return currentLevel;
    }
    public  void setCurrentLevel(int currentLevel) {
        this.currentLevel = currentLevel;
    }



    private boolean isVisitor;

    public boolean verifyVisitor() {
        return isVisitor;
    }

    public Account(boolean isVisitor) {
        this.currentLevel = 1;
        this.isVisitor = isVisitor;
    }


}
