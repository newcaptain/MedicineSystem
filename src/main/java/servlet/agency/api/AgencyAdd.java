package servlet.agency.api;

import utils.ApiResult;

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

@WebServlet(name = "AgencyAdd", urlPatterns = "/api/agency/add")
public class AgencyAdd extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String aname = request.getParameter("aname");
        String asex = request.getParameter("asex");
        String aphone = request.getParameter("aphone");
        String aremark = request.getParameter("aremark");

        String sql = "insert into agency(aname, asex, aphone, aremark) value(?, ?, ?, ?);";
        if(new db.dbquery().updateQuery(sql, aname, asex, aphone, aremark) > 0) {
            new ApiResult(response).sendSuccess();
        } else {
            new ApiResult(response).sendFailed("添加员工失败");
        }
    }

}
