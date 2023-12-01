package lk.ijse.carepoint.dto.tm;

import lombok.*;

import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class scheduleTm {
    String shedule_Id;
    Date date;
    String avaliability;
    String description;

}
