package lk.ijse.carepoint.controller;

import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import lk.ijse.carepoint.db.DbConnection;
import lk.ijse.carepoint.dto.*;
import lk.ijse.carepoint.dto.tm.appointmentTm;
import lk.ijse.carepoint.model.*;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

public class serviceAppointFrom {
    @FXML
    private Label lblcustEmail;

    @FXML
    private AnchorPane AppointPanel;

    @FXML
    private JFXComboBox<String> cmbPackageCode;

    @FXML
    private JFXComboBox<String> cmbVehicleId;

    @FXML
    private Label lblAppointDate;

    @FXML
    private Label lblAppointId;

    @FXML
    private Label lblCustID;

    @FXML
    private Label lblCustomerName;

    @FXML
    private Label lblDescription;

    @FXML
    private Label lblTimeAppoint;

    @FXML
    private Label lblUnitPrice;

    @FXML
    private TextField txtSheduleID;

    @FXML
    private TextField txtTelcustomer;

    private ObservableList<appointmentTm> obList = FXCollections.observableArrayList();

    private CustomerDAO customerDAO=new CustomerDAOImpl();
    private VehicleDAO vehicleDAO=new VehicleDAOImpl();
    private SheduleDAO sheduleDAO =new SheduleDAOImpl();


    public void initialize() {
        loadToVehicleID();
        generateNextAppointId();
        loadToPackageID();

    }
    private void generateNextAppointId() {
        try {
            String orderId = ServiceAppointModel.generateNextAppointId();
            lblAppointId.setText(orderId);
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

        //nextToDataShedule;
    }



////////////////////////////////////////////////////////
    public void cmbPackageOnAction(ActionEvent event) {
        String id =  cmbPackageCode.getValue();
//        CustomerModel customerModel = new CustomerModel();
        try {
            PackageDto packageDto = PackageModel.searchPackage(id);
            lblDescription.setText(packageDto.getType());
            lblUnitPrice.setText(String.valueOf(packageDto.getAmount()));

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    private void loadToPackageID() {
        ObservableList<String> obList = FXCollections.observableArrayList();
        try {
            List<PackageDto> PackageDtos = PackageModel.loadAllItems();

            for (PackageDto dto : PackageDtos) {
                obList.add(dto.getId());
            }
            cmbPackageCode.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

   /* public void btnAddToCartOnAction(ActionEvent event) {
        String vehicleNo = cmbVehicleId.getValue();
        String packageId = cmbPackageCode.getValue();
        String type = lblDescription.getText();
        double unitPrice = Double.parseDouble(lblUnitPrice.getText());
        double total = (unitPrice++);
        Button btn = new Button("Remove");

        setRemoveBtnAction(btn);
        btn.setCursor(Cursor.HAND);

        if (!obList.isEmpty()) {
            for (int i = 0; i < tblOrderCart.getItems().size(); i++) {
                if (colIVehicleNo.getCellData(i).equals(vehicleNo)) {

                    total = (unitPrice++);

                    obList.get(i).setTotal(total);

                    calculateTotal();
                    tblOrderCart.refresh();
                    return;
                }
            }
        }
        var appointmentTm = new appointmentTm(vehicleNo, packageId, type, unitPrice, total, btn);

        obList.add(appointmentTm);

        tblOrderCart.setItems(obList);
        calculateTotal();
    }*/

    /*private void calculateTotal() {
        double total = 0;
        for (int i = 0; i < tblOrderCart.getItems().size(); i++) {
            total += (double) colTotal.getCellData(i);
        }
        lblNetTotal.setText(String.valueOf(total));
    }

   private void setRemoveBtnAction(Button btn) {
        btn.setOnAction((e) -> {
            ButtonType yes = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
            ButtonType no = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);

            Optional<ButtonType> type = new Alert(Alert.AlertType.INFORMATION, "Are you sure to remove?", yes, no).showAndWait();

            if (type.orElse(no) == yes) {
                int focusedIndex = tblOrderCart.getSelectionModel().getFocusedIndex();
                obList.remove(focusedIndex);
                //obList.remove(focusedIndex);
                tblOrderCart.refresh();
                calculateTotal();
            }
        });
    }*/


    public void btnPlaceOrderOnAction(ActionEvent event) {
        String appoint_Id = lblAppointId.getText();
        String cust_Id = lblCustID.getText();
        String vehicle_No = cmbVehicleId.getValue();
        String shedule_Id = txtSheduleID.getText();
        String package_Id = cmbPackageCode.getValue();
        String paymentId = null;
        Date date = Date.valueOf(lblAppointDate.getText());
        String time= lblTimeAppoint.getText();
        Double amount= Double.valueOf(lblUnitPrice.getText());

        //boolean isValidation = validateAppoint();
       // if(isValidation) {
            //new Alert(Alert.AlertType.INFORMATION, "valied customer").show();

            if (appoint_Id.isEmpty() || cust_Id.isEmpty() || vehicle_No.isEmpty() || shedule_Id.isEmpty() || package_Id.isEmpty() || date.toString().isEmpty() || time.isEmpty() || amount.toString().isEmpty()) {
                new Alert(Alert.AlertType.ERROR, "Please enter all fields").show();
                return;
            }
            var dto = new serviceAppointDto(appoint_Id, cust_Id, vehicle_No, shedule_Id, package_Id,paymentId, date, time, amount);

            try {
                boolean isSaved = ServiceAppointModel.saveAppoint(dto);

                if (isSaved) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Appoint saved sucessfully!").show();
                    clearFields();
                    generateJasper();


                    navigateToWaitingAppoint();
                    ///////

                    try {
                        CustomerDto customerDto = customerDAO.searchCustomer(cust_Id);
                        //            System.out.println(customerDto);
                        if (customerDto != null) {
                            lblcustEmail.setText(customerDto.getAddress());
                        } else {
                            // new Alert(Alert.AlertType.INFORMATION, "customer not found").show();
                        }
                    } catch (SQLException e) {
                        new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
                    }
                    String email = lblcustEmail.getText();

                    System.out.println(email);
                    ///////
                    EmailSender mail = new EmailSender();
                    mail.setMsg( "Dear customer,Your appointment is booked. " +"\n"
                            + "Appointment ID: " + appoint_Id +"\n"
                            + "Customer ID: " + cust_Id +"\n"
                            + "Vehicle No: " + vehicle_No +"\n"
                            + "Date: " + date +"\n"
                            + "Time: " + time +"\n");
                    mail.setTo(email);
                    mail.setSubject("Subject");

                    Thread thread = new Thread(mail);
                    thread.start();
                    ///////
                }
            } catch (SQLException | IOException | JRException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            }

        //}

    }

    //////////jasper generate//////////////////
    private void generateJasper() throws JRException, SQLException {
        InputStream resourceAsStream = getClass().getResourceAsStream("/report/appoint.jrxml");
        JasperDesign load = JRXmlLoader.load(resourceAsStream);
        JasperReport jasperReport = JasperCompileManager.compileReport(load);

        JasperPrint jasperPrint =
                JasperFillManager.fillReport(
                        jasperReport,
                        null,
                        DbConnection.getInstance().getConnection() //database connection
                );

        JasperViewer.viewReport(jasperPrint, false);
    }

    private void navigateToWaitingAppoint() throws IOException {
        AppointPanel.getChildren().clear();
        AppointPanel.getChildren().add(FXMLLoader.load(getClass().getResource("/view/waitingAppointForm.fxml")));

    }

    private void clearFields() {
        lblAppointId.setText("");
        lblCustID.setText("");
        cmbVehicleId.getSelectionModel().clearSelection();
        txtSheduleID.setText("");
        cmbPackageCode.getSelectionModel().clearSelection();
        lblAppointDate.setText("");
        lblTimeAppoint.setText("");
        lblUnitPrice.setText("");
    }

    public void btnBackOnAction(ActionEvent event) throws IOException {
        AppointPanel.getChildren().clear();
        AppointPanel.getChildren().add(FXMLLoader.load(getClass().getResource("/view/registerStatus_form.fxml")));

    }

    public void cmbVehicleIdOnAction(ActionEvent event) {

    }
    private void loadToVehicleID() {
        String custID = lblCustID.getText();
        ObservableList<String> obList = FXCollections.observableArrayList();
        try {
            List<VehicleDto> VehicleDtos = vehicleDAO.loadAllItems();

            for (VehicleDto dto : VehicleDtos) {
                obList.add(dto.getVehicle_no());
            }
            cmbVehicleId.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void txtTelCustOnAction(ActionEvent event) {
        String tel = txtTelcustomer.getText();

//        var model = new CustomerModel();
        try {
            CustomerDto customerDto = customerDAO.searchCustomerID(tel);
//            System.out.println(customerDto);
            if (customerDto != null) {
                lblCustID.setText(customerDto.getCust_id());
                lblCustomerName.setText(customerDto.getName());
                txtTelcustomer.setText(customerDto.getTel());
            } else {
                new Alert(Alert.AlertType.INFORMATION, "customer not found").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    public void txtSheduleIDOnAction(ActionEvent event) {
        String shedule_ID = txtSheduleID.getText();


        try {
            SheduleDto sheduleDto = sheduleDAO.searchSheduleID(shedule_ID);

            if (sheduleDto != null) {
                txtSheduleID.setText(sheduleDto.getShedule_Id());
                lblAppointDate.setText(String.valueOf(sheduleDto.getDate()));
                lblTimeAppoint.setText(sheduleDto.getAvaliability());
            } else {
                new Alert(Alert.AlertType.INFORMATION, "shedule time not found").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }
}
