package servlet.medicine;

import db.dbquery;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@WebServlet(name = "MedicineShop", urlPatterns = "/MedicineShop")
public class MedicineShop extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String mno = request.getParameter("mno");
        String sql = "select mno,mname from medicine where mno=?";
        dbquery db = new dbquery();
        Map medicineMap = db.findOne(sql, mno);

        sql = "select cno,cname from client";
        List clientList = db.findAll(sql);

        sql = "select ano,aname from agency";
        List agencyList = db.findAll(sql);

        request.setAttribute("medicine", medicineMap);
        request.setAttribute("clientList", clientList);
        request.setAttribute("agencyList", agencyList);
        request.getRequestDispatcher("/medicine-shop.jsp").forward(request, response);
    }
}
