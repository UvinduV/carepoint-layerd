package lk.ijse.carepoint.dao;

import lk.ijse.carepoint.db.DbConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SqlUtil {
    public static <T>T test(String sql,Object... args) throws SQLException, ClassNotFoundException {
        Connection connection = DbConnection.getInstance().getConnection();
        //Connection connection = DBConnection.getDbConnection().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);

        for (int i = 0; i < args.length; i++) {
            pstm.setObject(i+1,args[i]);
        }
        if (sql.startsWith("SELECT")|| sql.startsWith("select")) {
            return (T)pstm.executeQuery();
        }else {
            return(T)(Boolean)(pstm.executeUpdate()>0);
        }
    }
}
