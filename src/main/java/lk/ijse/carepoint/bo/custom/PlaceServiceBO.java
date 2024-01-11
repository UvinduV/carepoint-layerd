package lk.ijse.carepoint.bo.custom;

import lk.ijse.carepoint.bo.SuperBO;
import lk.ijse.carepoint.dto.CustomerDto;
import lk.ijse.carepoint.dto.ItemDto;
import lk.ijse.carepoint.dto.ServiceDetailsDto;
import lk.ijse.carepoint.dto.serviceAppointDto;

import java.sql.SQLException;
import java.util.List;

public interface PlaceServiceBO extends SuperBO {
    boolean placeOrder(ServiceDetailsDto placeOrderDto) throws SQLException, ClassNotFoundException;
    CustomerDto searchCustomer(String newValue) throws SQLException, ClassNotFoundException;
    List<ItemDto> getAllItem() throws SQLException, ClassNotFoundException;
    ItemDto searchItem(String newValue) throws SQLException, ClassNotFoundException;
    List<serviceAppointDto> getAllAppoint() throws SQLException, ClassNotFoundException;
    serviceAppointDto searchAppoint(String newValue) throws SQLException, ClassNotFoundException;
}
