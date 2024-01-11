package lk.ijse.carepoint.dao.custom.impl;

import lk.ijse.carepoint.dao.SqlUtil;
import lk.ijse.carepoint.dao.custom.CustomerDAO;
import lk.ijse.carepoint.db.DbConnection;
import lk.ijse.carepoint.dto.CustomerDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAOImpl implements CustomerDAO {
    @Override
    public  String generateNewID() throws SQLException, ClassNotFoundException {

        ResultSet resultSet = SqlUtil.test("SELECT cust_id FROM customer ORDER BY cust_id DESC LIMIT 1");
        if(resultSet.next()) {
            return splitCustId(resultSet.getString(1));
        }
        return splitCustId(null);
    }

   @Override
    public String splitCustId(String currentCustId) {
       // private String splitOrderId(String currentOrderId) {
            if(currentCustId != null) {
                String[] split = currentCustId.split("C0");

                int id = Integer.parseInt(split[1]); //01
                id++;
                return "C00" + id;
            } else {
                return "C001";
            }

    }

    @Override
    public  List<CustomerDto>  getAll() throws SQLException, ClassNotFoundException {
        ResultSet resultSet= SqlUtil.test("SELECT * FROM customer");

        List<CustomerDto> itemList = new ArrayList<>();

        //ResultSet resultSet = pstm.executeQuery();
        while (resultSet.next()) {
            itemList.add(new CustomerDto(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4)
            ));
        }

        return itemList;
    }

    @Override
    public  CustomerDto searchCustomerID(String Tel) throws SQLException, ClassNotFoundException {
        ResultSet resultSet=SqlUtil.test("SELECT * FROM customer WHERE tel = ?",Tel);
        resultSet.next();
        CustomerDto dto = null;

        if(resultSet.next()) {
            String cust_id = resultSet.getString(1);
            String name = resultSet.getString(2);
            String address = resultSet.getString(3);
            String tel = resultSet.getString(4);

            dto = new CustomerDto(cust_id, name, address, tel);
        }
        return dto;
    }

    @Override
    public CustomerDto search(String newValue) throws SQLException, ClassNotFoundException {
        /*Connection connection = DbConnection.getInstance().getConnection ();

        String sql = "SELECT * FROM customer WHERE cust_id = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, custId);*/

       // ResultSet resultSet = pstm.executeQuery();
        ResultSet resultSet=SqlUtil.test("SELECT * FROM customer WHERE cust_id = ?",newValue);
        resultSet.next();
        CustomerDto dto = null;

        if(resultSet.next()) {
            String cust_id = resultSet.getString(1);
            String name = resultSet.getString(2);
            String address = resultSet.getString(3);
            String tel = resultSet.getString(4);

            dto = new CustomerDto(cust_id, name, address, tel);
        }
        return dto;
    }

    @Override
    public boolean save(CustomerDto dto) throws SQLException, ClassNotFoundException {
        /*Connection connection = DbConnection.getInstance().getConnection();

        String sql = "INSERT INTO customer VALUES(?, ?, ?, ?)";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1, dto.getCust_id());
        pstm.setString(2, dto.getName());
        pstm.setString(3, dto.getAddress());
        pstm.setString(4, dto.getTel());

        boolean isSaved = pstm.executeUpdate() > 0;*/
        boolean isSaved=SqlUtil.test("INSERT INTO customer VALUES(?, ?, ?, ?)",dto.getCust_id(),dto.getName(),dto.getAddress(),dto.getTel());

        return isSaved;
    }
///////////////////

    @Override
    public boolean update(CustomerDto dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }
}
