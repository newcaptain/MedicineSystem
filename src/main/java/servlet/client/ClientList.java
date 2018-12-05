package servlet.client;

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

@WebServlet(name = "ClientList",urlPatterns = "/ClientList")
public class ClientList extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // 获取并设置当前页
            int cpg = 1;
            if (request.getParameter("currentPage") == null) {
                request.setAttribute("currentPage", 1);
            } else {
                cpg = Integer.parseInt(request.getParameter("currentPage"));
                request.setAttribute("currentPage",cpg);
            }
            // 操作数据库
            Connection cnn = db.dbutils.getConnection();
            String sql = "select * from client";
            PreparedStatement pstmt = cnn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnNum = rsmd.getColumnCount();
            List list = new ArrayList();
            // 移动到指定位置, 开始计数
            rs.absolute((cpg-1)*5);
            int count = 0;
            while(rs.next()) {
                HashMap tmp = new HashMap(columnNum);
                for(int i=1; i<=columnNum; i++) {
                    tmp.put(rsmd.getColumnName(i), rs.getObject(i));
                }
                list.add(tmp);
                count++;
                if (count == 5) {
                    break;
                }
            }

            // 计算总页数
            rs.last();
            int rows = rs.getRow();
            int pageCount = (rows+4)/5;

            request.setAttribute("list", list);
            request.setAttribute("rows", rows);
            request.setAttribute("pageCount", pageCount);
            request.getRequestDispatcher("client-list.jsp").forward(request,response);

            db.dbutils.close(cnn, pstmt, rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
