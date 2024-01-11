package lk.ijse.carepoint.dao.custom;

import lk.ijse.carepoint.dto.CustomerDto;

import java.sql.SQLException;
import java.util.List;

public interface CustomerDAO {
    String generateNextOrderId() throws SQLException, ClassNotFoundException;
    String splitCustId(String currentCustId) ;

    List<CustomerDto> loadAllItems() throws SQLException, ClassNotFoundException;
    CustomerDto searchCustomerID(String Tel) throws SQLException, ClassNotFoundException;

    CustomerDto searchCustomer(String custId) throws SQLException, ClassNotFoundException;

    boolean saveCustomer(CustomerDto dto) throws SQLException, ClassNotFoundException;
}
