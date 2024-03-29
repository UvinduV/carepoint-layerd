package lk.ijse.carepoint.dto;

import lombok.*;

import java.sql.Date;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SheduleDto {
    String shedule_Id;
    Date date;
    String avaliability;
    String description;
}
