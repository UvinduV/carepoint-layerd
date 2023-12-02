package lk.ijse.carepoint.dto.tm;

import javafx.scene.control.Button;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class cartTm {
    private String code;
    private String description;
    private int qty;
    private double unitPrice;
    private double tot;
    private Button btn;
}
