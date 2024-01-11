package lk.ijse.carepoint.dao.custom;

import lk.ijse.carepoint.dto.VehicleDto;

import java.sql.SQLException;
import java.util.List;

public interface VehicleDAO {
     List<VehicleDto> loadAllItems() throws SQLException, ClassNotFoundException;

     List<VehicleDto> loadAllvehicles(String custID) throws SQLException, ClassNotFoundException;

    boolean saveVehicle(VehicleDto dto) throws SQLException, ClassNotFoundException;

}
