package servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "DoLogin",urlPatterns = "/doLogin")
public class DoLogin extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        HttpSession session = request.getSession();
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        PrintWriter pw = response.getWriter();
        if (username.equals("admin") && password.equals("123")) {
            // 登录成功
            pw.print("{\"code\": \"0\"}");
            session.setAttribute("token", true);
        } else {
            pw.print("{\"code\": \"-1\"}");
        }
        pw.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
