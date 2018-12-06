package servlet.medicine.api;

import db.dbquery;
import utils.ApiResult;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "MedicineShopDo", urlPatterns = "/api/medicine/shop")
public class MedicineShopDo extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String ano = request.getParameter("ano");
        String cno = request.getParameter("cno");
        String mno = request.getParameter("mno");
        String symptom = request.getParameter("symptom");

        String sql = "insert into userorder(cno,symptom, ano, mno) value(?,?,?,?)";
        if (new dbquery().updateQuery(sql, cno, symptom, ano, mno) > 0) {
            new ApiResult(response).sendSuccess();
        } else {
            new ApiResult(response).sendFailed("购买药品失败");
        }
    }

}
