package lk.ijse.carepoint.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;

public class dashboardFormController {
    @FXML
    private Label lblTime;
    @FXML
    private Label lblDate;

    @FXML
    private ImageView imHome;
    @FXML
    private JFXButton btnAppoint;

    @FXML
    private JFXButton btnHome;

    @FXML
    private JFXButton btnItems;

    @FXML
    private JFXButton btnLogout;

    @FXML
    private JFXButton btnRecods;

    @FXML
    private JFXButton btnShedule;

    @FXML
    private AnchorPane rootHome;

    @FXML
    private AnchorPane rootNode;

    public void initialize(){
        lblDate.setText(LocalDate.now().toString());
       // lblTime.setText(java.time.LocalTime.now().toString());
    }

    public void btnHomeOnAction(ActionEvent event) throws IOException {
        rootNode.getChildren().clear();
        rootNode.getChildren().add(FXMLLoader.load(getClass().getResource("/view/dashboard_form.fxml")));
    }

    public void btnSheduleOnAction(ActionEvent event) throws IOException {
        rootHome.getChildren().clear();
        rootHome.getChildren().add(FXMLLoader.load(getClass().getResource("/view/shedule_form.fxml")));

    }

    public void btnAppointOnAction(ActionEvent event) throws IOException {
        rootHome.getChildren().clear();
        rootHome.getChildren().add(FXMLLoader.load(getClass().getResource("/view/registerStatus_form.fxml")));
    }

    public void btnRecodsOnAction(ActionEvent event) throws IOException {
        rootHome.getChildren().clear();
        rootHome.getChildren().add(FXMLLoader.load(getClass().getResource("/view/waitingAppointForm.fxml")));

    }

    public void btnItemsOnAction(ActionEvent event) throws IOException {
        rootHome.getChildren().clear();
        rootHome.getChildren().add(FXMLLoader.load(getClass().getResource("/view/item_form.fxml")));

    }

    public void btnLogoutOnAction(ActionEvent event) throws IOException {
        //try {
            Parent rootNode = FXMLLoader.load(getClass().getResource("/view/login_form.fxml"));
            Scene scene = new Scene(rootNode);
            Stage stage = (Stage) this.rootNode.getScene().getWindow();
            stage.setTitle("carepoint");
            stage.setScene(scene);
        //} catch (IOException e) {
            //e.printStackTrace();
        //}
        System.out.println("Logout Successful");
    }

    public void imHomeOnAction(MouseEvent mouseEvent) throws IOException {
        Parent rootNode = FXMLLoader.load(getClass().getResource("/view/dashboard_form.fxml"));
        Scene scene = new Scene(rootNode);
        Stage stage = (Stage) this.rootNode.getScene().getWindow();
        stage.setTitle("carepoint");
        stage.setScene(scene);

    }
    private void setDate() {
//        LocalDate now = LocalDate.now();
        lblDate.setText(String.valueOf(LocalDate.now()));
    }
    private void setTime() {
//          LocalTime now = LocalTime.now();
        lblTime.setText(String.valueOf(LocalDate.now()));
    }

    public void btnAppointPane(MouseEvent mouseEvent) throws IOException {
        rootHome.getChildren().clear();
        rootHome.getChildren().add(FXMLLoader.load(getClass().getResource("/view/serviceAppoint_form.fxml")));
    }
}
