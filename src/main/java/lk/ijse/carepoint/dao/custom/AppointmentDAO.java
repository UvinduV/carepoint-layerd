package lk.ijse.carepoint.dao.custom;

import lk.ijse.carepoint.dao.CrudDAO;
import lk.ijse.carepoint.dto.serviceAppointDto;

import java.sql.SQLException;
import java.util.List;

public interface AppointmentDAO extends CrudDAO<serviceAppointDto> {
    String splitAppointId(String currentAppointId);

}
