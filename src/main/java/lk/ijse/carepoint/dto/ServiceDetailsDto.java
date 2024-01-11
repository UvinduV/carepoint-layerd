package lk.ijse.carepoint.dto;

import lk.ijse.carepoint.dto.tm.cartTm;
import lombok.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ServiceDetailsDto {
    private String appointId;
    private Date date;
    private String customerId;
    private List<cartTm> cartTmList = new ArrayList<>();
    private String totalprice;
}
