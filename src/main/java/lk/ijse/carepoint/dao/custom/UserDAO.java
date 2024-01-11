package lk.ijse.carepoint.dao.custom;

import lk.ijse.carepoint.dao.CrudDAO;
import lk.ijse.carepoint.dto.UserDto;

import java.sql.SQLException;

public interface UserDAO extends CrudDAO<UserDto> {
    boolean save(UserDto dto) throws SQLException, ClassNotFoundException;

    UserDto getUser(String un, String pw) throws SQLException, ClassNotFoundException;
}
