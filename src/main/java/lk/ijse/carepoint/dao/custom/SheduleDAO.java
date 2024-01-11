package lk.ijse.carepoint.dao.custom;

import lk.ijse.carepoint.dto.SheduleDto;

import java.sql.*;
import java.util.List;

public interface SheduleDAO {
    boolean saveShedule(SheduleDto dto) throws SQLException, ClassNotFoundException;

    String generateNextScheduleId() throws SQLException, ClassNotFoundException;

    String splitSheduleId(String currentSheduleId) ;

    SheduleDto searchSheduleID(String sheduleId) throws SQLException, ClassNotFoundException;

    List<SheduleDto> getAllShedule(Date dateIn) throws SQLException, ClassNotFoundException;
}
