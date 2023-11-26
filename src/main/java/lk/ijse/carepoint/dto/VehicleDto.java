package lk.ijse.carepoint.dto;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class VehicleDto {
    String vehicle_no;
    String cust_id;
    String type;
    String fuel_type;

}
