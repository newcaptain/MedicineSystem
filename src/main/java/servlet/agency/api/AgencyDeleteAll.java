package servlet.agency.api;

import com.google.protobuf.Api;
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

@WebServlet(name = "AgencyDeleteAll", urlPatterns = "/api/agency/deleteAll")
public class AgencyDeleteAll extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String[] anos = request.getParameterValues("anos[]");

        String sql = "delete agency, userorder from agency left join userorder on agency.ano=userorder.ano where agency.ano=?";
        if (new db.dbquery().delete(sql, anos) == 0) {
            new ApiResult(response).sendSuccess();
        } else {
            new ApiResult(response).sendFailed("删除员工失败");
        }
    }

}
