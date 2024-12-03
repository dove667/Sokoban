package Sokoban.Controller;

import Sokoban.Model.*;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import static Sokoban.Login_Application.primaryStage;
import java.util.Objects;
import static Sokoban.Model.GameSystem.*;

public class Level5Controller {

    @FXML
    private Circle Niker;

    @FXML
    private Rectangle board17;

    @FXML
    private Rectangle board171;

    @FXML
    private Rectangle board1710;

    @FXML
    private Rectangle board1711;

    @FXML
    private Rectangle board1712;

    @FXML
    private Rectangle board1713;

    @FXML
    private Rectangle board1714;

    @FXML
    private Rectangle board1715;

    @FXML
    private Rectangle board1716;

    @FXML
    private Rectangle board1717;

    @FXML
    private Rectangle board1718;

    @FXML
    private Rectangle board1719;

    @FXML
    private Rectangle board172;

    @FXML
    private Rectangle board1720;

    @FXML
    private Rectangle board1721;

    @FXML
    private Rectangle board1722;

    @FXML
    private Rectangle board17231;

    @FXML
    private Rectangle board172311;

    @FXML
    private Rectangle board172312;

    @FXML
    private Rectangle board17233;

    @FXML
    private Rectangle board1724;

    @FXML
    private Rectangle board173;

    @FXML
    private Rectangle board174;

    @FXML
    private Rectangle board175;

    @FXML
    private Rectangle board176;

    @FXML
    private Rectangle board177;

    @FXML
    private Rectangle board178;

    @FXML
    private Rectangle board179;

    @FXML
    private Polygon target1;

    @FXML
    private Polygon target2;

    @FXML
    private Polygon target3;

    GameSystem gameSystem = new GameSystem(3,3,25,8,6);



}
