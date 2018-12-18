package filter;

import com.mysql.cj.Session;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class loginfilter implements javax.servlet.Filter {
    private String[] EXCLUDE_PAGE;
    private String[] EXCLUDE_REQUEST;

    public void destroy() {
    }

    public void doFilter(javax.servlet.ServletRequest req, javax.servlet.ServletResponse resp, javax.servlet.FilterChain chain) throws javax.servlet.ServletException, IOException {
//        chain.doFilter(req, resp);
        HttpServletRequest rq = (HttpServletRequest) req;
        HttpSession session = rq.getSession();
        if (session.getAttribute("isLogin")!=null) {
            // 放行
            chain.doFilter(req, resp);
            return;
        } else {
            // 判断可访问页面
            String uri = rq.getRequestURI();
            for (String expage : EXCLUDE_PAGE) {
                if (uri.equals(expage)) {
                    chain.doFilter(req, resp);
                    return;
                }
            }
            // 判断可访问请求
            for (String exrequest : EXCLUDE_REQUEST) {
                if (uri.contains(exrequest)) {
                    chain.doFilter(req, resp);
                    return;
                }
            }
            // 打回
            ((HttpServletResponse)resp).sendRedirect("/login.html");
        }

    }

    public void init(javax.servlet.FilterConfig config) throws javax.servlet.ServletException {
        EXCLUDE_PAGE = config.getInitParameter("EXCLUDE_PAGE").split(",");
        EXCLUDE_REQUEST = config.getInitParameter("EXCLUDE_REQUEST").split(",");
    }

}
