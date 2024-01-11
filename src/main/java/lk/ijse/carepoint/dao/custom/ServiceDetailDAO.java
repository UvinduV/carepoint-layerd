package lk.ijse.carepoint.dao.custom;

import lk.ijse.carepoint.dto.tm.cartTm;

import java.sql.SQLException;
import java.util.List;

public interface ServiceDetailDAO {
    boolean saveServiceDetails(String appointId, String totalprice, List<cartTm> cartTmList) throws SQLException ;
    boolean saveServiceDetails(String appointId, String totalprice, cartTm tm) throws SQLException ;
}
