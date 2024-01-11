package lk.ijse.carepoint.bo;

import lk.ijse.carepoint.dao.custom.*;
import lk.ijse.carepoint.dao.custom.impl.AppointmentDAOImpl;
import lk.ijse.carepoint.dao.custom.impl.ItemDAOImpl;
import lk.ijse.carepoint.dao.custom.impl.ServiceDetailDAOImpl;
import lk.ijse.carepoint.db.DbConnection;
import lk.ijse.carepoint.dto.PlaceServiceDetailsDto;

import java.sql.Connection;
import java.sql.SQLException;

public class placeServiceDetailsModel {
    private ServiceDetailDAO serviceDetailDAO=new ServiceDetailDAOImpl();
    private AppointmentDAO appointmentDAO=new AppointmentDAOImpl();
    private ItemDAO itemDAO=new ItemDAOImpl();
    public boolean placeOrder(PlaceServiceDetailsDto placeOrderDto) throws SQLException, ClassNotFoundException {
        String appointId = placeOrderDto.getAppointId();
        

        boolean isSuccess = false;
        Connection connection = DbConnection.getInstance().getConnection();
        connection.setAutoCommit(false);

        boolean isUpdated = itemDAO.updateItem(placeOrderDto.getCartTmList());
        if (isUpdated) {
            boolean isOrderDetailSaved = serviceDetailDAO.saveServiceDetails(placeOrderDto.getAppointId(), placeOrderDto.getTotalprice(), placeOrderDto.getCartTmList());
            if(isOrderDetailSaved) {
                boolean isSaved=appointmentDAO.deleteAppoint(appointId);
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
