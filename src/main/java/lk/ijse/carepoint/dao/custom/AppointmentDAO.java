package lk.ijse.carepoint.dao.custom;

import lk.ijse.carepoint.dto.serviceAppointDto;

import java.sql.SQLException;
import java.util.List;

public interface AppointmentDAO {
    String generateNextAppointId() throws SQLException ;

    String splitAppointId(String currentAppointId);

    boolean saveAppoint(serviceAppointDto dto) throws SQLException ;

    List<serviceAppointDto> getAllAppointment() throws SQLException ;

    serviceAppointDto searchAppointId(String appointId) throws SQLException ;

    public boolean deleteAppoint(String appointId) throws SQLException ;
}
