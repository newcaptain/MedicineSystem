package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class dbquery {

    private Connection cnn = null;
    private PreparedStatement pstmt = null;
    private ResultSet rst = null;

    public Connection getConnection() {
        try {
            cnn = dbutils.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cnn;
    }

    public int insert(String sql, Object... args) {
        try {
            pstmt = getConnection().prepareStatement(sql);
            for (int i = 0; i < args.length; i++) {
                pstmt.setObject(i + 1, args[i]);
            }
            return pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        } finally {
            closeAll();
        }
    }

    public int delete(String sql, Object... args) {
        try {
            pstmt = getConnection().prepareStatement(sql);
            for (int i=0; i<args.length; i++) {
                pstmt.setObject(1, args[i]);
                pstmt.addBatch();
            }
            pstmt.executeBatch();
            return 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        } finally {
            closeAll();
        }
    }

    public void closeAll() {
        try {
            if (rst != null) {
                rst.close();
                rst = null;
            }
            if (pstmt != null) {
                pstmt.close();
                pstmt = null;
            }
            if (cnn != null) {
                cnn.close();
                cnn = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
