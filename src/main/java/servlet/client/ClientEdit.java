package servlet.client;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.*;
import java.util.HashMap;

@WebServlet(name = "ClientEdit", urlPatterns = "/ClientEdit")
public class ClientEdit extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String cno = request.getParameter("id");

        try {
            Connection cnn = db.dbutils.getConnection();
            String sql = "select * from client where cno=?";
            PreparedStatement pstmt = cnn.prepareStatement(sql);
            pstmt.setObject(1, cno);
            ResultSet rst = pstmt.executeQuery();
            ResultSetMetaData rsmd = rst.getMetaData();
            int colNums = rsmd.getColumnCount();
            HashMap hashmap = new HashMap(6);
            rst.next();
            for (int i=1; i<=colNums; i++) {
                hashmap.put(rsmd.getColumnName(i),rst.getString(i));
            }
            request.setAttribute("client", hashmap);
            request.getRequestDispatcher("/client-edit.jsp").forward(request, response);

            db.dbutils.close(cnn, pstmt, rst);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
