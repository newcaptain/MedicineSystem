//package filter;
//
//import com.mysql.cj.Session;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//import java.io.IOException;
//
//public class loginfilter implements javax.servlet.Filter {
//    private String[] EXCLUDE_PAGE;
//
//    public void destroy() {
//    }
//
//    public void doFilter(javax.servlet.ServletRequest req, javax.servlet.ServletResponse resp, javax.servlet.FilterChain chain) throws javax.servlet.ServletException, IOException {
////        chain.doFilter(req, resp);
//        HttpServletRequest rq = (HttpServletRequest) req;
//        String uri = rq.getRequestURI();
//        for (String expage : EXCLUDE_PAGE) {
//            if(uri.contains(expage)) {
//                chain.doFilter(req, resp);
//                return ;
//            }
//        }
//        // 设置拦截
//        HttpSession session = rq.getSession();
//        if (session.getAttribute("uid") != null) {
//            chain.doFilter(req, resp);
//            return;
//        } else {
//            ((HttpServletResponse)resp).sendRedirect("/login.html");
//        }
//
//    }
//
//    public void init(javax.servlet.FilterConfig config) throws javax.servlet.ServletException {
//        EXCLUDE_PAGE = config.getInitParameter("EXCLUDE_PAGE").split(",");
//    }
//
//}
