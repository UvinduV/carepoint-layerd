package lk.ijse.carepoint.dao.custom.impl;

import lk.ijse.carepoint.dao.custom.ServiceDetailDAO;
import lk.ijse.carepoint.db.DbConnection;
import lk.ijse.carepoint.dto.tm.cartTm;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class ServiceDetailDAOImpl implements ServiceDetailDAO {

    @Override
    public boolean saveServiceDetails(String appointId, String totalprice, List<cartTm> cartTmList) throws SQLException {
        for(cartTm tm : cartTmList) {
           if( !saveServiceDetails( appointId,  totalprice,  tm)){
               return false;
           }

        }
        return true;
    }
    @Override
    public boolean saveServiceDetails(String appointId, String totalprice, cartTm tm) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "INSERT INTO service_details VALUES(?, ?, ?, ?)";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1, appointId);
        pstm.setString(2, tm.getCode());
        pstm.setInt(3, tm.getQty());
        pstm.setDouble(4, Double.parseDouble(totalprice));

        return pstm.executeUpdate() > 0;
    }


}
