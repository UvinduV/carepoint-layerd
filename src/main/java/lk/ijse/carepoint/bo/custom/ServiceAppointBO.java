package lk.ijse.carepoint.bo.custom;

import lk.ijse.carepoint.dto.*;

import java.sql.SQLException;
import java.util.List;

public interface ServiceAppointBO {
    CustomerDto searchCustomer(String newValue) throws SQLException, ClassNotFoundException;
    CustomerDto searchCustomerID(String Tel) throws SQLException, ClassNotFoundException;
    List<VehicleDto> getAllVehicle() throws SQLException, ClassNotFoundException;
    SheduleDto searchSheduleID(String newValue) throws SQLException, ClassNotFoundException;
    PackageDto searchPackage(String newValue) throws SQLException, ClassNotFoundException;
    List<PackageDto> getAllPackage() throws SQLException, ClassNotFoundException;
    String generateAppointID() throws SQLException, ClassNotFoundException;
    boolean saveAppointment(serviceAppointDto dto) throws SQLException, ClassNotFoundException;
}
