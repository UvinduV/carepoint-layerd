package lk.ijse.carepoint.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.carepoint.dto.UserDto;
import lk.ijse.carepoint.model.UserModel;

import java.io.IOException;
import java.sql.SQLException;

public class loginFormController {
    @FXML
    private JFXButton btnSignIn;

    @FXML
    private Button btnSignUp;

    @FXML
    private AnchorPane rootNode;

    @FXML
    private TextField txtPassword;

    @FXML
    private TextField txtUsername;
    private UserModel userModel =new UserModel();
    public void btnSignInOnAction(ActionEvent event) {
        String username = txtUsername.getText();
        String password = txtPassword.getText();

        if (username.isEmpty() || password.isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Please enter all fields").show();
            return;
        }
        //UserDto userDto = null;
        try {
            UserDto userDto = userModel.getUser(username, password);
            if (userDto != null) {
                new Alert(Alert.AlertType.CONFIRMATION, "Login Successful").show();
                System.out.println("Login Successful");
                //clearFields();
                loadToDashBoard();
            }else {
                new Alert(Alert.AlertType.ERROR, "incorrect username or password").show();
                System.out.println("Login Failed ,incorrect username or password");
            }
        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        }


    }

    private void loadToDashBoard() throws IOException {
        System.out.println("Successfuly  login to the Login Page");
        Parent rootNode = FXMLLoader.load(getClass().getResource("/view/dashboard_form.fxml"));
        Scene scene = new Scene(rootNode);
        Stage stage = (Stage) this.rootNode.getScene().getWindow();
        stage.setTitle("carepoint");
        stage.setScene(scene);
    }


    public void btnSignUpOnAction(ActionEvent event) throws IOException {
        System.out.println("Navigating to the Sign Up Page");
        Parent rootNode = FXMLLoader.load(getClass().getResource("/view/signup_page.fxml"));
        Scene scene = new Scene(rootNode);
        Stage stage = (Stage) this.rootNode.getScene().getWindow();
        stage.setTitle("carepoint");
        stage.setScene(scene);
    }
}