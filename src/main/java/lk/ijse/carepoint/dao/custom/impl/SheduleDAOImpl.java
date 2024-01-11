package lk.ijse.carepoint.dao.custom.impl;

import lk.ijse.carepoint.dao.custom.SheduleDAO;
import lk.ijse.carepoint.db.DbConnection;
import lk.ijse.carepoint.dto.SheduleDto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SheduleDAOImpl implements SheduleDAO {
    @Override
    public boolean saveShedule(SheduleDto dto) throws SQLException {
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
    @Override
    public String generateNextScheduleId() throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT shedule_id FROM shedule ORDER BY shedule_id DESC LIMIT 1";
        PreparedStatement pstm = connection.prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();
        if(resultSet.next()) {
            return splitSheduleId(resultSet.getString(1));
        }
        return splitSheduleId(null);
    }
    @Override
    public String splitSheduleId(String currentSheduleId) {
        if(currentSheduleId != null) {
            String[] split = currentSheduleId.split("T0");

            int id = Integer.parseInt(split[1]); //01
            id++;
            return "T0" + id;
        } else {
            return "T001";
        }
    }
    @Override
    public SheduleDto searchSheduleID(String sheduleId) throws SQLException {
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
    @Override
    public List<SheduleDto> getAllShedule(Date dateIn) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT * FROM shedule where date(Sh_date) = ? order by Sh_date";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setDate(1, dateIn);
        ResultSet resultSet = pstm.executeQuery();


        ArrayList<SheduleDto> dtoList = new ArrayList<>();

        while(resultSet.next()) {
            dtoList.add(
                    new SheduleDto(
                            resultSet.getString(1),
                            resultSet.getDate(2),
                            resultSet.getString(3),
                            resultSet.getString(4)
                    )
            );
        }
        return dtoList;
    }



}