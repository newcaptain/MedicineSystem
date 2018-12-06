package servlet.order;

import db.dbquery;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "OrderList", urlPatterns = "/OrderList")
public class OrderList extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int cpg = 1;
        if (request.getParameter("currentPage") == null){
            request.setAttribute("currentPage", cpg);
        } else {
            cpg = Integer.parseInt(request.getParameter("currentPage"));
            request.setAttribute("currentPage", cpg);
        }
        dbquery db = new dbquery();
        int limit = 5;
        String sql = "select id, cname, aname, mname, odate " +
                "from userorder u inner join client c inner join agency a inner join medicine m " +
                "on u.cno=c.cno&&u.ano=a.ano&&u.mno=m.mno";
        List list = db.findOnePage(sql, (cpg-1)*limit, limit);

        sql = "select count(*) " +
                "from userorder u inner join client c inner join agency a inner join medicine m " +
                "on u.cno=c.cno&&u.ano=a.ano&&u.mno=m.mno";
        int rows = db.queryRows(sql);
        int pageCount = (rows+(limit-1))/limit;

        request.setAttribute("list", list);
        request.setAttribute("rows", rows);
        request.setAttribute("pageCount", pageCount);

        request.getRequestDispatcher("/order-list.jsp").forward(request, response);
    }
}
