package lk.ijse.carepoint.model;

import lk.ijse.carepoint.db.DbConnection;
import lk.ijse.carepoint.dto.CustomerDto;
import lk.ijse.carepoint.dto.SheduleDto;

import java.sql.*;

public class SheduleModel {



    public static boolean saveShedule(SheduleDto dto) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "INSERT INTO shedule VALUES(?, ?, ?, ?)";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1, dto.getShedule_Id());
        pstm.setDate(2, dto.getDate());
        pstm.setString(3, dto.getAvaliability());
        pstm.setString(4, dto.getDescription());

        boolean isSaved = pstm.executeUpdate() > 0;

        return isSaved;

    }

    public static String generateNextScheduleId() throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT shedule_id FROM shedule ORDER BY shedule_id DESC LIMIT 1";
        PreparedStatement pstm = connection.prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();
        if(resultSet.next()) {
            return splitSheduleId(resultSet.getString(1));
        }
        return splitSheduleId(null);
    }

    private static String splitSheduleId(String currentSheduleId) {
        if(currentSheduleId != null) {
            String[] split = currentSheduleId.split("T0");

            int id = Integer.parseInt(split[1]); //01
            id++;
            return "T0" + id;
        } else {
            return "T001";
        }
    }

    public static SheduleDto searchSheduleID(String sheduleId) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection ();

        String sql = "SELECT * FROM shedule WHERE shedule_id = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, sheduleId);

        ResultSet resultSet = pstm.executeQuery();

        SheduleDto dto = null;

        if(resultSet.next()) {
            String shedule_id = resultSet.getString(1);
            Date date = Date.valueOf(resultSet.getString(2));
            String avaliability = resultSet.getString(3);
            String description = resultSet.getString(4);

            dto = new SheduleDto(shedule_id, date, avaliability, description);
        }
        return dto;
    }
}
