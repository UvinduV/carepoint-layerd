package lk.ijse.carepoint.dao.custom;

import lk.ijse.carepoint.dto.VehicleDto;

import java.sql.SQLException;
import java.util.List;

public interface VehicleDAO {
     List<VehicleDto> loadAllItems() throws SQLException ;

     List<VehicleDto> loadAllvehicles(String custID) throws SQLException ;

    boolean saveVehicle(VehicleDto dto) throws SQLException ;

}
