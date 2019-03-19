package com.bjsxt.servlet;

import com.bjsxt.pojo.Flower;
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
 */

/**
 * 注解：
 * 1.大部分注解都有默认属性，如果注解中值给默认属性赋值，可以省略属性名
 * 否则在注解的括号里要写成 “属性名=属性值” 格式
 * 2.如果一个属性是数组类型格式： 属性名={值,值}，如果该数组只有一个值，可以省略大括号
 * 3.如果类型不是基本数据类型或String而是一个类类型，语法： 属性名=@类型
 * 4.注解中@表示引用注释声明
 */
// tomcat7.0及以上（web3.0及以上）可以使用WebServlet注解来配置servlet
@WebServlet(value = {"/show"})
public class ShowServlet extends HttpServlet {

    private FlowerService flowerService = new FlowerServiceImpl();
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Flower> list = flowerService.show();
        req.setAttribute("list", list);
        // 相对路径与绝对路径
        /**
         * 1.只要路径中以/开头的都叫做全路径，从服务器根目录出发找到其他资源
         *      如果jsp页面中配了basePath，则/表示从basePath出发找其他资源
         *
         * 2.只要不以/开头的都是相对路径，相对路径是从当前资源出发找到其他资源
         *      举例：servlet路径为"/a/b/show"，项目的webapp目录下有index.jsp，
         *      从show转发到该jsp，相对路径为 ../../index.jsp，绝对路径为 /index.jsp
         *      从show重定向到该jsp，相对路径同上，绝对路径为 /部署项目名/index.jsp
         * 使用绝对路径时，转发会自动补上部署项目名，重定向则需要自己加上去
         *
         * 3.如果请求转发，/表示webapp目录（项目根路径）
         */
        req.getRequestDispatcher("/index.jsp").forward(req, resp);
    }
}
