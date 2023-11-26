package lk.ijse.carepoint.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.carepoint.dto.CustomerDto;
import lk.ijse.carepoint.dto.PackageDto;
import lk.ijse.carepoint.dto.VehicleDto;
import lk.ijse.carepoint.dto.tm.appointmentTm;
import lk.ijse.carepoint.model.CustomerModel;
import lk.ijse.carepoint.model.PackageModel;
import lk.ijse.carepoint.model.ServiceAppointModel;
import lk.ijse.carepoint.model.VehicleModel;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class serviceAppointFrom {

    @FXML
    private AnchorPane AppointPanel;

    @FXML
    private DatePicker appointDate;

    @FXML
    private JFXButton btnAddToCart;

    @FXML
    private JFXComboBox<String> cmbCustomerId;

    @FXML
    private JFXComboBox<String> cmbPackageCode;

    @FXML
    private JFXComboBox<String> cmbVehicleId;

    @FXML
    private TableColumn<?, ?> colAction;

    @FXML
    private TableColumn<?, ?> colIVehicleNo;

    @FXML
    private TableColumn<?, ?> colPackageId;

    @FXML
    private TableColumn<?, ?> colTotal;

    @FXML
    private TableColumn<?, ?> colType;

    @FXML
    private TableColumn<?, ?> colUnitPrice;

    @FXML
    private Label lblAppointId;

    @FXML
    private Label lblCustomerName;

    @FXML
    private Label lblDescription;

    @FXML
    private Label lblNetTotal;

    @FXML
    private Label lblUnitPrice;

    @FXML
    private TableView<appointmentTm> tblOrderCart;

    private ObservableList<appointmentTm> obList = FXCollections.observableArrayList();

    public void initialize() {
        loadToCustomerID();
        loadToVehicleID();
        generateNextAppointId();
        loadToPackageID();
        setCellValueFactory();
    }
    private void setCellValueFactory() {
        colIVehicleNo.setCellValueFactory(new PropertyValueFactory<>("vehicleNo"));
        colPackageId.setCellValueFactory(new PropertyValueFactory<>("packageId"));
        colType.setCellValueFactory(new PropertyValueFactory<>("Description"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        colTotal.setCellValueFactory(new PropertyValueFactory<>("total"));
        colAction.setCellValueFactory(new PropertyValueFactory<>("btn"));
    }
    private void generateNextAppointId() {
        try {
            String orderId = ServiceAppointModel.generateNextAppointId();
            lblAppointId.setText(orderId);
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    public void cmbCustomerOnAction(ActionEvent event) {
        String id = cmbCustomerId.getValue();
//        CustomerModel customerModel = new CustomerModel();
        try {
            CustomerDto customerDto = CustomerModel.searchCustomer(id);
            lblCustomerName.setText(customerDto.getName());

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    private void loadToCustomerID() {
        ObservableList<String> obList = FXCollections.observableArrayList();
        try {
            List<CustomerDto> CustomerDtos = CustomerModel.loadAllItems();

            for (CustomerDto dto : CustomerDtos) {
                obList.add(dto.getCust_id());
            }
            cmbCustomerId.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void cmbPackageOnAction(ActionEvent event) {
        String id = cmbPackageCode.getValue();
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

    public void btnAddToCartOnAction(ActionEvent event) {
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
    }

    private void calculateTotal() {
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
                int focusedIndex = tblOrderCart.getSelectionModel().getSelectedIndex();

                obList.remove(focusedIndex);
                tblOrderCart.refresh();
                calculateTotal();
            }
        });
    }


    public void btnPlaceOrderOnAction(ActionEvent event) {
        String appointId = lblAppointId.getText();
        Date date = Date.valueOf(appointDate.getValue());
        String customerId = cmbCustomerId.getValue();

      /*  List<appointmentTm> cartTmList = new ArrayList<>();
        for (int i = 0; i < tblOrderCart.getItems().size(); i++) {
            appointmentTm appointmentTm = tblOrderCart.getSelectionModel().getSelectedItem();

            cartTmList.add(appointmentTm);
        }

        var serviceAppointDto = new serviceAppointDto(appointId, date, customerId, cartTmList);
        try {
            boolean isSuccess = createAppoint.placeOrder(placeOrderDto);
            if (isSuccess) {
                new Alert(Alert.AlertType.CONFIRMATION, "Order Success!").show();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }*/
    }

    public void btnBackOnAction(ActionEvent event) throws IOException {
        AppointPanel.getChildren().clear();
        AppointPanel.getChildren().add(FXMLLoader.load(getClass().getResource("/view/registerStatus_form.fxml")));

    }

    public void cmbVehicleIdOnAction(ActionEvent event) {
       /* String id = cmbVehicleId.getValue();
//        CustomerModel customerModel = new CustomerModel();
        try {
            VehicleDto vehicleDt0 = VehicleModel.searchVehicle(id);
            lbl.setText(customerDto.getName());

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }*/
    }
    private void loadToVehicleID() {
        ObservableList<String> obList = FXCollections.observableArrayList();
        try {
            List<VehicleDto> VehicleDtos = VehicleModel.loadAllItems();

            for (VehicleDto dto : VehicleDtos) {
                obList.add(dto.getVehicle_no());
            }
            cmbVehicleId.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
