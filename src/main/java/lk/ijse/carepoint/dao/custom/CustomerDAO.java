package lk.ijse.carepoint.dao.custom;

import lk.ijse.carepoint.dto.CustomerDto;

import java.sql.SQLException;
import java.util.List;

public interface CustomerDAO {
    String generateNextOrderId() throws SQLException;
    String splitCustId(String currentCustId) ;

    List<CustomerDto> loadAllItems() throws SQLException;
    CustomerDto searchCustomerID(String Tel) throws SQLException ;

    CustomerDto searchCustomer(String custId) throws SQLException ;

    boolean saveCustomer(CustomerDto dto) throws SQLException ;
}
