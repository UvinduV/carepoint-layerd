package lk.ijse.carepoint.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.carepoint.dto.serviceAppointDto;
import lk.ijse.carepoint.dto.tm.appointmentTm;
import lk.ijse.carepoint.model.ServiceAppointModel;

import java.sql.SQLException;
import java.util.List;

public class waitingAppointFormController {
    @FXML
    private AnchorPane AddServiceRecodsPanel;

    @FXML
    private TableColumn<?, ?> colAction;

    @FXML
    private TableColumn<?, ?> colAppointId;

    @FXML
    private TableColumn<?, ?> colCustId;

    @FXML
    private TableColumn<?, ?> colDate;

    @FXML
    private TableColumn<?, ?> colVehicleId;

    @FXML
    private TableColumn<?, ?> coleTime;

    @FXML
    private TableView<appointmentTm> tblAppoint;

    @FXML
    private AnchorPane waitingAppointPanel;
    private ServiceAppointModel serviceAppointModel = new ServiceAppointModel();

    public void initialize() {
        setCellValueFactory();
        loadAllAppointment();
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




}
