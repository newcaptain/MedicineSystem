package servlet.agency;

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

@WebServlet(name = "AgencyList", urlPatterns = "/AgencyList")
public class AgencyList extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int cpg = 1;
        if (request.getParameter("currentPage") == null) {
            request.setAttribute("currentPage", cpg);
        } else {
            cpg = Integer.parseInt(request.getParameter("currentPage"));
            request.setAttribute("currentPage", cpg);
        }

        dbquery db = new dbquery();
        String sql = "select * from agency";
        int limit = 5;
        List list = db.findOnePage(sql, (cpg-1)*limit, limit);

        sql = "select count(*) from agency";
        int rows = db.queryRows(sql);
        int pageCount = (rows+(limit-1))/limit;

        request.setAttribute("list", list);
        request.setAttribute("rows", rows);
        request.setAttribute("pageCount", pageCount);
        request.getRequestDispatcher("/agency-list.jsp").forward(request, response);
    }
}
