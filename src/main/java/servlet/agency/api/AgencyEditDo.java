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

@WebServlet(name = "AgencyEditDo", urlPatterns = "/api/agency/edit")
public class AgencyEditDo extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String ano = request.getParameter("ano");
        String aname = request.getParameter("aname");
        String asex = request.getParameter("asex");
        String aphone = request.getParameter("aphone");
        String aremark = request.getParameter("aremark");

        response.setContentType("application/json; charset=utf-8");
        PrintWriter pw = response.getWriter();
        String sql = "update agency set aname=?, asex=?, aphone=?, aremark=? where ano=?";
        if (new db.dbquery().updateQuery(sql, aname, asex, aphone, aremark, ano) > 0) {
            pw.print("{\"code\": 0}");
        } else {
            pw.print("{\"code\": -1, \"msg\": \"修改员工信息失败\"}");
        }
    }

}
