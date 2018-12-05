package servlet.agency;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@WebServlet(name = "AgencyList", urlPatterns = "/AgencyList")
public class AgencyList extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int pg = 1;
        if (request.getParameter("currentPage") == null) {
            request.setAttribute("currentPage", pg);
        } else {
            pg = Integer.parseInt(request.getParameter("currentPage"));
            request.setAttribute("currentPage", pg);
        }

        try {
            Connection cnn = db.dbutils.getConnection();
            String sql = "select * from agency";
            PreparedStatement pstmt = cnn.prepareStatement(sql);
            ResultSet rst = pstmt.executeQuery();
            ResultSetMetaData rsmd = rst.getMetaData();
            int colNums = rsmd.getColumnCount();
            List list = new ArrayList();
            // 从指定位置开始
            rst.absolute((pg-1)*5);
            int counter = 0;
            while (rst.next()) {
                HashMap hsm = new HashMap(colNums);
                for (int i=1; i<=colNums; i++) {
                    hsm.put(rsmd.getColumnName(i), rst.getString(i));
                }
                list.add(hsm);
                counter ++;
                if (counter == 5) {
                    break;
                }
            }
            // 计算总行数,总页数
            rst.last();
            int rows = rst.getRow();
            int pageCount = (rows+4)/5;

            // 丢到request
            request.setAttribute("list", list);
            request.setAttribute("rows", rows);
            request.setAttribute("pageCount", pageCount);
            request.getRequestDispatcher("/agency-list.jsp").forward(request, response);

            db.dbutils.close(cnn, pstmt, rst);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
