package lk.ijse.carepoint.dao.custom.impl;

import lk.ijse.carepoint.dao.SqlUtil;
import lk.ijse.carepoint.dao.custom.UserDAO;
import lk.ijse.carepoint.db.DbConnection;
import lk.ijse.carepoint.dto.UserDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAOImpl implements UserDAO {
    @Override
    public boolean saveUser(UserDto dto) throws SQLException, ClassNotFoundException {
        /*Connection connection = DbConnection.getInstance().getConnection();

        String sql = "INSERT INTO user VALUES(?, ?, ?, ?, ?)";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1, dto.getUsername());
        pstm.setString(2, dto.getPassword());
        pstm.setString(3, dto.getFirst_name());
        pstm.setString(4, dto.getLast_Name());
        pstm.setString(5, dto.getPossition());

        boolean isSaved = pstm.executeUpdate() > 0;*/
        boolean isSaved= SqlUtil.test("INSERT INTO user VALUES(?, ?, ?, ?, ?)",dto);

        return isSaved;
    }

    @Override
    public UserDto getUser(String un, String pw) throws SQLException, ClassNotFoundException {
       /* Connection connection = DbConnection.getInstance().getConnection ();

        String sql = "SELECT * FROM user WHERE username = ? AND password = ? ";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, un);
        pstm.setString(2, pw);

        ResultSet resultSet = pstm.executeQuery();*/
        ResultSet resultSet=SqlUtil.test("SELECT * FROM user WHERE username = ? AND password = ? ",un,pw);

        UserDto dto = null;

        if(resultSet.next()) {
            String username = resultSet.getString(1);
            String password = resultSet.getString(2);
            String first_name = resultSet.getString(3);
            String last_Name = resultSet.getString(4);
            String possition = resultSet.getString(5);


            dto = new UserDto(username, password, first_name, last_Name, possition);
        }
        return dto;
    }
}
