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

@WebServlet(name = "ClientDelete", urlPatterns = "/api/client/delete")
public class ClientDelete extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 拿Response
        response.setContentType("application/json; charset=utf-8");
        PrintWriter pw = response.getWriter();

        // 执行SQL语句
        String cno = request.getParameter("cno");
        String sql = "delete from client where cno=?";
        if (new db.dbquery().delete(sql, cno) == 0) {
            pw.print("{\"code\": 0}");
        } else {
            pw.print("{\"code\": -1, \"msg\": \"删除顾客失败\"}");
        }
        pw.close();
//        try {
//            Connection cnn = db.dbutils.getConnection();
//            String sql = "delete from client where cno=?";
//            PreparedStatement pstmt = cnn.prepareStatement(sql);
//            pstmt.setString(1, cno);
//            int res = pstmt.executeUpdate();
//            if (res >= 1) {
//                // 删除成功
//                pw.print("{\"code\": 0}");
//            } else {
//                // 删除失败
//                pw.print("{\"code\": -1, \"msg\": \"删除顾客失败\"}");
//            }
//
//            pw.close();
//            db.dbutils.close(cnn, pstmt, null);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
    }

}
