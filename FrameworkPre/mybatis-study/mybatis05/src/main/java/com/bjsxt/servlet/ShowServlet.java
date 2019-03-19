package com.bjsxt.servlet;

import com.bjsxt.pojo.Flower;
import com.bjsxt.pojo.PageInfo;
import com.bjsxt.service.FlowerService;
import com.bjsxt.service.impl.FlowerServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Create by Administrator on 2019/3/17
 * 分页servlet
 */

@WebServlet(value = {"/showPage"})
public class ShowServlet extends HttpServlet {

    private FlowerService flowerService = new FlowerServiceImpl();
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 当前第几页
        String pageNumStr = req.getParameter("pageNum");
        int pageNum = 1;
        if (pageNumStr != null && !pageNumStr.equals("")) {
            pageNum = Integer.parseInt(pageNumStr);
        }
        // 每页几条
        String pageSizeStr = req.getParameter("pageSize");
        int pageSize = 2;
        if (pageSizeStr != null && !pageSizeStr.equals("")) {
            pageSize = Integer.parseInt(pageSizeStr);
        }

        PageInfo pageInfo = flowerService.show(pageNum, pageSize);
        req.setAttribute("pageInfo", pageInfo);
        req.getRequestDispatcher("/index.jsp").forward(req, resp);
    }
}
