package lk.ijse.carepoint.dao.custom.impl;

import lk.ijse.carepoint.dao.SqlUtil;
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
    public List<VehicleDto> getAll() throws SQLException, ClassNotFoundException {
        /*Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT * FROM vehicle";
        PreparedStatement pstm = connection.prepareStatement(sql);*/
        ResultSet resultSet=SqlUtil.test("SELECT * FROM vehicle");
        List<VehicleDto> itemList = new ArrayList<>();
       // ResultSet resultSet = pstm.executeQuery();
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
    public  List<VehicleDto> loadAllvehicles(String custID) throws SQLException, ClassNotFoundException {

        ResultSet resultSet =SqlUtil.test("SELECT * FROM vehicle WHERE cust_id = ?",custID);
        List<VehicleDto> itemList = new ArrayList<>();
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
    public boolean save(VehicleDto dto) throws SQLException, ClassNotFoundException {
        /*Connection connection = DbConnection.getInstance().getConnection();

        String sql = "INSERT INTO vehicle VALUES(?, ?, ?, ?)";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1, dto.getVehicle_no());
        pstm.setString(2, dto.getCust_id());
        pstm.setString(3, dto.getType());
        pstm.setString(4, dto.getFuel_type());*/

        //boolean isSaved = pstm.executeUpdate() > 0;
        boolean isSaved= SqlUtil.test("INSERT INTO vehicle VALUES(?, ?, ?, ?)",dto.getVehicle_no(),dto.getCust_id(),dto.getType(),dto.getFuel_type());

        return isSaved;
    }
////////////

    @Override
    public boolean update(VehicleDto dto) throws SQLException, ClassNotFoundException {
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

    @Override
    public VehicleDto search(String newValue) throws SQLException, ClassNotFoundException {
        return null;
    }
}

