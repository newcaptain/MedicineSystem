package servlet.order;

import db.dbquery;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@WebServlet(name = "OrderView", urlPatterns = "/OrderView")
public class OrderView extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        String sql = "select id, cname, csex, cage, cphone, mname, mefficacy, mmode, aname, odate, symptom " +
                "from userorder u inner join client c inner join agency a inner join medicine m " +
                "on u.cno=c.cno && u.ano=a.ano && u.mno=m.mno " +
                "where u.id=?";
        Map map = new dbquery().findOne(sql, id);
        request.setAttribute("item", map);
        request.getRequestDispatcher("/order-view.jsp").forward(request, response);
    }
}
