package lk.ijse.carepoint.dao.custom;

import lk.ijse.carepoint.dao.CrudDAO;
import lk.ijse.carepoint.dto.VehicleDto;

import java.sql.SQLException;
import java.util.List;

public interface VehicleDAO extends CrudDAO<VehicleDto> {

     List<VehicleDto> loadAllvehicles(String custID) throws SQLException, ClassNotFoundException;



}
