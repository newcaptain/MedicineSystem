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

@WebServlet(name = "ClientEditDo", urlPatterns = "/api/client/edit")
public class ClientEditDo extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String cno = request.getParameter("cno");
        String cname = request.getParameter("cname");
        String csex = request.getParameter("csex");
        String cage = request.getParameter("cage");
        String caddress = request.getParameter("caddress");
        String cphone = request.getParameter("cphone");
        String cremark = request.getParameter("cremark");

        response.setContentType("application/json; charset=utf-8");
        PrintWriter pw = response.getWriter();

        String sql = "update client set cname=?, csex=?, cage=?, caddress=?, cphone=?, cremark=? where cno=?";
        if (new db.dbquery().updateQuery(sql, cname, csex, cage, caddress, cphone, cremark, cno) > 0) {
            pw.print("{\"code\": 0}");
        } else {
            pw.print("{\"code\": -1, \"msg\": \"修改顾客信息失败\"}");
        }
    }
}
