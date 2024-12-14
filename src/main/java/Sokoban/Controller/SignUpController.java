package Sokoban.Controller;

import Sokoban.Model.Account;
import Sokoban.Model.AccountsSystem;
import Sokoban.Model.GameSystem;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;

import static Sokoban.Login_Application.primaryStage;

public class SignUpController {

    @FXML
    private Button Btn_Return;

    @FXML
    private Button Btn_SignUp;

    @FXML
    private AnchorPane Pane;

    @FXML
    private PasswordField Password_Ensure;

    @FXML
    private PasswordField Password_input;

    @FXML
    private TextField Text_Name;

    @FXML
    void ReturnBtnClicked(MouseEvent event) {
        URL url = getClass().getResource("/Sokoban/Fxml/LoginScene.fxml");
        Parent root;
        try {
            root = FXMLLoader.load(Objects.requireNonNull(url));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        //设置场景
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
    }



    @FXML
    void SignUpBtnClicked(MouseEvent event) {
        if (!Text_Name.getText().matches("[a-zA-Z0-9]+")) {
            Alert name = new Alert(Alert.AlertType.ERROR);
            name.setTitle("Invalid Name");
            name.setHeaderText(null);
            name.setContentText("Name should only contain letters and numbers.");
            name.showAndWait();
        } else if (Password_input.getText().length() < 8) {
            Alert password = new Alert(Alert.AlertType.ERROR);
            password.setTitle("Invalid Password");
            password.setHeaderText(null);
            password.setContentText("Password should be at least 8 characters long.");
            password.showAndWait();
        } else if (!Password_input.getText().equals(Password_Ensure.getText())) {
            Alert password = new Alert(Alert.AlertType.ERROR);
            password.setTitle("Password Mismatch");
            password.setHeaderText(null);
            password.setContentText("Passwords do not match.");
            password.showAndWait();
        }
        else if(AccountsSystem.checkSameName(Text_Name.getText())){
            Alert name = new Alert(Alert.AlertType.ERROR);
            name.setTitle("Invalid Name");
            name.setHeaderText(null);
            name.setContentText("Name already exists.");
            name.showAndWait();
        }
        else{
            Alert success = new Alert(Alert.AlertType.INFORMATION);
            success.setTitle("Success");
            success.setHeaderText("Sign up successful");
            success.setContentText("You can now log in with your new account.");
            success.showAndWait();


            AccountsSystem.addName(Text_Name.getText());
            AccountsSystem.addPassword(Password_Ensure.getText());
            Account account = new Account(false);
            AccountsSystem.addAccount(account);
            AccountsSystem.saveAccounts();
            URL url = getClass().getResource("/Sokoban/Fxml/LoginScene.fxml");
            Parent root;
            try {
                root = FXMLLoader.load(Objects.requireNonNull(url));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            //设置场景
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
        }
    }

}
