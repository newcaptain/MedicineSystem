package servlet.medicine;

import db.dbquery;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "MedicineList", urlPatterns = "/MedicineList")
public class MedicineList extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int cpg = 1;
        if (request.getParameter("currentPage") == null) {
            request.setAttribute("currentPage", 1);
        } else {
            cpg = Integer.parseInt(request.getParameter("currentPage"));
            request.setAttribute("currentPage", cpg);
        }
        String sql = "select * from medicine;";
        int limit = 5;
        dbquery db = new db.dbquery();
        List list = db.findOnePage(sql, (cpg-1)*limit, limit);

        sql = "select count(*) from medicine";
        int rows = db.queryRows(sql);
        int pageCount = (rows+(limit-1))/limit;

        request.setAttribute("list", list);
        request.setAttribute("rows", rows);
        request.setAttribute("pageCount", pageCount);

        request.getRequestDispatcher("medicine-list.jsp").forward(request, response);
    }
}
