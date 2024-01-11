package lk.ijse.carepoint.dao.custom;

import lk.ijse.carepoint.dao.CrudDAO;
import lk.ijse.carepoint.dto.ItemDto;
import lk.ijse.carepoint.dto.tm.cartTm;

import java.sql.SQLException;
import java.util.List;

public interface ItemDAO extends CrudDAO<ItemDto> {
    boolean updateItem(List<cartTm> cartTmList) throws SQLException, ClassNotFoundException;

}
