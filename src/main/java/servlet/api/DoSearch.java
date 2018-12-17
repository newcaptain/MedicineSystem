package servlet.api;

import db.dbquery;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "DoSearch", urlPatterns = "/api/search/*")
public class DoSearch extends HttpServlet {

    /**
     * 查找顾客
     * @param request 请求
     * @return 查找出的顾客列表
     */
    private List searchClient(HttpServletRequest request) {
        String cname = request.getParameter("cname");
        String sql = "select * from client where cname like ?";
        return new dbquery().findAll(sql, "%"+cname+"%");
    }

    /**
     * 查找员工
     * @param request 请求
     * @return 查找出的员工列表
     */
    private List searchAgency(HttpServletRequest request) {
        String aname = request.getParameter("aname");
        String sql = "select * from agency where aname like ?";
        return new dbquery().findAll(sql, "%"+aname+"%");
    }

    /**
     * 查找药品
     * @param request 请求
     * @return 查找出的药品列表
     */
    private List searchMedicine(HttpServletRequest request) {
        String mname = request.getParameter("mname");
        String sql = "select * from medicine where mname like ?";
        return new dbquery().findAll(sql, "%"+mname+"%");
    }

    /**
     * 查找订单
     * @param request 请求
     * @return 查找出的订单列表
     */
    private List searchOrder(HttpServletRequest request) {
        String ono = request.getParameter("ono");
        // 连表查询
        String sql = "select id, cname, mname, aname, odate " +
                "from userorder u inner join client c inner join medicine m inner join agency a " +
                "on u.cno=c.cno && u.ano=a.ano && u.mno=m.mno " +
                "where id like ?";
        return new dbquery().findAll(sql, "%"+ono+"%");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 分发请求
        String extend = request.getRequestURI().substring(12);
        List list = null;
        switch (extend) {
            case "client":
                list = searchClient(request);
                break;
            case "agency":
                list = searchAgency(request);
                break;
            case "medicine":
                list = searchMedicine(request);
                break;
            case "order":
                list = searchOrder(request);
                break;
        }

        // 构造 JSON 返回
        Map map = new HashMap(2);
        if (list.size() > 0) {
            map.put("code", 0);
            map.put("data", list);
        } else {
            map.put("code", -1);
        }
        JSONObject json = JSONObject.fromObject(map);
        response.setContentType("application/json;charset=utf-8");
        PrintWriter pw = response.getWriter();
        pw.println(json);
        pw.close();
    }
}
