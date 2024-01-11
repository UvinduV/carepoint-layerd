package lk.ijse.carepoint.dao.custom;

import lk.ijse.carepoint.dto.PackageDto;

import java.sql.SQLException;
import java.util.List;

public interface PackageDAO {
    List<PackageDto> loadAllItems() throws SQLException ;

    PackageDto searchPackage(String Pid) throws SQLException ;
}
