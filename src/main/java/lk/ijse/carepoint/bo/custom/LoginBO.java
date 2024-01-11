package lk.ijse.carepoint.bo.custom;

import lk.ijse.carepoint.dto.UserDto;

import java.sql.SQLException;

public interface LoginBO {
    UserDto getUser(String un, String pw) throws SQLException, ClassNotFoundException;
}
