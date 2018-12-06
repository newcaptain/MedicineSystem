package servlet.client.api;

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

@WebServlet(name = "ClientDelete", urlPatterns = "/api/client/delete")
public class ClientDelete extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String cno = request.getParameter("cno");
        String sql = "delete client, userorder from client left join userorder on client.cno=userorder.cno where client.cno=?";
        if (new db.dbquery().delete(sql, cno) == 0) {
            new ApiResult(response).sendSuccess();
        } else {
            new ApiResult(response).sendFailed("删除顾客失败");
        }
    }
}
