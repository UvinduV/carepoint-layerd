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
import lk.ijse.carepoint.bo.BOFactory;
import lk.ijse.carepoint.bo.custom.SheduleBO;
import lk.ijse.carepoint.bo.custom.impl.SheduleBOImpl;
import lk.ijse.carepoint.dto.SheduleDto;
import lk.ijse.carepoint.dto.tm.scheduleTm;
import lk.ijse.carepoint.dao.custom.SheduleDAO;
import lk.ijse.carepoint.dao.custom.impl.SheduleDAOImpl;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class sheduleFormController {
    @FXML
    private Label lblScheduleIDIcon;
    @FXML
    private JFXButton F01Slot1Time;

    @FXML
    private JFXButton F01Slot9time;

    @FXML
    private JFXButton F02Slot1Time;

    @FXML
    private JFXButton F02Slot9Time;

    @FXML
    private JFXButton H01Slot10Time;

    @FXML
    private JFXButton H01Slot11Time;

    @FXML
    private JFXButton H01Slot9Time;

    @FXML
    private JFXButton H02Slot10Time;

    @FXML
    private JFXButton H02Slot11Time;

    @FXML
    private JFXButton H02Slot9Time;

    @FXML
    private TableColumn<?, ?> colAction;

    @FXML
    private TableColumn<?, ?> colDate;

    @FXML
    private TableColumn<?, ?> colIScheduleId;

    @FXML
    private TableColumn<?, ?> colTimeSlot;

    @FXML
    private TableColumn<?, ?> cotScheduleType;

    @FXML
    private Label lblF1Time1;

    @FXML
    private Label lblF1Time9;

    @FXML
    private Label lblF2Time1;

    @FXML
    private Label lblF2Time9;

    @FXML
    private Label lblH1Time10;

    @FXML
    private Label lblH1Time11;

    @FXML
    private Label lblH1Time9;

    @FXML
    private Label lblH2Time10;

    @FXML
    private Label lblH2Time11;

    @FXML
    private Label lblH2Time9;

    @FXML
    private DatePicker scheduleDate;

    @FXML
    private AnchorPane schedulePanel;

    @FXML
    private TableView<scheduleTm> tblOrderCart;

    //private SheduleDAO sheduleDAO=new SheduleDAOImpl();
    private SheduleBO sheduleBO= (SheduleBO) BOFactory.getBoFactory().getBo(BOFactory.BOTypes.SHEDULE);
    private ObservableList<scheduleTm> obListShedule = FXCollections.observableArrayList();
    public void initialize() {
        setCellValueFactory();
    }

    private void setCellValueFactory() {
        colIScheduleId.setCellValueFactory(new PropertyValueFactory<>("shedule_Id"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colTimeSlot.setCellValueFactory(new PropertyValueFactory<>("avaliability"));
        cotScheduleType.setCellValueFactory(new PropertyValueFactory<>("description"));

       // colAction.setCellValueFactory(new PropertyValueFactory<>("btn"));

    }
    public void generateNextScheduleId() {
            try {
                String sheduleID = sheduleBO.generateSheduelID();
                lblScheduleIDIcon.setText(sheduleID);
                System.out.println(sheduleID);
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }


    }

    public void schedueleTable(String time,String avil){
        generateNextScheduleId();
        String shedule_Id = lblScheduleIDIcon.getText();
        Date date = Date.valueOf(scheduleDate.getValue());
        String avalibility = time;
        String description = avil;

        if (!obListShedule.isEmpty()) {
            for (int i = 0; i < tblOrderCart.getItems().size(); i++) {
                if (colIScheduleId.getCellData(i).equals(shedule_Id)) {


                    tblOrderCart.refresh();
                    return;
                }
            }
        }

        var scheduleTm = new scheduleTm(shedule_Id, date, avalibility, description);

        obListShedule.add(scheduleTm);

        tblOrderCart.setItems(obListShedule);
        
        
        if (!obListShedule.isEmpty()) {
            var dto = new SheduleDto(shedule_Id, date, avalibility, description);

            try {
                boolean isSaved = sheduleBO.saveSlot(dto);

                //boolean ispassed = serviceAppointFrom.passSheduleID(shedule_Id); //passSheduleID

                if (isSaved) {
                    
                    new Alert(Alert.AlertType.CONFIRMATION, "time slot recived sucessfully!").show();
                    clearFields();
                   // nvigateToAppoint();
                    //scheduleDate.refresh();
                }
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }

        }


    }

    private void nvigateToAppoint() throws IOException {
        schedulePanel.getChildren().clear();
        schedulePanel.getChildren().add(FXMLLoader.load(getClass().getResource("/view/serviceAppoint_form.fxml")));

    }

    private void clearFields() {
        lblScheduleIDIcon.setText("");
        scheduleDate.setValue(null);
    }

    public void F01Slot9timeOnAction(ActionEvent event) {

        F01Slot9time.setOnAction((e) -> {
            ButtonType yes = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
            ButtonType no = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);

            Optional<ButtonType> type = new Alert(Alert.AlertType.INFORMATION, "Are you recived this time slot?", yes, no).showAndWait();

            if (type.orElse(no) == yes) {
                schedueleTable("9-12 AM","F01 Fill");
            }
        });

        //schedueleTable("9-12 AM","F01 Fill");



    }

    public void F01Slot1TimeOnAction(ActionEvent event) {


        F01Slot1Time.setOnAction((e) -> {
            ButtonType yes = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
            ButtonType no = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);

            Optional<ButtonType> type = new Alert(Alert.AlertType.INFORMATION, "Are you recived this time slot?", yes, no).showAndWait();

            if (type.orElse(no) == yes) {
               schedueleTable("1-4 PM","F01 Fill");


            }
        });
    }

    public void F02Slot9TimeOnAction(ActionEvent event) {

        F02Slot9Time.setOnAction((e) -> {
            ButtonType yes = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
            ButtonType no = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);

            Optional<ButtonType> type = new Alert(Alert.AlertType.INFORMATION, "Are you recived this time slot?", yes, no).showAndWait();

            if (type.orElse(no) == yes) {
                schedueleTable("9-12 AM","F02 Fill");
            }
        });
    }

    public void F02Slot1TimeOnAction(ActionEvent event) {

        F02Slot1Time.setOnAction((e) -> {
            ButtonType yes = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
            ButtonType no = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);

            Optional<ButtonType> type = new Alert(Alert.AlertType.INFORMATION, "Are you recived this time slot?", yes, no).showAndWait();

            if (type.orElse(no) == yes) {
                schedueleTable("1-4 PM","F02 Fill");

            }
        });
    }

    public void H01Slot9TimeOnAction(ActionEvent event) {
       // generateNextAppointId();
        H01Slot9Time.setOnAction((e) -> {
            ButtonType yes = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
            ButtonType no = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);

            Optional<ButtonType> type = new Alert(Alert.AlertType.INFORMATION, "Are you recived this time slot?", yes, no).showAndWait();

            if (type.orElse(no) == yes) {
                schedueleTable("9-10 AM","H01 Fill");

            }
        });
    }

    public void H01Slot10TimeOnAction(ActionEvent event) {
        //generateNextAppointId();
        H01Slot10Time.setOnAction((e) -> {
            ButtonType yes = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
            ButtonType no = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);

            Optional<ButtonType> type = new Alert(Alert.AlertType.INFORMATION, "Are you recived this time slot?", yes, no).showAndWait();

            if (type.orElse(no) == yes) {
                schedueleTable("10-11 AM","H01 Fill");

            }
        });
    }

    public void H01Slot11TimeOnAction(ActionEvent event) {
        //generateNextAppointId();
        H01Slot11Time.setOnAction((e) -> {
            ButtonType yes = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
            ButtonType no = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);

            Optional<ButtonType> type = new Alert(Alert.AlertType.INFORMATION, "Are you recived this time slot?", yes, no).showAndWait();

            if (type.orElse(no) == yes) {
                schedueleTable("11-12 AM","H01 Fill");
            }
        });
    }

    public void H02Slot9TimeOnAction(ActionEvent event) {
       // generateNextAppointId();
        H02Slot9Time.setOnAction((e) -> {
            ButtonType yes = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
            ButtonType no = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);

            Optional<ButtonType> type = new Alert(Alert.AlertType.INFORMATION, "Are you recived this time slot?", yes, no).showAndWait();

            if (type.orElse(no) == yes) {
                schedueleTable("1-2 PM","H02 Fill");
            }
        });
    }

    public void H02Slot10TimeOnAction(ActionEvent event) {
        //generateNextAppointId();
        H02Slot10Time.setOnAction((e) -> {
            ButtonType yes = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
            ButtonType no = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);

            Optional<ButtonType> type = new Alert(Alert.AlertType.INFORMATION, "Are you recived this time slot?", yes, no).showAndWait();

            if (type.orElse(no) == yes) {
                schedueleTable("2-3 PM","H02 Fill");

            }
        });
    }

    public void H02Slot11TimeOnAction(ActionEvent event) {
       // generateNextAppointId();
        H02Slot11Time.setOnAction((e) -> {
            ButtonType yes = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
            ButtonType no = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);

            Optional<ButtonType> type = new Alert(Alert.AlertType.INFORMATION, "Are you recived this time slot?", yes, no).showAndWait();

            if (type.orElse(no) == yes) {
                schedueleTable("3-4 PM","H02 Fill");
            }
        });
    }

    public void scheduleDateOnAction(ActionEvent event) {
            Date date = Date.valueOf(scheduleDate.getValue());

            ObservableList<scheduleTm> obList = FXCollections.observableArrayList();

            try {
                System.out.println(date);
                List<SheduleDto> dtoList = sheduleBO.getAllShedule(date);

                for (SheduleDto dto : dtoList) {
                    obList.add(
                            new scheduleTm(
                                    dto.getShedule_Id(),
                                    dto.getDate(),
                                    dto.getAvaliability(),
                                    dto.getDescription()
                            )
                    );
                }

                tblOrderCart.setItems(obList);
                //tblOrderCart.refresh();
            } catch (SQLException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }


    }
}
