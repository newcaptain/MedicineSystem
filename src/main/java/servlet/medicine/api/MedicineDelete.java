package servlet.medicine.api;

import utils.ApiResult;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "MedicineDelete", urlPatterns = "/api/medicine/delete")
public class MedicineDelete extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String mno = request.getParameter("mno");
        String sql = "delete from medicine where mno=?";
        if (new db.dbquery().delete(sql, mno) == 0) {
            new ApiResult(response).sendSuccess();
        } else {
            new ApiResult(response).sendFailed("药品删除失败");
        }
    }
}
