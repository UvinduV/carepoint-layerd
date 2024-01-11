package lk.ijse.carepoint.bo.custom;

import lk.ijse.carepoint.bo.SuperBO;
import lk.ijse.carepoint.dto.SheduleDto;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

public interface SheduleBO extends SuperBO {
    String generateSheduelID() throws SQLException, ClassNotFoundException;
    boolean saveSlot(SheduleDto dto) throws SQLException, ClassNotFoundException;
    List<SheduleDto> getAllShedule(Date dateIn) throws SQLException, ClassNotFoundException;
}
