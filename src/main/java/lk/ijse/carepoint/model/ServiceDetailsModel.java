package lk.ijse.carepoint.model;

import lk.ijse.carepoint.db.DbConnection;
import lk.ijse.carepoint.dto.PlaceServiceDetailsDto;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;

public class ServiceDetailsModel {
    public static boolean placeOrder(PlaceServiceDetailsDto placeOrderDto) throws SQLException {
        String appointId = placeOrderDto.getAppointId();

        ServiceAppointModel serviceAppointModel=new ServiceAppointModel();
        ItemModel itemModel=new ItemModel();
        ServiceRecodModel serviceRecodModel=new ServiceRecodModel();

        boolean isSuccess = false;
        Connection connection = DbConnection.getInstance().getConnection();
        connection.setAutoCommit(false);

        boolean isUpdated = itemModel.updateItem(placeOrderDto.getCartTmList());
        if (isUpdated) {
            boolean isOrderDetailSaved = serviceRecodModel.saveServiceDetails(placeOrderDto.getAppointId(), placeOrderDto.getTotalprice(), placeOrderDto.getCartTmList());
            if(isOrderDetailSaved) {
                boolean isSaved=serviceAppointModel.deleteAppoint(appointId);
                if (isSaved) {
                    connection.commit();
                    isSuccess = true;
                    connection.setAutoCommit(true);
                }
            }
        }
        connection.commit();
        connection.setAutoCommit(true);
        return isSuccess;
    }
}
