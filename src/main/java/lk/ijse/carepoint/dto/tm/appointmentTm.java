package lk.ijse.carepoint.dto.tm;

import javafx.scene.control.Button;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class appointmentTm {
    private String vehicleNo;
    private String packageId;
    private String type;
    private Double unitPrice;
    private Double total;
    private Button btn;

}
