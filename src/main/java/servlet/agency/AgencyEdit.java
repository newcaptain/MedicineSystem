package servlet.agency;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.*;
import java.util.HashMap;

@WebServlet(name = "AgencyEdit", urlPatterns = "/AgencyEdit")
public class AgencyEdit extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String ano = request.getParameter("ano");
        try {
            String sql = "select * from agency where ano=?";
            Connection cnn = db.dbutils.getConnection();
            PreparedStatement pstmt = cnn.prepareStatement(sql);
            pstmt.setObject(1, ano);
            ResultSet rst = pstmt.executeQuery();
            ResultSetMetaData rsmd = rst.getMetaData();
            int colNums = rsmd.getColumnCount();
            rst.next();

            HashMap hs = new HashMap(colNums);
            for (int i=1; i<=colNums; i++) {
                hs.put(rsmd.getColumnName(i), rst.getObject(i));
            }
            request.setAttribute("agency", hs);

            request.getRequestDispatcher("/agency-edit.jsp").forward(request, response);
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
