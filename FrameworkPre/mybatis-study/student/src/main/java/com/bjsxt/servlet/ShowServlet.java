package com.bjsxt.servlet;

import com.bjsxt.pojo.PageInfo;
import com.bjsxt.service.StudentService;
import com.bjsxt.service.impl.StudentServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Create by Administrator on 2019/3/26
 */
@WebServlet("/show")
public class ShowServlet extends HttpServlet {

    private StudentService studentService = new StudentServiceImpl();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String sname = req.getParameter("sname");
        if (sname != null && !sname.equals("")) {
            sname = new String(sname.getBytes("iso-8859-1"), "utf-8");
        }
        String tname = req.getParameter("tname");
        if (tname != null && !tname.equals("")) {
            tname = new String(tname.getBytes("iso-8859-1"), "utf-8");
        }
        String pageSize = req.getParameter("pageSize");
        String pageNumber = req.getParameter("pageNumber");
        PageInfo pi = studentService.showPage(sname, tname, pageSize, pageNumber);
        req.setAttribute("pageInfo", pi);
        req.getRequestDispatcher("/index.jsp").forward(req, resp);
    }
}
