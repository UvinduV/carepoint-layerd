package lk.ijse.carepoint.bo.custom.impl;

import lk.ijse.carepoint.bo.custom.PlaceServiceBO;
import lk.ijse.carepoint.dao.SqlUtil;
import lk.ijse.carepoint.dao.custom.*;
import lk.ijse.carepoint.dao.custom.impl.AppointmentDAOImpl;
import lk.ijse.carepoint.dao.custom.impl.CustomerDAOImpl;
import lk.ijse.carepoint.dao.custom.impl.ItemDAOImpl;
import lk.ijse.carepoint.dao.custom.impl.ServiceDetailDAOImpl;
import lk.ijse.carepoint.db.DbConnection;
import lk.ijse.carepoint.dto.CustomerDto;
import lk.ijse.carepoint.dto.ItemDto;
import lk.ijse.carepoint.dto.ServiceDetailsDto;
import lk.ijse.carepoint.dto.serviceAppointDto;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PlaceServiceBOImpl implements PlaceServiceBO {
    private ServiceDetailDAO serviceDetailDAO=new ServiceDetailDAOImpl();
    private AppointmentDAO appointmentDAO=new AppointmentDAOImpl();
    private ItemDAO itemDAO=new ItemDAOImpl();
    private CustomerDAO customerDAO=new CustomerDAOImpl();
    @Override
    public boolean placeOrder(ServiceDetailsDto placeOrderDto) throws SQLException, ClassNotFoundException {
        String appointId = placeOrderDto.getAppointId();
        

        boolean isSuccess = false;
        Connection connection = DbConnection.getInstance().getConnection();
        connection.setAutoCommit(false);

        boolean isUpdated = itemDAO.updateItem(placeOrderDto.getCartTmList());
        if (isUpdated) {
            boolean isOrderDetailSaved = serviceDetailDAO.saveServiceDetails(placeOrderDto.getAppointId(),placeOrderDto.getCustomerId(),placeOrderDto.getTotalprice(), placeOrderDto.getCartTmList());
            if(isOrderDetailSaved) {
                boolean isSaved=appointmentDAO.delete(appointId);
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
    @Override
    public CustomerDto searchCustomer(String newValue) throws SQLException, ClassNotFoundException {
        return customerDAO.search(newValue);
    }
    @Override
    public List<ItemDto> getAllItem() throws SQLException, ClassNotFoundException {
        return itemDAO.getAll();
    }
    @Override
    public ItemDto searchItem(String newValue) throws SQLException, ClassNotFoundException {
        return itemDAO.search(newValue);
    }
    @Override
    public List<serviceAppointDto> getAllAppoint() throws SQLException, ClassNotFoundException {
        return appointmentDAO.getAll();
    }
    @Override
    public serviceAppointDto searchAppoint(String newValue) throws SQLException, ClassNotFoundException {
        return appointmentDAO.search(newValue);
    }
}
