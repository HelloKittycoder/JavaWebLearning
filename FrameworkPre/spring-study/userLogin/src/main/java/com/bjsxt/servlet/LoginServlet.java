package com.bjsxt.servlet;

import com.bjsxt.pojo.Users;
import com.bjsxt.service.UsersService;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by shucheng on 2019-5-9 下午 14:07
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private UsersService usersService;

    @Override
    public void init() throws ServletException {
        ApplicationContext ac = WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());
        usersService = ac.getBean("usersService", UsersService.class);
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        // 校验验证码是否匹配
        String code = req.getParameter("code");
        String codeSession = req.getSession().getAttribute("code").toString();

        // 验证码匹配时，继续验证用户名和密码填写是否正确
        if (codeSession.equals(code)) {
            String username = req.getParameter("username");
            String password = req.getParameter("password");
            Users users = new Users();
            users.setUsername(username);
            users.setPassword(password);
            Users checkUserResult = usersService.login(users);
            if (checkUserResult != null) {
                resp.sendRedirect("main.jsp");
            } else {
                req.setAttribute("error", "用户名或密码不正确");
                req.getRequestDispatcher("index.jsp").forward(req, resp);
            }
        } else {
            req.setAttribute("error", "验证码不正确");
            req.getRequestDispatcher("index.jsp").forward(req, resp);
        }
    }
}
