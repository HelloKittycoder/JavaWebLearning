package com.bjsxt.servlet;

import com.bjsxt.pojo.Flower;
import com.bjsxt.service.FlowerService;
import com.bjsxt.service.impl.FlowerServiceImpl;
import com.bjsxt.util.WebUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Create by Administrator on 2019/3/16
 */
@WebServlet("/insert")
public class InertServlet extends HttpServlet {

    private FlowerService flowerService = new FlowerServiceImpl();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name =req.getParameter("name");
        String price =req.getParameter("price");
        String production =req.getParameter("production");

        Flower flower = new Flower();
        flower.setName(name);
        flower.setPrice(Double.parseDouble(price));
        flower.setProduction(production);
        int index = flowerService.add(flower);

        // 这里不使用转发的原因：会导致数据被重复提交
        if (index > 0) { // 新增成功
            // 刷新列表
            // req.getRequestDispatcher("/show").forward(req, resp);
            resp.sendRedirect(WebUtil.getPath(req, "/show"));
        } else { // 新增失败
            // req.getRequestDispatcher("/add.jsp").forward(req, resp);
            resp.sendRedirect(WebUtil.getPath(req, "/add.jsp"));
        }
    }
}
