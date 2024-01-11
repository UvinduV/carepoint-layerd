package lk.ijse.carepoint.controller;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.carepoint.dto.*;
import lk.ijse.carepoint.dto.tm.appointmentTm;
import lk.ijse.carepoint.dto.tm.cartTm;
import lk.ijse.carepoint.model.*;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class placeServiceFormController {
    @FXML
    private Label lblcustEmail;
    @FXML
    private AnchorPane AddServiceRecodsPanel;

    @FXML
    private JFXButton btnAddCart;

    @FXML
    private JFXButton btnServiceComplete;

    @FXML
    private ComboBox<String> cmbItemCode;

    @FXML
    private TableColumn<?, ?> colAction;

    @FXML
    private TableColumn<?, ?> colAppointId;

    @FXML
    private TableColumn<?, ?> colCode;

    @FXML
    private TableColumn<?, ?> colCustId;

    @FXML
    private TableColumn<?, ?> colDate;

    @FXML
    private TableColumn<?, ?> colDesc;

    @FXML
    private TableColumn<?, ?> colPrice;

    @FXML
    private TableColumn<?, ?> colQty;

    @FXML
    private TableColumn<?, ?> colTotal;

    @FXML
    private TableColumn<?, ?> colVehicleId;

    @FXML
    private TableColumn<?, ?> coleTime;

    @FXML
    private Label lblAmount;

    @FXML
    private Label lblAppointDate;

    @FXML
    private Label lblAppointTime;

    @FXML
    private Label lblCustID;

    @FXML
    private Label lblCustName;

    @FXML
    private Label lblDesc;

    @FXML
    private Label lblNetTotal;

    @FXML
    private Label lblQtyOnHand;

    @FXML
    private Label lblUnitPrice;

    @FXML
    private TableView<appointmentTm> tblAppoint;

    @FXML
    private TableView<cartTm> tblOderCart;

    @FXML
    private TextField txtApointId;

    @FXML
    private TextField txtQty;

    @FXML
    private AnchorPane waitingAppointPanel;
    private ServiceAppointModel serviceAppointModel = new ServiceAppointModel();

    private ServiceDetailsModel serviceDetailsModel=new ServiceDetailsModel();
    private ItemModel itemModel= new ItemModel();

    private CustomerDAO customerDAO=new CustomerDAOImpl();

    private ObservableList<cartTm> obList = FXCollections.observableArrayList();

    public void initialize() {
        setCellValueFactory();
        setCellValueFactoryCart();
        loadAllAppointment();
        loadItemCodes();

    }

    private void setCellValueFactory() {
        colAppointId.setCellValueFactory(new PropertyValueFactory<>("appoint_Id"));
        colCustId.setCellValueFactory(new PropertyValueFactory<>("cust_Id"));
        colVehicleId.setCellValueFactory(new PropertyValueFactory<>("vehicle_No"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        coleTime.setCellValueFactory(new PropertyValueFactory<>("time"));
    }

    private void loadAllAppointment() {
        var model = new ServiceAppointModel();

        ObservableList<appointmentTm> obList = FXCollections.observableArrayList();

        try {
            List<serviceAppointDto> dtoList = model.getAllAppointment();

            for (serviceAppointDto dto : dtoList) {
                obList.add(

                        new appointmentTm(
                                dto.getAppoint_Id(),
                                dto.getCust_Id(),
                                dto.getVehicle_No(),
                                dto.getShedule_Id(),
                                dto.getPackage_Id(),
                                dto.getDate(),
                                dto.getTime(),
                                dto.getAmount()
                        )
                );
            }

            tblAppoint.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    //////////////////////service Details Adding ////////////////////////////


    public void txtAppointIdOnAction(ActionEvent event) {
        String appoint_Id = txtApointId.getText();

        System.out.println("appoint");

        try {
            serviceAppointDto dto = serviceAppointModel.searchAppointId(appoint_Id);
            if (dto != null) {
                txtApointId.setText(dto.getAppoint_Id());
                lblCustID.setText(dto.getCust_Id());
                //lblCustName.setText(dto.getCust_Name());
                lblAppointDate.setText(String.valueOf(dto.getDate()));
                lblAppointTime.setText(dto.getTime());
                lblAmount.setText(String.valueOf(dto.getAmount()));

                String id = lblCustID.getText();
                getCustName(id);
            } else {
                new Alert(Alert.AlertType.INFORMATION, "customer not found").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }
    private void setCellValueFactoryCart() {
        colCode.setCellValueFactory(new PropertyValueFactory<>("code"));
        colDesc.setCellValueFactory(new PropertyValueFactory<>("description"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        colTotal.setCellValueFactory(new PropertyValueFactory<>("tot"));
        colAction.setCellValueFactory(new PropertyValueFactory<>("btn"));
    }

    private String getCustName(String id) {
        String custId = id;
        try {
            CustomerDto customerDto = customerDAO.searchCustomer(custId);
//            System.out.println(customerDto);
            if (customerDto != null) {
                lblCustName.setText(customerDto.getName());
            } else {
               // new Alert(Alert.AlertType.INFORMATION, "customer not found").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

        return null;

    }
    private void loadItemCodes() {
        ObservableList<String> obList = FXCollections.observableArrayList();
        try {
            List<ItemDto> itemDtos = itemModel.loadAllItems();

            for (ItemDto dto : itemDtos) {
                obList.add(dto.getCode());
            }
            cmbItemCode.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void btnAddCartOnAction(ActionEvent event) {
        String code = cmbItemCode.getValue();
        String description = lblDesc.getText();
        int qty = Integer.parseInt(txtQty.getText());
        double unitPrice = Double.parseDouble(lblUnitPrice.getText());
        double tot = unitPrice * qty;
        Button btn = new Button("Remove");

        setRemoveBtnAction(btn);
        btn.setCursor(Cursor.HAND);

        if (!obList.isEmpty()) {
            for (int i = 0; i < tblOderCart.getItems().size(); i++) {
                if (colCode.getCellData(i).equals(code)) {
                    int col_qty = (int) colQty.getCellData(i);
                    qty += col_qty;
                    tot = unitPrice * qty;

                    obList.get(i).setQty(qty);
                    obList.get(i).setTot(tot);

                    calculateTotal();
                    tblOderCart.refresh();
                    return;
                }
            }
        }
        var cartTm = new cartTm(code, description, qty, unitPrice, tot, btn);

        obList.add(cartTm);

        tblOderCart.setItems(obList);
        calculateTotal();
        txtQty.clear();
    }

    private void setRemoveBtnAction(Button btn) {
        btn.setOnAction((e) -> {
            ButtonType yes = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
            ButtonType no = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);

            Optional<ButtonType> type = new Alert(Alert.AlertType.INFORMATION, "Are you sure to remove?", yes, no).showAndWait();

            if (type.orElse(no) == yes) {
                int focusedIndex = tblOderCart.getSelectionModel().getSelectedIndex();

                obList.remove(focusedIndex);
                tblOderCart.refresh();
                calculateTotal();
            }
        });
    }

    private void calculateTotal() {
            double total = 0;
            for (int i = 0; i < tblOderCart.getItems().size(); i++) {
                total += (double) colTotal.getCellData(i);



            }
            double amount= Double.valueOf(lblAmount.getText());
            double netTotal = (amount + total);

            lblNetTotal.setText(String.valueOf(netTotal));

    }

    public void cmbItemCodeOnAction(ActionEvent event) {
        String code = cmbItemCode.getValue();

        txtQty.requestFocus();
        try {
            ItemDto dto = itemModel.searchItem(code);
            lblDesc.setText(dto.getDescription());
            lblUnitPrice.setText(String.valueOf(dto.getUnitPrice()));
            lblQtyOnHand.setText(String.valueOf(dto.getQtyOnHand()));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void btnServiceCompleteOnAction(ActionEvent event) {
        String appointId = txtApointId.getText();
        Date date = Date.valueOf(lblAppointDate.getText());
        String customerId = lblCustID.getText();
        String totalprice= lblNetTotal.getText();
        int qty = 0;
        String itemCode = cmbItemCode.getValue();

        List<cartTm> cartTmList = new ArrayList<>();
        for (int i = 0; i < tblOderCart.getItems().size(); i++) {
            cartTm CartTm = tblOderCart.getSelectionModel().getSelectedItem();

            cartTmList.add(CartTm);
        }

        var placeOrderDto = new PlaceServiceDetailsDto(appointId, date, customerId, cartTmList, totalprice);
        boolean isSuccess = false;
        try {
            isSuccess = serviceDetailsModel.placeOrder(placeOrderDto);
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

        if (isSuccess) {
            loadAllAppointment();
            tblAppoint.refresh();

            new Alert(Alert.AlertType.CONFIRMATION, "service complete sucessfully!").show();
            emailSend(customerId);
            clearFields();
        }

    }

    private void emailSend(String cust_Id) {
        ///////
        //CustomerModel.searchCustomer(cust_Id);
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
        mail.setMsg( "Dear customer, your vehicleservice is complete. " +"\n"
                + "Total amount: " + lblNetTotal.getText() +"\n"
                + "Thank you.!" +"\n"
                + "\n"
                + "CarePoint. Tel: 0123456789");
        mail.setTo(email);
        mail.setSubject("Subject");

        Thread thread = new Thread(mail);
        thread.start();
        ///////
    }

    private void clearFields() {
        txtApointId.clear();
        lblAppointDate.setText("");
        lblCustID.setText("");
        lblCustName.setText("");
        lblDesc.setText("");
        lblNetTotal.setText("");
        lblUnitPrice.setText("");
        lblQtyOnHand.setText("");
    }
}
