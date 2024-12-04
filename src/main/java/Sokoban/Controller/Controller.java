package Sokoban.Controller;

import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

public abstract class Controller {


    public abstract void initialize();
    abstract void HomeBtnPressed() throws IOException;
    abstract void BackBtnPressed() throws IOException;
    abstract void SaveBtnPressed() throws IOException;
    abstract void LoadBtnPressed() throws IOException;
    abstract void stepsUpdate();
    abstract void MovePlayer(KeyEvent event) throws IOException;
    abstract void DownBtnPressed() throws IOException;
    abstract void UpBtnPressed() throws IOException;
    abstract void LeftBtnPressed() throws IOException;
    abstract void RightBtnPressed() throws IOException;



}
