package servlet.client.api;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.beans.PropertyVetoException;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@WebServlet(name = "ClientAdd", urlPatterns = "/api/client/add")
public class ClientAdd extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String cname = request.getParameter("cname");
        String csex = request.getParameter("csex");
        String cage = request.getParameter("cage");
        String caddress = request.getParameter("caddress");
        String cphone = request.getParameter("cphone");
        String cremark = request.getParameter("cremark");

        response.setContentType("application/json;charset=utf-8");
        PrintWriter pw = response.getWriter();
        try {
            Connection cnn = db.dbutils.getConnection();
            String sql = "insert into client(cname,csex,cage,caddress,cphone,cremark) value(?,?,?,?,?,?);";
            PreparedStatement pstmt = cnn.prepareStatement(sql);
            pstmt.setString(1, cname);
            pstmt.setString(2, csex);
            pstmt.setString(3, cage);
            pstmt.setString(4, caddress);
            pstmt.setString(5, cphone);
            pstmt.setString(6, cremark);
            int rs = pstmt.executeUpdate();
            if (rs >= 1) {
                // 添加用户成功
                pw.print("{\"code\": 0}");
            } else {
                // 插入失败
                pw.print("{\"code\": -1, \"msg\": \"添加用户失败\"}");
            }
            pw.close();
            db.dbutils.close(cnn, pstmt, null);
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
