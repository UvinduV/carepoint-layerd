package lk.ijse.carepoint.dto;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CustomerDto {
    String cust_id;
    String name;
    String address;
    String tel;

}
