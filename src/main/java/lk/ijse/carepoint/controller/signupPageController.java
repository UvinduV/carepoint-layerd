package lk.ijse.carepoint.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.carepoint.bo.BOFactory;
import lk.ijse.carepoint.bo.custom.impl.SignUpBOImpl;
import lk.ijse.carepoint.bo.custom.SignUpBO;
import lk.ijse.carepoint.dto.UserDto;

import java.io.IOException;
import java.sql.SQLException;

public class signupPageController {
    @FXML
    private AnchorPane rootNode;

    @FXML
    private Button btnSignIn;
    @FXML
    private JFXButton btnSignup;


    @FXML
    private JFXTextField txtFName;

    @FXML
    private JFXTextField txtLName;

    @FXML
    private JFXPasswordField txtPassword;

    @FXML
    private JFXTextField txtPosition;

    @FXML
    private JFXPasswordField txtRePassword;

    @FXML
    private JFXTextField txtUsername;

   // private UserDAO userDAO=new UserDAOImpl();
    SignUpBO signUpBO= (SignUpBO) BOFactory.getBoFactory().getBo(BOFactory.BOTypes.SIGNUP);
    public void btnSignupOnAction(ActionEvent actionEvent) {
        String username = txtUsername.getText();
        String password = txtPassword.getText();
        String rePassword = txtRePassword.getText();
        String first_name = txtFName.getText();
        String last_Name = txtLName.getText();
        String possition = txtPosition.getText();

        if (username.isEmpty() || password.isEmpty() || rePassword.isEmpty() || first_name.isEmpty() || last_Name.isEmpty() || possition.isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Please enter all fields").show();
            return;

        }
        if (!password.equals(rePassword)) {
            new Alert(Alert.AlertType.ERROR, "Confirm Password is does not match").show();
            return;

        }

        var dto = new UserDto(username, password, first_name, last_Name, possition);


        try {
           boolean isSaved = signUpBO.saveUser(dto);
            if (isSaved) {
                new Alert(Alert.AlertType.CONFIRMATION, "customer saved!").show();
                //clearFields();
                loadToLogin();
            }
        } catch (SQLException | IOException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }


    }

    private void loadToLogin() throws IOException {
        System.out.println("Successfuly  login to the Login Page");
        Parent rootNode = FXMLLoader.load(getClass().getResource("/view/login_form.fxml"));
        Scene scene = new Scene(rootNode);
        Stage stage = (Stage) this.rootNode.getScene().getWindow();
        stage.setTitle("carepoint");
        stage.setScene(scene);
    }


    public void btnSignInOnAction(ActionEvent event) throws IOException {
        System.out.println("Navigating to the Login Page");
        Parent rootNode = FXMLLoader.load(getClass().getResource("/view/login_form.fxml"));
        Scene scene = new Scene(rootNode);
        Stage stage = (Stage) this.rootNode.getScene().getWindow();
        stage.setTitle("carepoint");
        stage.setScene(scene);
    }
}
