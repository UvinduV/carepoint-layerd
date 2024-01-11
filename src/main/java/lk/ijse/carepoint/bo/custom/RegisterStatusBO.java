package lk.ijse.carepoint.bo.custom;

import lk.ijse.carepoint.bo.SuperBO;
import lk.ijse.carepoint.dto.CustomerDto;
import lk.ijse.carepoint.dto.VehicleDto;

import java.sql.SQLException;
import java.util.List;

public interface RegisterStatusBO extends SuperBO {
    String generateCustID() throws SQLException, ClassNotFoundException;
    List<CustomerDto> getAllCutomer() throws SQLException, ClassNotFoundException;
    boolean saveCustomer(CustomerDto dto) throws SQLException, ClassNotFoundException;
    boolean saveVehicle(VehicleDto dto) throws SQLException, ClassNotFoundException;
}
