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
import java.util.Map;

@WebServlet(name = "ClientEdit", urlPatterns = "/ClientEdit")
public class ClientEdit extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String cno = request.getParameter("id");
        String sql = "select * from client where cno=?";
        Map map = new db.dbquery().findOne(sql, cno);
        request.setAttribute("client", map);
        request.getRequestDispatcher("/client-edit.jsp").forward(request, response);
    }
}
