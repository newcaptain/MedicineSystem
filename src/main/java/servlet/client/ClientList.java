package servlet.client;

import db.dbquery;

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
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取并设置当前页
        int cpg = 1;
        if (request.getParameter("currentPage") == null) {
            request.setAttribute("currentPage", 1);
        } else {
            cpg = Integer.parseInt(request.getParameter("currentPage"));
            request.setAttribute("currentPage",cpg);
        }

        String sql = "select * from client";
        int limit = 5;
        dbquery db = new db.dbquery();
        List list = db.findOnePage(sql, (cpg-1)*limit, limit);

        sql = "select count(*) from client";
        int rows = db.queryRows(sql);
        int pageCount = (rows+(limit-1))/limit;

        request.setAttribute("list", list);
        request.setAttribute("rows", rows);
        request.setAttribute("pageCount", pageCount);
        request.getRequestDispatcher("client-list.jsp").forward(request,response);
    }
}
