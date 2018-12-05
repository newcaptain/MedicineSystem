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

@WebServlet(name = "AgencyDeleteAll", urlPatterns = "/api/agency/deleteAll")
public class AgencyDeleteAll extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String[] anos = request.getParameterValues("anos[]");
        response.setContentType("application/json;charset=utf-8");
        PrintWriter pw = response.getWriter();
        String sql = "delete from agency where ano=?";
        if (new db.dbquery().delete(sql, anos) == 0) {
            pw.print("{\"code\": 0}");
        } else {
            pw.print("{\"code\": -1, \"msg\": \"删除员工失败\"}");
        }
        pw.close();
//        try {
//            Connection cnn = db.dbutils.getConnection();
//            String sql = "delete from agency where ano=?";
//            PreparedStatement pstmt = cnn.prepareStatement(sql);
//            for (int i=0; i<anos.length; i++) {
//                pstmt.setObject(1, anos[i]);
//                pstmt.addBatch();
//            }
//            pstmt.executeBatch();
//            response.setContentType("application/json;charset=utf-8");
//            PrintWriter pw = response.getWriter();
//            pw.print("{\"code\": 0}");
//            pw.close();
//            db.dbutils.close(cnn, pstmt, null);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
    }

}
