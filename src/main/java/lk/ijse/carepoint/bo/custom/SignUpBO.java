package lk.ijse.carepoint.bo.custom;

import lk.ijse.carepoint.bo.SuperBO;
import lk.ijse.carepoint.dto.UserDto;

import java.sql.SQLException;

public interface SignUpBO extends SuperBO {
    boolean saveUser(UserDto dto) throws SQLException, ClassNotFoundException;
}
