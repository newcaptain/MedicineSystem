package servlet.order.api;

import db.dbquery;
import utils.ApiResult;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "OrderDelete", urlPatterns = "/api/order/delete")
public class OrderDelete extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        String sql = "delete from userorder where id=?";
        if (new dbquery().delete(sql, id) == 0) {
            new ApiResult(response).sendSuccess();
        } else {
            new ApiResult(response).sendFailed("订单删除失败");
        }
    }
}
