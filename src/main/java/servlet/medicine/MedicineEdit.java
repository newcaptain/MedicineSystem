package servlet.medicine;

import db.dbquery;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@WebServlet(name = "MedicineEdit", urlPatterns = "/MedicineEdit")
public class MedicineEdit extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String mno = request.getParameter("mno");
        String sql = "select * from medicine where mno=?";
        Map map = new dbquery().findOne(sql, mno);
        request.setAttribute("medicine", map);
        request.getRequestDispatcher("/medicine-edit.jsp").forward(request, response);
    }
}
