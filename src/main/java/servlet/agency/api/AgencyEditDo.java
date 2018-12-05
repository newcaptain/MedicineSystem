package servlet.agency.api;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@WebServlet(name = "AgencyEditDo", urlPatterns = "/api/agency/edit")
public class AgencyEditDo extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String ano = request.getParameter("ano");
        String aname = request.getParameter("aname");
        String asex = request.getParameter("asex");
        String aphone = request.getParameter("aphone");
        String aremark = request.getParameter("aremark");

//        Connection cnn = null;
//        try {
//            cnn = db.dbutils.getConnection();
//            PreparedStatement pstmt = cnn.prepareStatement(sql);
//        } catch (PropertyVetoException e) {
//            e.printStackTrace();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
