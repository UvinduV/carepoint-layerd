package lk.ijse.carepoint.dao.custom.impl;

import lk.ijse.carepoint.dao.SqlUtil;
import lk.ijse.carepoint.dao.custom.ItemDAO;
import lk.ijse.carepoint.db.DbConnection;
import lk.ijse.carepoint.dto.ItemDto;
import lk.ijse.carepoint.dto.tm.cartTm;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemDAOImpl implements ItemDAO {
    @Override
    public boolean updateItem(List<cartTm> cartTmList) throws SQLException, ClassNotFoundException {
        /*Connection connection = DbConnection.getInstance().getConnection();

        String sql = "UPDATE item SET description = ?, unit_price = ?, qty_on_hand = ? WHERE item_id = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);

       *//* pstm.setString(1, ItemDto.getDescription());
        pstm.setDouble(2, ItemDto.getUnitPrice());
        pstm.setInt(3, ItemDto.getQtyOnHand());
        pstm.setString(4, ItemDto.getCode());*//*
        return pstm.executeUpdate() > 0;*/
        return SqlUtil.test("UPDATE item SET description = ?, unit_price = ?, qty_on_hand = ? WHERE item_id = ?");

    }
    @Override
    public boolean saveItem(ItemDto dto) throws SQLException, ClassNotFoundException {
        return SqlUtil.test("INSERT INTO item VALUES(?, ?, ?, ?)",dto.getCode(),dto.getDescription(),dto.getUnitPrice(),dto.getQtyOnHand());
    }
    @Override
    public ItemDto searchItem(String code) throws SQLException, ClassNotFoundException {
        /*Connection connection = DbConnection.getInstance().getConnection();
        String sql = "SELECT * FROM item WHERE item_id = ?";

        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, code);

        ResultSet resultSet = pstm.executeQuery();*/
        ResultSet resultSet=SqlUtil.test("SELECT * FROM item WHERE item_id = ?",code);

        ItemDto dto = null;

        if(resultSet.next()) {
            dto = new ItemDto(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getDouble(3),
                    resultSet.getInt(4)
            );
        }
        return dto;
    }
    @Override
    public boolean updateItem(ItemDto dto) throws SQLException, ClassNotFoundException {

        return SqlUtil.test("UPDATE item SET description = ?, unit_price = ?, qty_on_hand = ? WHERE item_id = ?",dto.getDescription(),dto.getUnitPrice(),dto.getQtyOnHand(),dto.getCode());

    }
    @Override
    public List<ItemDto> loadAllItems() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SqlUtil.test("SELECT * FROM item");
        List<ItemDto> itemList = new ArrayList<>();

        while (resultSet.next()) {
            itemList.add(new ItemDto(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getDouble(3),
                    resultSet.getInt(4)
            ));
        }

        return itemList;
    }
    @Override
    public boolean deleteItem(String code) throws SQLException, ClassNotFoundException {
        /*Connection connection = DbConnection.getInstance().getConnection();

        String sql = "DELETE FROM item WHERE item_id = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1, code);

        return pstm.executeUpdate() > 0;*/
        return SqlUtil.test("DELETE FROM item WHERE item_id = ?",code);
    }
}
