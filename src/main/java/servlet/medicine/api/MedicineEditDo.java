package servlet.medicine.api;

import db.dbquery;
import utils.ApiResult;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "MedicineEditDo", urlPatterns = "/api/medicine/edit")
public class MedicineEditDo extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String mno = request.getParameter("mno");
        String mname = request.getParameter("mname");
        String mmode = request.getParameter("mmode");
        String mefficacy = request.getParameter("mefficacy");

        String sql = "update medicine set mname=?, mmode=?, mefficacy=? where mno=?";
        if (new dbquery().updateQuery(sql, mname, mmode, mefficacy, mno) > 0) {
            new ApiResult(response).sendSuccess();
        } else {
            new ApiResult(response).sendFailed("修改药品失败");
        }
    }
}
