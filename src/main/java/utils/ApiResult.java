package utils;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @ClassName: ApiResult
 * @Description: 处理API的返回
 * @Author: newCaptain
 * @Date: 2018-12-06 09:17
 **/
public class ApiResult {
    private HttpServletResponse response;
    private PrintWriter pw = null;

    public ApiResult(HttpServletResponse response) {
        this.response = response;
        this.response.setContentType("application/json;charset=utf-8");
    }

    public int sendSuccess() {
        try {
            pw = response.getWriter();
            pw.print("{\"code\": 0}");
            return 0;
        } catch (IOException e) {
            e.printStackTrace();
            return -1;
        } finally {
            close();
        }
    }

    public int sendFailed(String msg) {
        try {
            pw = response.getWriter();
            pw.print("{\"code\": -1, \"msg\": " + msg +"}");
            return 0;
        } catch (IOException e) {
            e.printStackTrace();
            return -1;
        } finally {
            close();
        }
    }

    public void close() {
        if (pw != null) {
            pw.close();
            pw = null;
        }
    }
}
