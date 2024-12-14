package Sokoban.Controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;

import java.util.Objects;

public class Level6Controller {

    @FXML

    private Button Btn_back;

    @FXML
    private Button Btn_down;

    @FXML
    private Button Btn_home;

    @FXML
    private Button Btn_left;

    @FXML
    private Button Btn_load;

    @FXML
    private Button Btn_right;

    @FXML
    private Button Btn_save;

    @FXML
    private Button Btn_up;

    @FXML
    private GridPane GridBoard;

    @FXML
    private ImageView Img_Back;

    @FXML
    private ImageView Img_home;

    @FXML
    private ImageView Img_load;

    @FXML
    private ImageView Img_save;

    @FXML
    private Label Label_Level1;

    @FXML
    private Label Label_steps;

    @FXML
    private Label Label_timer;

    @FXML
    private GridPane Movement;

    @FXML
    private Circle Niker;

    @FXML
    private Rectangle board1;

    @FXML
    private Rectangle board10;

    @FXML
    private Rectangle board11;

    @FXML
    private Rectangle board12;

    @FXML
    private Rectangle board13;

    @FXML
    private Rectangle board14;

    @FXML
    private Rectangle board15;

    @FXML
    private Rectangle board16;

    @FXML
    private Rectangle board17;

    @FXML
    private Rectangle board18;

    @FXML
    private Rectangle board19;

    @FXML
    private Rectangle board2;

    @FXML
    private Rectangle board20;

    @FXML
    private Rectangle board21;

    @FXML
    private Rectangle board22;

    @FXML
    private Rectangle board23;

    @FXML
    private Rectangle board24;

    @FXML
    private Rectangle board25;

    @FXML
    private Rectangle board26;

    @FXML
    private Rectangle board27;

    @FXML
    private Rectangle board28;

    @FXML
    private Rectangle board29;

    @FXML
    private Rectangle board3;

    @FXML
    private Rectangle board30;

    @FXML
    private Rectangle board31;

    @FXML
    private Rectangle board32;

    @FXML
    private Rectangle board33;

    @FXML
    private Rectangle board34;

    @FXML
    private Rectangle board35;

    @FXML
    private Rectangle board36;

    @FXML
    private Rectangle board37;

    @FXML
    private Rectangle board38;

    @FXML
    private Rectangle board39;

    @FXML
    private Rectangle board4;

    @FXML
    private Rectangle board40;

    @FXML
    private Rectangle board41;

    @FXML
    private Rectangle board42;

    @FXML
    private Rectangle board43;

    @FXML
    private Rectangle board44;

    @FXML
    private Rectangle board45;

    @FXML
    private Rectangle board5;

    @FXML
    private Rectangle board6;

    @FXML
    private Rectangle board7;

    @FXML
    private Rectangle board8;

    @FXML
    private Rectangle board9;

    @FXML
    private Rectangle box1;

    @FXML
    private Rectangle box2;

    @FXML
    private Rectangle box3;

    @FXML
    private Rectangle box4;

    @FXML
    private Rectangle box5;

    @FXML
    private Rectangle ground1;

    @FXML
    private Rectangle ground11;

    @FXML
    private Rectangle ground110;

    @FXML
    private Rectangle ground111;

    @FXML
    private Rectangle ground112;

    @FXML
    private Rectangle ground113;

    @FXML
    private Rectangle ground114;

    @FXML
    private Rectangle ground115;

    @FXML
    private Rectangle ground116;

    @FXML
    private Rectangle ground117;

    @FXML
    private Rectangle ground118;

    @FXML
    private Rectangle ground119;

    @FXML
    private Rectangle ground12;

    @FXML
    private Rectangle ground120;

    @FXML
    private Rectangle ground121;

    @FXML
    private Rectangle ground122;

    @FXML
    private Rectangle ground123;

    @FXML
    private Rectangle ground124;

    @FXML
    private Rectangle ground125;

    @FXML
    private Rectangle ground126;

    @FXML
    private Rectangle ground127;

    @FXML
    private Rectangle ground128;

    @FXML
    private Rectangle ground129;

    @FXML
    private Rectangle ground13;

    @FXML
    private Rectangle ground130;

    @FXML
    private Rectangle ground131;

    @FXML
    private Rectangle ground132;

    @FXML
    private Rectangle ground133;

    @FXML
    private Rectangle ground134;

    @FXML
    private Rectangle ground135;

    @FXML
    private Rectangle ground136;

    @FXML
    private Rectangle ground137;

    @FXML
    private Rectangle ground138;

    @FXML
    private Rectangle ground139;

    @FXML
    private Rectangle ground14;

    @FXML
    private Rectangle ground140;

    @FXML
    private Rectangle ground141;

    @FXML
    private Rectangle ground142;

    @FXML
    private Rectangle ground143;

    @FXML
    private Rectangle ground144;

    @FXML
    private Rectangle ground145;

    @FXML
    private Rectangle ground146;

    @FXML
    private Rectangle ground147;

    @FXML
    private Rectangle ground148;

    @FXML
    private Rectangle ground149;

    @FXML
    private Rectangle ground15;

    @FXML
    private Rectangle ground150;

    @FXML
    private Rectangle ground151;

    @FXML
    private Rectangle ground152;

    @FXML
    private Rectangle ground153;

    @FXML
    private Rectangle ground154;

    @FXML
    private Rectangle ground155;

    @FXML
    private Rectangle ground156;

    @FXML
    private Rectangle ground157;

    @FXML
    private Rectangle ground158;

    @FXML
    private Rectangle ground159;

    @FXML
    private Rectangle ground16;

    @FXML
    private Rectangle ground160;

    @FXML
    private Rectangle ground161;

    @FXML
    private Rectangle ground162;

    @FXML
    private Rectangle ground163;

    @FXML
    private Rectangle ground164;

    @FXML
    private Rectangle ground165;

    @FXML
    private Rectangle ground166;

    @FXML
    private Rectangle ground167;

    @FXML
    private Rectangle ground168;

    @FXML
    private Rectangle ground169;

    @FXML
    private Rectangle ground17;

    @FXML
    private Rectangle ground170;

    @FXML
    private Rectangle ground171;

    @FXML
    private Rectangle ground172;

    @FXML
    private Rectangle ground173;

    @FXML
    private Rectangle ground174;

    @FXML
    private Rectangle ground175;

    @FXML
    private Rectangle ground176;

    @FXML
    private Rectangle ground177;

    @FXML
    private Rectangle ground178;

    @FXML
    private Rectangle ground179;

    @FXML
    private Rectangle ground18;

    @FXML
    private Rectangle ground180;

    @FXML
    private Rectangle ground181;

    @FXML
    private Rectangle ground182;

    @FXML
    private Rectangle ground183;

    @FXML
    private Rectangle ground184;

    @FXML
    private Rectangle ground185;

    @FXML
    private Rectangle ground186;

    @FXML
    private Rectangle ground187;

    @FXML
    private Rectangle ground188;

    @FXML
    private Rectangle ground189;

    @FXML
    private Rectangle ground19;

    @FXML
    private Rectangle ground190;

    @FXML
    private Rectangle ground191;

    @FXML
    private Rectangle ground192;

    @FXML
    private Rectangle ground193;

    @FXML
    private Label myTime;

    @FXML
    private AnchorPane pane;

    @FXML
    private Label space;

    @FXML
    private Label steps;

    @FXML
    private Polygon target1;

    @FXML
    private Polygon target2;

    @FXML
    private Polygon target3;

    @FXML
    private Polygon target4;

    @FXML
    private Polygon target5;


    public void initialize() {
        Platform.runLater(() -> {
            //图形初始化
            Image SUST = new Image("file:src/main/resources/Sokoban/Sokoban/pictures/SUST.jpeg");
            Niker.setFill(new ImagePattern(SUST));

            Image imageUp = new Image(Objects.requireNonNull(getClass().getResource("/Sokoban/Sokoban/pictures/up.png")).toExternalForm());
            ImageView imageViewUp = new ImageView(imageUp);
            imageViewUp.setFitHeight(60);
            imageViewUp.setFitWidth(60);
            Btn_up.setGraphic(imageViewUp);

            Image imageDown = new Image(Objects.requireNonNull(getClass().getResource("/Sokoban/Sokoban/pictures/down.png")).toExternalForm());
            ImageView imageViewDown = new ImageView(imageDown);
            imageViewDown.setFitHeight(60);
            imageViewDown.setFitWidth(60);
            Btn_down.setGraphic(imageViewDown);

            Image imageLeft = new Image(Objects.requireNonNull(getClass().getResource("/Sokoban/Sokoban/pictures/left.png")).toExternalForm());
            ImageView imageViewLeft = new ImageView(imageLeft);
            imageViewLeft.setFitHeight(60);
            imageViewLeft.setFitWidth(60);
            Btn_left.setGraphic(imageViewLeft);

            Image imageRight = new Image(Objects.requireNonNull(getClass().getResource("/Sokoban/Sokoban/pictures/right.png")).toExternalForm());
            ImageView imageViewRight = new ImageView(imageRight);
            imageViewRight.setFitHeight(60);
            imageViewRight.setFitWidth(60);
            Btn_right.setGraphic(imageViewRight);
        });
    }

    @FXML
    void BackBtnPressed(MouseEvent event) {

    }

    @FXML
    void DownBtnPressed(MouseEvent event) {

    }

    @FXML
    void HomeBtnPressed(MouseEvent event) {

    }

    @FXML
    void LeftBtnPressed(MouseEvent event) {

    }

    @FXML
    void LoadBtnPressed(MouseEvent event) {

    }

    @FXML
    void RightBtnPressed(MouseEvent event) {

    }

    @FXML
    void SaveBtnPressed(MouseEvent event) {

    }

    @FXML
    void UpBtnPressed(MouseEvent event) {

    }

}
