package lk.ijse.carepoint.controller;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.carepoint.bo.custom.ItemBO;
import lk.ijse.carepoint.bo.custom.impl.ItemBOImpl;
import lk.ijse.carepoint.dto.ItemDto;
import lk.ijse.carepoint.dto.tm.itemTm;
import lk.ijse.carepoint.dao.custom.ItemDAO;
import lk.ijse.carepoint.dao.custom.impl.ItemDAOImpl;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class itemFormController {
    @FXML
    private JFXButton btnBack;

    @FXML
    private JFXButton btnClear;

    @FXML
    private JFXButton btnSave;

    @FXML
    private JFXButton btnUpdate;

    @FXML
    private TableColumn<?, ?> colAction;

    @FXML
    private TableColumn<?, ?> colCode;

    @FXML
    private TableColumn<?, ?> colDescription;

    @FXML
    private TableColumn<?, ?> colQtyOnHand;

    @FXML
    private TableColumn<?, ?> colUnitPrice;

    @FXML
    private AnchorPane itemPanel;

    @FXML
    private TableView<itemTm> tblItem;

    @FXML
    private TextField txtCode;

    @FXML
    private TextField txtDescription;

    @FXML
    private TextField txtQtyOnHand;

    @FXML
    private TextField txtUnitPrice;

   // private ItemDAO itemDAO=new ItemDAOImpl();
    private ItemBO itemBO=new ItemBOImpl();

    public void initialize() {
        setCellValueFactory();
        loadAllItems();
        //tableListener();
    }


    private void setData(itemTm row) {
        txtCode.setText(row.getCode());
        txtDescription.setText(row.getDescription());
        txtUnitPrice.setText(String.valueOf(row.getUnitPrice()));
        txtQtyOnHand.setText(String.valueOf(row.getQtyOnHand()));
    }

    private void setCellValueFactory() {
        colCode.setCellValueFactory(new PropertyValueFactory<>("code"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        colQtyOnHand.setCellValueFactory(new PropertyValueFactory<>("qtyOnHand"));
        colAction.setCellValueFactory(new PropertyValueFactory<>("btn"));
    }

    private void loadAllItems() {
        ObservableList<itemTm> obList = FXCollections.observableArrayList();
        try {
            List<ItemDto> dtoList = itemBO.getAllItem();

            for (ItemDto dto : dtoList) {
                obList.add(new itemTm(
                        dto.getCode(),
                        dto.getDescription(),
                        dto.getUnitPrice(),
                        dto.getQtyOnHand(),

                        new Button("Delete")
                ));
            }
            tblItem.setItems(obList);
            setRemoveBtnAction(obList.get(0).getBtn());

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    private void tableListener() {
        tblItem.getSelectionModel().selectedItemProperty().addListener((observable, oldValued, newValue) -> {
//            System.out.println(newValue);
            //setData(newValue);
        });

    }
    private void setRemoveBtnAction(Button btn) {
        btn.setOnAction((e) -> {
            ButtonType yes = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
            ButtonType no = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);

            Optional<ButtonType> type = new Alert(Alert.AlertType.INFORMATION, "Are you sure to remove?", yes, no).showAndWait();

            if (type.orElse(no) == yes) {
                int focusedIndex = tblItem.getSelectionModel().getSelectedIndex();
                String code = tblItem.getItems().get(focusedIndex).getCode();
                try {
                    boolean isDeleted = itemBO.deleteItem(code);
                    if (isDeleted) {
                        tblItem.getItems().remove(focusedIndex);
                        tblItem.refresh();
                        new Alert(Alert.AlertType.CONFIRMATION, "customer deleted!").show();
                    } else {
                        new Alert(Alert.AlertType.CONFIRMATION, "customer not deleted!").show();
                    }
                }
                catch (SQLException ex) {
                    new Alert(Alert.AlertType.ERROR, ex.getMessage()).show();
                } catch (ClassNotFoundException ex) {
                    throw new RuntimeException(ex);
                }

            }
        });
    }


    public void codeSearchOnAction(ActionEvent event) {
        String code = txtCode.getText();

        try {
            ItemDto dto = itemBO.searchItem(code);
            if (dto != null) {
                setFields(dto);
            } else {
                new Alert(Alert.AlertType.INFORMATION, "item not found!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    public void btnSaveOnAction(ActionEvent event) {
        String code = txtCode.getText();
        String description = txtDescription.getText();
        double unitPrice = Double.parseDouble(txtUnitPrice.getText());
        int qtyOnHand = Integer.parseInt(txtQtyOnHand.getText());

        var dto = new ItemDto(code, description, unitPrice, qtyOnHand);

//        var model = new ItemModel();
        try {
            boolean isSaved = itemBO.saveItem(dto);
            if (isSaved) {
                loadAllItems();
                tblItem.refresh();
                new Alert(Alert.AlertType.CONFIRMATION, "item saved!").show();
                clearFields();
            }
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    private void clearFields() {
        txtCode.clear();
        txtDescription.clear();
        txtUnitPrice.clear();
        txtQtyOnHand.clear();
    }

    public void btnUpdateOnAction(ActionEvent event) {
        String code = txtCode.getText();
        String description = txtDescription.getText();
        double unitPrice = Double.parseDouble(txtUnitPrice.getText());
        int qtyOnHand = Integer.parseInt(txtQtyOnHand.getText());

//        var model = new ItemModel();
        try {
            boolean isUpdated = itemBO.updateItem(new ItemDto(code, description, unitPrice, qtyOnHand));
            if (isUpdated) {
                new Alert(Alert.AlertType.CONFIRMATION, "item updated").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    public void btnClearOnAction(ActionEvent event) {
        clearFields();
    }
    private void setFields(ItemDto dto) {
        txtCode.setText(dto.getCode());
        txtDescription.setText(dto.getDescription());
        txtUnitPrice.setText(String.valueOf(dto.getUnitPrice()));
        txtQtyOnHand.setText(String.valueOf(dto.getQtyOnHand()));
    }

    public void btnBackOnAction(ActionEvent event) throws IOException {
        itemPanel.getChildren().clear();
        itemPanel.getChildren().add(FXMLLoader.load(getClass().getResource("/view/dashboard_form.fxml")));


    }
}
