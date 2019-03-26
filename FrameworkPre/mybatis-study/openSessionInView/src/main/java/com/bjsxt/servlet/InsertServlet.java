package com.bjsxt.servlet;

import com.bjsxt.pojo.Log;
import com.bjsxt.service.LogService;
import com.bjsxt.service.impl.LogServiceImpl;
import com.bjsxt.util.WebUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Create by Administrator on 2019/3/25
 */
@WebServlet("/insert")
public class InsertServlet extends HttpServlet {

    private LogService logService = new LogServiceImpl();
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        Log log = new Log();
        log.setAccIn(req.getParameter("accIn"));
        log.setAccOut(req.getParameter("accOut"));
        log.setMoney(Double.parseDouble(req.getParameter("money")));
        int index = logService.insertLog(log);
        if (index > 0) {
            resp.sendRedirect(WebUtil.getPath(req, "/success.jsp"));
        } else {
            resp.sendRedirect(WebUtil.getPath(req, "/error.jsp"));
        }
    }
}
