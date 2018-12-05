package servlet.client.api;

import db.dbquery;

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

@WebServlet(name = "ClientAdd", urlPatterns = "/api/client/add")
public class ClientAdd extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String cname = request.getParameter("cname");
        String csex = request.getParameter("csex");
        String cage = request.getParameter("cage");
        String caddress = request.getParameter("caddress");
        String cphone = request.getParameter("cphone");
        String cremark = request.getParameter("cremark");

        response.setContentType("application/json;charset=utf-8");
        PrintWriter pw = response.getWriter();
        String sql = "insert into client(cname,csex,cage,caddress,cphone,cremark) value(?,?,?,?,?,?);";
        if (new dbquery().updateQuery(sql, cname, csex, cage, caddress, cphone, cremark) > 0) {
            pw.print("{\"code\": 0}");
        } else {
            pw.print("{\"code\": -1, \"msg\": \"添加用户失败\"}");
        }
        pw.close();
    }
}
