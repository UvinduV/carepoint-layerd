package lk.ijse.carepoint.controller;

//import com.google.protobuf.Message;
import com.jfoenix.controls.JFXButton;
//import com.mysql.cj.Session;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import lk.ijse.carepoint.dto.CustomerDto;
import lk.ijse.carepoint.dto.VehicleDto;
import lk.ijse.carepoint.model.CustomerDAO;
import lk.ijse.carepoint.model.CustomerDAOImpl;
import lk.ijse.carepoint.model.VehicleDAO;
import lk.ijse.carepoint.model.VehicleDAOImpl;

//import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
//import java.util.Properties;
import java.util.regex.Pattern;

public class registerStatus {

    @FXML
    private JFXButton btnCreateAppoint;
    @FXML
    private TextField txtVehicleId;
    @FXML
    private JFXButton btnCustomerSave;

    @FXML
    private JFXButton btnVehicleSave;

    @FXML
    private ComboBox<String> cmbCarType;

    @FXML
    private ComboBox<String> cmbFuelType;

    @FXML
    private ComboBox<String> cmbOwnerId;

    @FXML
    private AnchorPane custPanel;

    @FXML
    private Label lblCustId;

    @FXML
    private AnchorPane regStatusPanel;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtTel;

    @FXML
    private AnchorPane vehiclePanel;

    private CustomerDAO customerDAO =new CustomerDAOImpl();
    private VehicleDAO vehicleDAO = new VehicleDAOImpl();

    //private String[] carType = {"CAR","VAN","SUV","BUS"};

    public void initialize() {
        generateNextCustID();
        loadcarType();
        loadFuelType();
        loadOwnerId();

    }

    ///////////////////////////////

    private void generateNextCustID() {

        try {
           String orderId = customerDAO.generateNextOrderId();
           lblCustId.setText(orderId);
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

    }


    public void txtNameOnAction(ActionEvent event) {
    }

    public void txtAddressOnAction(ActionEvent event) {
    }

    public void txtTelOnAction(ActionEvent event) {
    }
    ////////////////////////////


    public void btnVehicleSaveOnAction(ActionEvent event) {
        String vehicle_no = txtVehicleId.getText();
        String cust_id = cmbOwnerId.getValue();
        String type = cmbCarType.getValue();
        String fuel_type = cmbFuelType.getValue();

        boolean isValidation = validateVehicle();
        if(isValidation) {
            new Alert(Alert.AlertType.INFORMATION, "valied vehicle").show();

            if (vehicle_no.isEmpty() || cust_id.isEmpty() || type.isEmpty() || fuel_type.isEmpty()) {
                new Alert(Alert.AlertType.ERROR, "Please enter all fields").show();
                return;

            }

            var dto = new VehicleDto(vehicle_no, cust_id, type, fuel_type);
           String email = "uvindu7070@gmail.com";

            try {
                boolean isSaved = vehicleDAO.saveVehicle(dto);

                if (isSaved) {
                    new Alert(Alert.AlertType.CONFIRMATION, "vehicle saved sucessfully!").show();
                   //////


                    /////
                    clearFields();
                    navigateToAppoint();

                }
            } catch (SQLException | IOException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            }
        }


    }
    private void navigateToAppoint() throws IOException {
        regStatusPanel.getChildren().clear();
        regStatusPanel.getChildren().add(FXMLLoader.load(getClass().getResource("/view/serviceAppoint_form.fxml")));

    }

    private boolean validateVehicle() {
        String vehicle_no = txtVehicleId.getText();
        boolean matches1 = Pattern.compile("[A-Z]{2,}[\\d]{4}").matcher(vehicle_no).matches();
        if (!matches1) {
            new Alert(Alert.AlertType.ERROR, "invalid vehicle No").show();
            return false;
        }
        return true;
    }

    private void loadcarType() {
        /*ObservableList<String> obList = FXCollections.observableArrayList();
        List<String> carType = Arrays.asList("CAR","VAN","SUV","BUS");
        obList.addAll(carType);
        cmbCarType.setItems(obList);*/
        ObservableList<String> obList = FXCollections.observableArrayList();
        List<String> itemDtos = Arrays.asList("CAR","VAN","SUV","BUS");

        obList.addAll(itemDtos);
        cmbCarType.setItems(obList);


    }
    private void loadFuelType() {

        ObservableList<String> obList = FXCollections.observableArrayList();
        List<String> itemFuel = Arrays.asList("PETROL","DIESEL","HYBRID","ELECTRIC");

        obList.addAll(itemFuel);
        cmbFuelType.setItems(obList);


    }
    private void loadOwnerId() {
        ObservableList<String> obList = FXCollections.observableArrayList();
        try {
            List<CustomerDto> CustomerDtos = customerDAO.loadAllItems();

            for (CustomerDto dto : CustomerDtos) {
                obList.add(dto.getCust_id());
            }
            cmbOwnerId.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void btnCustomerSaveOnAction(ActionEvent event) {
        String cust_id = lblCustId.getText();
        String name = txtName.getText();
        String address = txtAddress.getText();
        String tel = txtTel.getText();

        boolean isValidation = validateCustomer();
        if(isValidation) {
            new Alert(Alert.AlertType.INFORMATION, "valied customer").show();

            if (cust_id.isEmpty() || name.isEmpty() || address.isEmpty() || tel.isEmpty()) {
                new Alert(Alert.AlertType.ERROR, "Please enter all fields").show();
                return;

            }


            var dto = new CustomerDto(cust_id,name,address,tel);

            try {
                boolean isSaved = customerDAO.saveCustomer(dto);

                if (isSaved) {
                    loadOwnerId();
                   // new Alert(Alert.AlertType.CONFIRMATION, "customer saved sucessfully!").show();

                    //////////
                    String email = address;
                    System.out.println(email);

                    EmailSender mail = new EmailSender();
                    mail.setMsg("Hello " + txtName.getText() + ", \n Your successfully registed in carepoint family ! ");
                    mail.setTo(email);
                    mail.setSubject("Subject");

                    Thread thread = new Thread(mail);
                    thread.start();
                    ///////
                    new Alert(Alert.AlertType.CONFIRMATION, "customer saved sucessfully!").show();
                    clearFields();
                }
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            }
        }

    }


    private void clearFields() {
        txtVehicleId.clear();
        txtName.clear();
        txtAddress.clear();
        txtTel.clear();
    }

    private boolean validateCustomer() {
        String name = txtName.getText();
        boolean matches1 = Pattern.compile("[A-Z][a-z]{3,}").matcher(name).matches();
        if (!matches1) {
            new Alert(Alert.AlertType.ERROR, "invalid name").show();
            return false;
        }

        String tel = txtTel.getText();
        boolean matches3 = Pattern.compile("[\\d]{10}").matcher(tel).matches();
        if(!matches3) {
            new Alert(Alert.AlertType.ERROR, "invalid tel").show();
            return false;
        }
        return true;
    }

    public void btnCreateAppointOnAction(ActionEvent event) throws IOException {
        navigateToAppoint();
    }
    ///////


    ////////////////// email sending code///////

}
