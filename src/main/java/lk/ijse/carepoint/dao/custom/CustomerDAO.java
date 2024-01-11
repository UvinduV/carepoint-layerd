package lk.ijse.carepoint.dao.custom;

import lk.ijse.carepoint.dao.CrudDAO;
import lk.ijse.carepoint.dto.CustomerDto;

import java.sql.SQLException;
import java.util.List;

public interface CustomerDAO extends CrudDAO<CustomerDto> {
    String splitCustId(String currentCustId) ;

    CustomerDto searchCustomerID(String Tel) throws SQLException, ClassNotFoundException;


}
