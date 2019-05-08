package com.bjsxt.servlet;

import com.bjsxt.service.AirportService;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by shucheng on 2019-5-8 上午 9:23
 */
@WebServlet("/airport")
public class AirportServlet extends HttpServlet {
    private AirportService airportService;

    @Override
    public void init() throws ServletException {
        // 对service实例化
        // ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        // spring和web整合后所有信息都存放在WebApplicationContext
        ApplicationContext ac = WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());
        airportService = ac.getBean("airportService", AirportService.class);
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("list", airportService.show());
        req.getRequestDispatcher("index.jsp").forward(req, resp);
    }
}
