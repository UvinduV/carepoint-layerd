package lk.ijse.carepoint.dto;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class serviceRecodDto {
    private String appointId;
    private String customerId;
    private String totalprice;
    private int qty;
    private String itemCode;

}
