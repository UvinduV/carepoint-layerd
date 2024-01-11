package lk.ijse.carepoint.dao.custom.impl;

import lk.ijse.carepoint.dao.SqlUtil;
import lk.ijse.carepoint.dao.custom.ServiceDetailDAO;
import lk.ijse.carepoint.db.DbConnection;
import lk.ijse.carepoint.dto.tm.cartTm;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class ServiceDetailDAOImpl implements ServiceDetailDAO {

    @Override
    public boolean saveServiceDetails(String appointId, String custID,String totalprice, List<cartTm> cartTmList) throws SQLException, ClassNotFoundException {
        for(cartTm tm : cartTmList) {
           if( !saveServiceDetails( appointId, custID, totalprice,  tm)){
               return false;
           }

        }
        return true;
    }
    @Override
    public boolean saveServiceDetails(String appointId,String custID, String totalprice, cartTm tm) throws SQLException, ClassNotFoundException {
        /*Connection connection = DbConnection.getInstance().getConnection();

        String sql = "INSERT INTO service_details VALUES(?, ?, ?, ?)";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1, appointId);
        pstm.setString(2, tm.getCode());
        pstm.setInt(3, tm.getQty());
        pstm.setDouble(4, Double.parseDouble(totalprice));

        return pstm.executeUpdate() > 0;*/
        return SqlUtil.test("INSERT INTO service_details VALUES(?, ?, ?, ?)",appointId,tm.getCode(),tm.getQty(),Double.parseDouble(totalprice));
    }


    @Override
    public List<ServiceDetailDAO> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean save(ServiceDetailDAO dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(ServiceDetailDAO dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public String generateNewID() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public ServiceDetailDAO search(String newValue) throws SQLException, ClassNotFoundException {
        return null;
    }
}
