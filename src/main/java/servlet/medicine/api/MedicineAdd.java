package servlet.medicine.api;

import utils.ApiResult;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "MedicineAdd", urlPatterns = "/api/medicine/add")
public class MedicineAdd extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String mname = request.getParameter("mname");
        String mmode = request.getParameter("mmode");
        String mefficacy = request.getParameter("mefficacy");

        String sql = "insert into medicine(mname, mmode, mefficacy) value(?,?,?)";
        if (new db.dbquery().updateQuery(sql, mname, mmode, mefficacy) > 0) {
            new ApiResult(response).sendSuccess();
        } else {
            new ApiResult(response).sendFailed("添加药品失败");
        }
    }
}
