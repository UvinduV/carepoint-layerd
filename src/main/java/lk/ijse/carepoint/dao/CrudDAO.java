package lk.ijse.carepoint.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface CrudDAO <T>{
    List<T> getAll() throws SQLException, ClassNotFoundException ;
    boolean save(T dto) throws SQLException, ClassNotFoundException;
    boolean update(T dto) throws SQLException, ClassNotFoundException;
    boolean delete(String id) throws SQLException, ClassNotFoundException;
    String generateNewID() throws SQLException, ClassNotFoundException;
    T search(String newValue) throws SQLException, ClassNotFoundException;
}
