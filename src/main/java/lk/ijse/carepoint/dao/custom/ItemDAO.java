package lk.ijse.carepoint.dao.custom;

import lk.ijse.carepoint.dto.ItemDto;
import lk.ijse.carepoint.dto.tm.cartTm;

import java.sql.SQLException;
import java.util.List;

public interface ItemDAO {
    boolean updateItem(List<cartTm> cartTmList) throws SQLException, ClassNotFoundException;

    boolean saveItem(ItemDto dto) throws SQLException, ClassNotFoundException;

    ItemDto searchItem(String code) throws SQLException, ClassNotFoundException;

    boolean updateItem(ItemDto itemDto) throws SQLException, ClassNotFoundException;

    List<ItemDto> loadAllItems() throws SQLException, ClassNotFoundException;

    boolean deleteItem(String code) throws SQLException, ClassNotFoundException;
}
