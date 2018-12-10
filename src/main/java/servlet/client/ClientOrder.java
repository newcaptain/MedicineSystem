package servlet.client;

import db.dbquery;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ClientOrder", urlPatterns = "/ClientOrder")
public class ClientOrder extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String cno = request.getParameter("cno");
        String sql = "select id, cname, csex, cage, cphone, mname, mefficacy, mmode, aname, odate, symptom " +
                "from userorder u inner join client c inner join medicine m inner join agency a " +
                "on u.cno=c.cno && u.ano=a.ano && u.mno=m.mno " +
                "where c.cno=?";
        List list = new dbquery().findAll(sql, cno);
        request.setAttribute("items", list);
        request.getRequestDispatcher("/order-view.jsp").forward(request, response);
    }
}
