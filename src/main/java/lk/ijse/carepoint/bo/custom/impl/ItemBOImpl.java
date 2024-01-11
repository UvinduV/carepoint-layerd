package lk.ijse.carepoint.bo.custom.impl;

import lk.ijse.carepoint.bo.custom.ItemBO;
import lk.ijse.carepoint.dao.SqlUtil;
import lk.ijse.carepoint.dao.custom.ItemDAO;
import lk.ijse.carepoint.dao.custom.impl.ItemDAOImpl;
import lk.ijse.carepoint.dto.ItemDto;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemBOImpl implements ItemBO {
    private ItemDAO itemDAO=new ItemDAOImpl();
    @Override
    public List<ItemDto> getAllItem() throws SQLException, ClassNotFoundException {
        return itemDAO.getAll();
    }
    @Override
    public boolean deleteItem(String id) throws SQLException, ClassNotFoundException {
        return itemDAO.delete(id);
    }
    @Override
    public ItemDto searchItem(String newValue) throws SQLException, ClassNotFoundException {
       return itemDAO.search(newValue);
    }
    @Override
    public boolean saveItem(ItemDto dto) throws SQLException, ClassNotFoundException {
        return itemDAO.save(dto);
    }
    @Override
    public boolean updateItem(ItemDto dto) throws SQLException, ClassNotFoundException {
        return itemDAO.update(dto);
    }
}
