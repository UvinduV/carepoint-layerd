package lk.ijse.carepoint.model;

import lk.ijse.carepoint.db.DbConnection;
import lk.ijse.carepoint.dto.CustomerDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface CustomerDAO {
    String generateNextOrderId() throws SQLException;
    String splitCustId(String currentCustId) ;

    List<CustomerDto> loadAllItems() throws SQLException;
    CustomerDto searchCustomerID(String Tel) throws SQLException ;

    CustomerDto searchCustomer(String custId) throws SQLException ;

    boolean saveCustomer(CustomerDto dto) throws SQLException ;
}
