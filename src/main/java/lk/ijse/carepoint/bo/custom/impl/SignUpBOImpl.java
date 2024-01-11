package lk.ijse.carepoint.bo.custom.impl;

import lk.ijse.carepoint.bo.custom.SignUpBO;
import lk.ijse.carepoint.dao.SqlUtil;
import lk.ijse.carepoint.dao.custom.UserDAO;
import lk.ijse.carepoint.dao.custom.impl.UserDAOImpl;
import lk.ijse.carepoint.dto.UserDto;

import java.sql.SQLException;

public class SignUpBOImpl implements SignUpBO {
    UserDAO userDAO=new UserDAOImpl();
    @Override
    public boolean saveUser(UserDto dto) throws SQLException, ClassNotFoundException {
        return userDAO.save(dto);
    }
}
