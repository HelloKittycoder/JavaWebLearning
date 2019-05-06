package com.bjsxt.servlet;

import com.bjsxt.pojo.Airplane;
import com.bjsxt.service.AirplaneService;
import com.bjsxt.service.impl.AirplaneServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by shucheng on 2019-5-6 下午 15:13
 */
@WebServlet("/showairplane")
public class ShowAirplaneServlet extends HttpServlet {

    private AirplaneService airplaneService = new AirplaneServiceImpl();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        int takeid = 0;
        String takeIdStr = (String) req.getParameter("takeid");
        if (takeIdStr != null && !takeIdStr.equals("")) {
            takeid = Integer.parseInt(takeIdStr);
        }
        int landid = 0;
        String landIdStr = (String) req.getParameter("landid");
        if (landIdStr != null && !landIdStr.equals("")) {
            landid = Integer.parseInt(landIdStr);
        }
        List<Airplane> airplaneList = airplaneService.show(takeid, landid);
        req.setAttribute("airplaneList", airplaneList);
        req.getRequestDispatcher("index.jsp").forward(req, resp);
    }
}
