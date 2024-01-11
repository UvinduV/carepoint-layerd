package lk.ijse.carepoint.dao.custom;

import lk.ijse.carepoint.dto.ItemDto;
import lk.ijse.carepoint.dto.tm.cartTm;

import java.sql.SQLException;
import java.util.List;

public interface ItemDAO {
    boolean updateItem(List<cartTm> cartTmList) throws SQLException ;

    boolean saveItem(ItemDto dto) throws SQLException;

    ItemDto searchItem(String code) throws SQLException;

    boolean updateItem(ItemDto itemDto) throws SQLException ;

    List<ItemDto> loadAllItems() throws SQLException ;

    boolean deleteItem(String code) throws SQLException ;
}
