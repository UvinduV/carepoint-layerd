package lk.ijse.carepoint.model;

import lk.ijse.carepoint.db.DbConnection;
import lk.ijse.carepoint.dto.PlaceServiceDetailsDto;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;

public class PlaceServiceDetailsModel {
    public static boolean placeOrder(PlaceServiceDetailsDto placeOrderDto) throws SQLException {
        String orderId = placeOrderDto.getAppointId();
        String customerId = placeOrderDto.getCustomerId();
        Date date = placeOrderDto.getDate();
        String totalPrice = placeOrderDto.getTotalprice();

        Connection connection = null;
        /*try {
            connection = DbConnection.getInstance().getConnection();
            connection.setAutoCommit(false);


                boolean isUpdated = ItemModel.updateItem(placeOrderDto.getCartTmList());
                if(isUpdated) {
                    boolean isOrderDetailSaved = ServiceRecodModel.saveServiceDetails(placeOrderDto.getAppointId(), placeOrderDto.getTotalprice(), placeOrderDto.getCartTmList());
                    if (isOrderDetailSaved) {
                        connection.commit();
                   }
                }
            }
        } catch (SQLException e) {
           // connection.rollback();
        } finally {
            connection.setAutoCommit(true);
        }*/

        return true;
    }
}
