package lk.ijse.carepoint.dao.custom;

import lk.ijse.carepoint.dao.CrudDAO;
import lk.ijse.carepoint.dto.SheduleDto;

import java.sql.*;
import java.util.List;

public interface SheduleDAO extends CrudDAO<SheduleDto> {

    String splitSheduleId(String currentSheduleId) ;

    List<SheduleDto> getAllShedule(Date dateIn) throws SQLException, ClassNotFoundException;
}
