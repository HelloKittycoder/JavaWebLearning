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
 * Created by shucheng on 2019-5-6 上午 0:16
 */
@WebServlet("/showland")
public class ShowLandServlet extends HttpServlet {

    private AirportService airportService = new AirportServiceImpl();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("landport", airportService.showLandPort());
        req.getRequestDispatcher("showairplane").forward(req, resp);
    }
}
