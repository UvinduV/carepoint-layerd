package lk.ijse.carepoint.dao.custom.impl;

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
    public List<PackageDto> loadAllItems() throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT * FROM package";
        PreparedStatement pstm = connection.prepareStatement(sql);

        List<PackageDto> itemList = new ArrayList<>();

        ResultSet resultSet = pstm.executeQuery();
        while (resultSet.next()) {
            itemList.add(new PackageDto(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getDouble(3)

            ));
        }

        return itemList;
    }

    public PackageDto searchPackage(String Pid) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection ();

        String sql = "SELECT * FROM package WHERE id = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, Pid);

        ResultSet resultSet = pstm.executeQuery();

        PackageDto dto = null;

        if(resultSet.next()) {
            String id = resultSet.getString(1);
            String type= resultSet.getString(2);
            Double amount = Double.valueOf((resultSet.getString(3)));

            dto = new PackageDto(id, type, amount);
        }
        return dto;
    }
}
