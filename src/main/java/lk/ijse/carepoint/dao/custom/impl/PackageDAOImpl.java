package lk.ijse.carepoint.dao.custom.impl;

import lk.ijse.carepoint.dao.SqlUtil;
import lk.ijse.carepoint.dao.custom.PackageDAO;
import lk.ijse.carepoint.db.DbConnection;
import lk.ijse.carepoint.dto.PackageDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PackageDAOImpl implements PackageDAO {
    public List<PackageDto> getAll() throws SQLException, ClassNotFoundException {
        /*Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT * FROM package";
        PreparedStatement pstm = connection.prepareStatement(sql);*/
        ResultSet resultSet= SqlUtil.test("SELECT * FROM package");

        List<PackageDto> itemList = new ArrayList<>();

        //ResultSet resultSet = pstm.executeQuery();
        while (resultSet.next()) {
            itemList.add(new PackageDto(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getDouble(3)

            ));
        }

        return itemList;
    }

    public PackageDto search(String newValue) throws SQLException, ClassNotFoundException {
       /* Connection connection = DbConnection.getInstance().getConnection ();

        String sql = "SELECT * FROM package WHERE id = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, Pid);

        ResultSet resultSet = pstm.executeQuery();*/
        ResultSet resultSet =SqlUtil.test("SELECT * FROM package WHERE id = ?",newValue);

        PackageDto dto = null;

        if(resultSet.next()) {
            String id = resultSet.getString(1);
            String type= resultSet.getString(2);
            Double amount = Double.valueOf((resultSet.getString(3)));

            dto = new PackageDto(id, type, amount);
        }
        return dto;
    }
//////////

    @Override
    public boolean save(PackageDto dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(PackageDto dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public String generateNewID() throws SQLException, ClassNotFoundException {
        return null;
    }

}
