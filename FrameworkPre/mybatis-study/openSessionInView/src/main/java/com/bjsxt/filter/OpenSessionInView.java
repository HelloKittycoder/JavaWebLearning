package com.bjsxt.filter;

import com.bjsxt.util.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * Create by Administrator on 2019/3/25
 * 最开始是由Spring框架提出的，整合Hibernate框架使用的是openSessionInView
 */
@WebFilter("/*")
public class OpenSessionInView implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        /*InputStream is = Resources.getResourceAsStream("mybatis.xml");
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(is);
        SqlSession session = factory.openSession();*/
        SqlSession session = MyBatisUtil.getSession();

        try {
            chain.doFilter(request, response);
            session.commit();
        } catch (Exception e) {
            session.rollback();
            e.printStackTrace();
        } finally {
            MyBatisUtil.closeSession();
        }

        /*session.commit();
        session.close();*/
    }

    @Override
    public void destroy() {

    }
}
