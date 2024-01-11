package lk.ijse.carepoint.model;

import lk.ijse.carepoint.db.DbConnection;
import lk.ijse.carepoint.dto.VehicleDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface VehicleDAO {
     List<VehicleDto> loadAllItems() throws SQLException ;

     List<VehicleDto> loadAllvehicles(String custID) throws SQLException ;

    boolean saveVehicle(VehicleDto dto) throws SQLException ;

}
