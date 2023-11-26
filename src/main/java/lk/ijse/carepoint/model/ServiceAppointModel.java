package lk.ijse.carepoint.model;

import lk.ijse.carepoint.db.DbConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
}
