package com.kittycoder.filter;

import javax.servlet.*;
import java.io.IOException;

/**
 * Created by shucheng on 2020/2/7 14:48
 */
public class MyFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("MyFilter process...");
        chain.doFilter(request, response);
    }
}
