package lk.ijse.carepoint.dao.custom;

import lk.ijse.carepoint.dto.SheduleDto;

import java.sql.*;
import java.util.List;

public interface SheduleDAO {
    boolean saveShedule(SheduleDto dto) throws SQLException;

    String generateNextScheduleId() throws SQLException ;

    String splitSheduleId(String currentSheduleId) ;

    SheduleDto searchSheduleID(String sheduleId) throws SQLException;

    List<SheduleDto> getAllShedule(Date dateIn) throws SQLException ;
}
