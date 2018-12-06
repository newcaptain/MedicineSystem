package db;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: dbquery
 * @Description: 数据库操作类
 * @Author: newCaptain
 * @Date: 2018-12-05 19:48
 **/
public class dbquery {
    private Connection cnn = null;
    private PreparedStatement pstmt = null;
    private ResultSet rst = null;

    /**
     * 从连接池中获取连接
     */
    public Connection getConnection() {
        try {
            cnn = dbutils.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cnn;
    }

    /**
     * 查找一条记录
     */
    public Map findOne(String sql, Object arg) {
        try {
            pstmt = getConnection().prepareStatement(sql);
            pstmt.setObject(1, arg);
            rst = pstmt.executeQuery();
            ResultSetMetaData rsmd = rst.getMetaData();
            int colNums = rsmd.getColumnCount();
            rst.next();

            HashMap map = new HashMap(colNums);
            for (int i=1; i<=colNums; i++) {
                map.put(rsmd.getColumnName(i), rst.getObject(i));
            }
            return map;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            closeAll();
        }
    }

    /**
     * 查询表中所有记录
     */
    public List findAll(String sql, Object... args) {
        try {
            pstmt = getConnection().prepareStatement(sql);
            for (int i=0; i<args.length; i++) {
                pstmt.setObject(i+1, args[i]);
            }
            rst = pstmt.executeQuery();
            ResultSetMetaData rsmd = rst.getMetaData();
            int colNums = rsmd.getColumnCount();
            List list = new ArrayList();
            while (rst.next()) {
                Map map = new HashMap(colNums);
                for (int i=1; i<=colNums; i++) {
                    map.put(rsmd.getColumnName(i), rst.getObject(i));
                }
                list.add(map);
            }
            return list;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            closeAll();
        }
    }

    /**
     * 查找一页多条记录, 针对分页的查询
     */
    public List findOnePage(String sql, int skip, int limit) {
        List list = new ArrayList();
        try {
            pstmt = getConnection().prepareStatement(sql);
            rst = pstmt.executeQuery();
            rst.absolute(skip);
            ResultSetMetaData rsmd = rst.getMetaData();
            int colNums = rsmd.getColumnCount();
            int counter = 0;
            while (rst.next()) {
                HashMap map = new HashMap(colNums);
                for (int i=1; i<=colNums; i++) {
                    map.put(rsmd.getColumnName(i), rst.getObject(i));
                }
                list.add(map);
                counter ++;
                if (counter >= limit) {
                    break;
                }
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
            return list;
        } finally {
            closeAll();
        }
    }

    /**
     * 获取记录条数
     */
    public int queryRows(String sql) {
        try {
            pstmt = getConnection().prepareStatement(sql);
            rst = pstmt.executeQuery();
            rst.next();
            return rst.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }
        return 0;
    }

    /**
     * 更新和插入操作
     */
    public int updateQuery(String sql, Object... args) {
        try {
            pstmt = getConnection().prepareStatement(sql);
            for (int i=0; i<args.length; i++) {
                pstmt.setObject(i+1, args[i]);
            }
            return pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        } finally {
            closeAll();
        }
    }

    /**
     * 删除操作
     */
    public int delete(String sql, Object... args) {
        try {
            pstmt = getConnection().prepareStatement(sql);
            for (int i=0; i<args.length; i++) {
                pstmt.setObject(1, args[i]);
                pstmt.addBatch();
            }
            pstmt.executeBatch();
            return 1;
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        } finally {
            closeAll();
        }
    }

    /**
     * 关闭连接
     */
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
