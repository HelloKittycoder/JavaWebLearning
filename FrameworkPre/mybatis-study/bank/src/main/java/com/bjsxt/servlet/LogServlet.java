package com.bjsxt.servlet;

import com.bjsxt.pojo.PageInfo;
import com.bjsxt.service.LogService;
import com.bjsxt.service.impl.LogServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Create by Administrator on 2019/3/21
 */
@WebServlet("/showLogList")
public class LogServlet extends HttpServlet {

    private LogService logService = new LogServiceImpl();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pageSizeStr = req.getParameter("pageSize");
        int pageSize = 2;
        if (pageSizeStr != null && !pageSizeStr.equals("")) {
            pageSize = Integer.parseInt(pageSizeStr);
        }
        String pageNumStr = req.getParameter("pageNum");
        int pageNum = 1;
        if (pageNumStr != null && !pageNumStr.equals("")) {
            pageNum = Integer.parseInt(pageNumStr);
        }
        // 查询分页数据
        PageInfo pageInfo = logService.showPage(pageSize, pageNum);
        req.setAttribute("pageInfo", pageInfo);
        req.getRequestDispatcher("/log.jsp").forward(req, resp);
    }
}
