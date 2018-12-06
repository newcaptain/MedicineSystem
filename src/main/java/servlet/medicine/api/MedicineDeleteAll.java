package servlet.medicine.api;

import db.dbquery;
import utils.ApiResult;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "MedicineDeleteAll", urlPatterns = "/api/medicine/deleteAll")
public class MedicineDeleteAll extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String[] mnos = request.getParameterValues("mnos[]");
        String sql = "delete from medicine where mno=?";
        if (new dbquery().delete(sql, mnos) == 0) {
            new ApiResult(response).sendSuccess();
        } else {
            new ApiResult(response).sendFailed("药品删除失败");
        }
    }
}
