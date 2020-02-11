package com.kittycoder.listener;

import javax.servlet.*;

/**
 * Created by shucheng on 2020/2/7 15:41
 */
public class MyListener implements ServletRequestListener, ServletRequestAttributeListener, ServletContextListener {

    /*@Override
    public void requestInitialized(ServletRequestEvent sre) {
        System.out.println("请求被创建..." + ((HttpServletRequest) sre.getServletRequest()).getRequestURL());
    }

    @Override
    public void requestDestroyed(ServletRequestEvent sre) {
        System.out.println("请求被销毁..." + ((HttpServletRequest) sre.getServletRequest()).getRequestURL());
    }*/

    /*@Override
    public void attributeAdded(ServletRequestAttributeEvent srae) {
        System.out.println("添加属性..." + srae.getName() + "->" + srae.getValue());
    }*/

    /*@Override
    public void attributeRemoved(ServletRequestAttributeEvent srae) {
        System.out.println("移除属性..." + srae.getName() + "->" + srae.getValue());
    }*/

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("contextInitialized...web应用启动");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("contextInitialized...web应用销毁");
    }
}
