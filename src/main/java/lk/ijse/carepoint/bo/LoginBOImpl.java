package lk.ijse.carepoint.bo;

import lk.ijse.carepoint.bo.custom.LoginBO;
import lk.ijse.carepoint.dao.SqlUtil;
import lk.ijse.carepoint.dao.custom.UserDAO;
import lk.ijse.carepoint.dao.custom.impl.UserDAOImpl;
import lk.ijse.carepoint.dto.UserDto;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginBOImpl implements LoginBO {
    UserDAO userDAO=new UserDAOImpl();
    @Override
    public UserDto getUser(String un, String pw) throws SQLException, ClassNotFoundException {
        return userDAO.getUser(un,pw);
    }
}
