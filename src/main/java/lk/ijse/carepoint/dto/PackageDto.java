package lk.ijse.carepoint.dto;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PackageDto {
    String id;
    String type;
    Double amount;
}
