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
import java.util.Map;

@WebServlet(name = "AgencyEdit", urlPatterns = "/AgencyEdit")
public class AgencyEdit extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String ano = request.getParameter("ano");
        String sql = "select * from agency where ano=?";
        Map map = new db.dbquery().findOne(sql, ano);
        request.setAttribute("agency", map);
        request.getRequestDispatcher("/agency-edit.jsp").forward(request, response);
    }
}
