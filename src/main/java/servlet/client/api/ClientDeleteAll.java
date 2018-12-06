package servlet.client.api;

import net.sf.json.JSONObject;
import utils.ApiResult;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.beans.PropertyVetoException;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet(name = "ClientDeleteAll", urlPatterns = "/api/client/deleteAll")
public class ClientDeleteAll extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取要删除的数组
        String[] cnos = request.getParameterValues("cnos[]");
        String sql = "delete from client where cno=?";
        if (new db.dbquery().delete(sql, cnos) == 0){
            new ApiResult(response).sendSuccess();
        } else {
            new ApiResult(response).sendFailed("删除顾客失败");
        }
    }
}
