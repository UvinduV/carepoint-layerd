package lk.ijse.carepoint.dto;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserDto {
    String username;
    String password;
    String first_name;
    String last_Name;
    String possition;
}
