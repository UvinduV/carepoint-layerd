package lk.ijse.carepoint.dao.custom;

import lk.ijse.carepoint.dao.CrudDAO;
import lk.ijse.carepoint.dto.tm.cartTm;

import java.sql.SQLException;
import java.util.List;

public interface ServiceDetailDAO extends CrudDAO<ServiceDetailDAO> {
    boolean saveServiceDetails(String appointId, String custID,String totalprice, List<cartTm> cartTmList) throws SQLException, ClassNotFoundException;
    boolean saveServiceDetails(String appointId,String custID, String totalprice, cartTm tm) throws SQLException, ClassNotFoundException;
}
