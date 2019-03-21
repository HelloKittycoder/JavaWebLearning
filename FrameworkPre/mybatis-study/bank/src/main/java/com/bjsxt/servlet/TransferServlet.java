package com.bjsxt.servlet;

import com.bjsxt.pojo.Account;
import com.bjsxt.service.AccountService;
import com.bjsxt.service.impl.AccountServiceImpl;
import com.bjsxt.util.WebUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Create by Administrator on 2019/3/20
 * 转账servlet
 */
@WebServlet("/transfer")
public class TransferServlet extends HttpServlet {

    private AccountService accountService = new AccountServiceImpl();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        Account accOut = new Account();
        // 付款方账号
        accOut.setAccNo(req.getParameter("accOutAccNo"));
        // 付款方密码
        accOut.setPassword(Integer.parseInt(req.getParameter("accOutPassword")));
        // 付款方转账金额
        accOut.setBalance(Double.parseDouble(req.getParameter("accOutBalance")));

        Account accIn = new Account();
        // 收款方账号
        accIn.setAccNo(req.getParameter("accInAccNo"));
        // 收款方姓名
        accIn.setName(req.getParameter("accInName"));

        // 执行转账操作
        int index = accountService.transfer(accIn, accOut);
        if (index == AccountService.SUCCESS) {
            resp.sendRedirect(WebUtil.getPath(req, "/showLogList"));
        } else {
            HttpSession session = req.getSession();
            session.setAttribute("code", index);
            resp.sendRedirect(WebUtil.getPath(req, "/error/error.jsp"));
        }
    }
}
