package servlet;

import db.dbquery;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "Welcome", urlPatterns = "/welcome")
public class Welcome extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        dbquery db = new dbquery();
        // 获取顾客数
        int cnum = db.queryRows("select count(*) from client");
        // 获取销售人员数
        int anum = db.queryRows("select count(*) from agency");
        // 获取药品数
        int mnum = db.queryRows("select count(*) from medicine");
        // 获取订单数
        int onum = db.queryRows("select count(*) from userorder");

        request.setAttribute("cnum", cnum);
        request.setAttribute("anum", anum);
        request.setAttribute("mnum", mnum);
        request.setAttribute("onum", onum);

        request.getRequestDispatcher("/welcome.jsp").forward(request, response);
    }
}
