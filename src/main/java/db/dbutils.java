package db;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class dbutils {
    private static ComboPooledDataSource ds = new ComboPooledDataSource("mysql");

    public static DataSource getDataSource() {
        return ds;
    }

    public static Connection getConnection() throws SQLException {
        return ds.getConnection();
    }

    public static void close(Connection cnn, PreparedStatement pstmt, ResultSet rs) throws SQLException {
        if (rs != null) {
            rs.close();
            rs = null;
        }
        if (pstmt != null) {
            pstmt.close();
            pstmt = null;
        }
        if(cnn != null) {
            cnn.close();
            cnn = null;
        }
    }

}
