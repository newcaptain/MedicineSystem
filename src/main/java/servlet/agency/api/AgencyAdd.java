package servlet.agency.api;

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

@WebServlet(name = "AgencyAdd", urlPatterns = "/api/agency/add")
public class AgencyAdd extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String aname = request.getParameter("aname");
        String asex = request.getParameter("asex");
        String aphone = request.getParameter("aphone");
        String aremark = request.getParameter("aremark");

        response.setContentType("application/json;charset=uft-8");
        PrintWriter pw = response.getWriter();
        try {
            Connection cnn = db.dbutils.getConnection();
            String sql = "insert into agency(aname, asex, aphone, aremark) value(?, ?, ?, ?);";
            PreparedStatement pstmt = cnn.prepareStatement(sql);
            pstmt.setObject(1, aname);
            pstmt.setObject(2, asex);
            pstmt.setObject(3, aphone);
            pstmt.setObject(4, aremark);
            int rs = pstmt.executeUpdate();
            if (rs > 0) {
                // 插入成功
                pw.print("{\"code\": 0}");
            } else {
                pw.print("{\"code\": -1, \"msg\": \"添加员工失败\"}");
            }
            pw.close();
            db.dbutils.close(cnn, pstmt, null);
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
