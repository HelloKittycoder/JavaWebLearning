package com.bjsxt.servlet;

import com.bjsxt.service.AirportService;
import com.bjsxt.service.impl.AirportServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by shucheng on 2019-5-5 下午 23:49
 * http://localhost:8080/airplane/showtake
 */
@WebServlet("/showtake")
public class ShowTakeServlet extends HttpServlet {

    private AirportService airportService = new AirportServiceImpl();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("takeport", airportService.showTakePort());
        req.getRequestDispatcher("showland").forward(req, resp);
    }
}
