package lk.ijse.carepoint.model;

import lk.ijse.carepoint.db.DbConnection;
import lk.ijse.carepoint.dto.ItemDto;
import lk.ijse.carepoint.dto.serviceAppointDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ServiceAppointModel {
    public static String generateNextAppointId() throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT appoint_id FROM appointment ORDER BY appoint_id DESC LIMIT 1";
        PreparedStatement pstm = connection.prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();
        if(resultSet.next()) {
            return splitAppointId(resultSet.getString(1));
        }
        return splitAppointId(null);
    }

    private static String splitAppointId(String currentAppointId) {
        if(currentAppointId != null) {
            String[] split = currentAppointId.split("A0");

            int id = Integer.parseInt(split[1]); //01
            id++;
            return "A00" + id;
        } else {
            return "A001";
        }
    }

    public static boolean saveAppoint(serviceAppointDto dto) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "INSERT INTO appointment VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1, dto.getAppoint_Id());
        pstm.setString(2, dto.getCust_Id());
        pstm.setString(3, dto.getVehicle_No());
        pstm.setString(4, dto.getShedule_Id());
        pstm.setString(5, dto.getPackage_Id());
        pstm.setString(6, dto.getPayemntId());
        pstm.setString(7, String.valueOf(dto.getDate()));
        pstm.setString(8, dto.getTime());
        pstm.setString(9, String.valueOf(dto.getAmount()));

        boolean isSaved = pstm.executeUpdate() > 0;

        return isSaved;
    }

    public List<serviceAppointDto> getAllAppointment() throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT * FROM appointment";
        PreparedStatement pstm = connection.prepareStatement(sql);
        ResultSet resultSet = pstm.executeQuery();

        ArrayList<serviceAppointDto> dtoList = new ArrayList<>();

        while(resultSet.next()) {
            dtoList.add(
                    new serviceAppointDto(
                            resultSet.getString(1),
                            resultSet.getString(2),
                            resultSet.getString(3),
                            resultSet.getString(4),
                            resultSet.getString(5),
                            resultSet.getString(6),
                            resultSet.getDate(7),
                            resultSet.getString(8),
                            resultSet.getDouble(9)
                    )
            );
        }
        return dtoList;
    }

    public serviceAppointDto searchAppointId(String appointId) throws SQLException {

        Connection connection = DbConnection.getInstance().getConnection();
        String sql = "SELECT * FROM appointment WHERE appoint_id = ?";

        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, appointId);

        ResultSet resultSet = pstm.executeQuery();

        serviceAppointDto dto = null;

        if(resultSet.next()) {
            dto = new serviceAppointDto(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    resultSet.getString(6),
                    resultSet.getDate(7),
                    resultSet.getString(8),
                    resultSet.getDouble(9)
            );
        }
        return dto;
    }

    public boolean deleteAppoint(String appointId) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "DELETE FROM appointment WHERE appoint_id = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1, appointId);

        return pstm.executeUpdate() > 0;
    }
}
