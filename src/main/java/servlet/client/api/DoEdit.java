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

@WebServlet(name = "DoEdit", urlPatterns = "/api/client/edit")
public class DoEdit extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String cno = request.getParameter("cno");
        String cname = request.getParameter("cname");
        String csex = request.getParameter("csex");
        String cage = request.getParameter("cage");
        String caddress = request.getParameter("caddress");
        String cphone = request.getParameter("cphone");
        String cremark = request.getParameter("cremark");

        response.setContentType("application/json; charset=utf-8");
        PrintWriter pw = response.getWriter();
        try {
            Connection cnn = db.dbutils.getConnection();
            String sql = "update client set cname=?, csex=?, cage=?, caddress=?, cphone=?, cremark=? where cno=?";
            PreparedStatement pstmt = cnn.prepareStatement(sql);
            pstmt.setObject(1, cname);
            pstmt.setObject(2, csex);
            pstmt.setObject(3,cage);
            pstmt.setObject(4,caddress);
            pstmt.setObject(5, cphone);
            pstmt.setObject(6, cremark);
            pstmt.setObject(7, cno);
            int rs = pstmt.executeUpdate();
            if (rs > 0) {
                // 修改成功
                pw.print("{\"code\": 0}");
            } else {
                pw.print("{\"code\": -1, \"msg\": \"修改信息失败\"}");
            }
            pw.close();
            db.dbutils.close(cnn, pstmt, null);
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
