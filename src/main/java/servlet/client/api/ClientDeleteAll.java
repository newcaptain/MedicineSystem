package servlet.client.api;

import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.beans.PropertyVetoException;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet(name = "ClientDeleteAll", urlPatterns = "/api/client/deleteAll")
public class ClientDeleteAll extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        PrintWriter pw = response.getWriter();
        // 获取要删除的数组
        String[] cnos = request.getParameterValues("cnos[]");
        try {
            // 执行SQL
            Connection cnn = db.dbutils.getConnection();
            String sql = "delete from client where cno=?";
            PreparedStatement pstmt = cnn.prepareStatement(sql);
            for (int i=0; i<cnos.length; i++) {
                pstmt.setObject(1, cnos[i]);
                pstmt.addBatch();
            }
            pstmt.executeBatch();
            // 正常响应
            pw.print("{\"code\": 0}");
            pw.close();

            db.dbutils.close(cnn, pstmt, null);
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
            pw.print("{\"code\": -1, \"msg\": \"删除顾客失败\"}");
            pw.close();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
