package lk.ijse.carepoint.dto.tm;

import javafx.scene.control.Button;
import lombok.*;

import java.sql.Date;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class appointmentTm {
    String appoint_Id;
    String cust_Id;
    String vehicle_No;
    String shedule_Id;
    String package_Id;
    Date date;
    String time;
    Double amount;


    //Button btnDelete;

}
