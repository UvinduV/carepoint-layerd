package lk.ijse.carepoint.dao.custom;

import lk.ijse.carepoint.dto.serviceAppointDto;

import java.sql.SQLException;
import java.util.List;

public interface AppointmentDAO {
    String generateNextAppointId() throws SQLException, ClassNotFoundException;

    String splitAppointId(String currentAppointId);

    boolean saveAppoint(serviceAppointDto dto) throws SQLException, ClassNotFoundException;

    List<serviceAppointDto> getAllAppointment() throws SQLException, ClassNotFoundException;

    serviceAppointDto searchAppointId(String appointId) throws SQLException, ClassNotFoundException;

    public boolean deleteAppoint(String appointId) throws SQLException, ClassNotFoundException;
}
