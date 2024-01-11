package lk.ijse.carepoint.bo.custom;

import lk.ijse.carepoint.bo.SuperBO;
import lk.ijse.carepoint.dto.ItemDto;

import java.sql.SQLException;
import java.util.List;

public interface ItemBO extends SuperBO {
    List<ItemDto> getAllItem() throws SQLException, ClassNotFoundException;
    boolean deleteItem(String id) throws SQLException, ClassNotFoundException;
    ItemDto searchItem(String newValue) throws SQLException, ClassNotFoundException;
    boolean saveItem(ItemDto dto) throws SQLException, ClassNotFoundException;
    boolean updateItem(ItemDto dto) throws SQLException, ClassNotFoundException;
}
