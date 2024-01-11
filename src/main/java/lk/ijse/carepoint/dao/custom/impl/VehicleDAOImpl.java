package lk.ijse.carepoint.dao.custom.impl;

import lk.ijse.carepoint.dao.custom.VehicleDAO;
import lk.ijse.carepoint.db.DbConnection;
import lk.ijse.carepoint.dto.VehicleDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VehicleDAOImpl implements VehicleDAO {

    @Override
    public List<VehicleDto> loadAllItems() throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT * FROM vehicle";
        PreparedStatement pstm = connection.prepareStatement(sql);

        List<VehicleDto> itemList = new ArrayList<>();

        ResultSet resultSet = pstm.executeQuery();
        while (resultSet.next()) {
            itemList.add(new VehicleDto(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4)
            ));
        }

        return itemList;

    }

    @Override
    public  List<VehicleDto> loadAllvehicles(String custID) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT * FROM vehicle WHERE cust_id = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, custID);
        List<VehicleDto> itemList = new ArrayList<>();

        ResultSet resultSet = pstm.executeQuery();
        while (resultSet.next()) {
            itemList.add(new VehicleDto(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4)
            ));
        }

        return itemList;
    }

    @Override
    public boolean saveVehicle(VehicleDto dto) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "INSERT INTO vehicle VALUES(?, ?, ?, ?)";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1, dto.getVehicle_no());
        pstm.setString(2, dto.getCust_id());
        pstm.setString(3, dto.getType());
        pstm.setString(4, dto.getFuel_type());

        boolean isSaved = pstm.executeUpdate() > 0;

        return isSaved;
    }

}

