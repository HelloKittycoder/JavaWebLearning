package com.bjsxt;

import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.result.RequestResultMatchers;

import static org.springframework.test.util.AssertionErrors.assertEquals;
import static org.springframework.test.util.AssertionErrors.assertTrue;

/**
 * Created by shucheng on 2019-5-18 下午 22:39
 * 因为RequestResultMatchers类中没有现成的方法，所有直接继承已有的类，写了一些方法
 */
public class RequestResultMatchersExt extends RequestResultMatchers {
    // 验证application中的属性的方法
    public ResultMatcher applicationAttribute(final String name, final Object value) {
        return new ResultMatcher() {
            @Override
            public void match(MvcResult result) throws Exception {
                assertEquals("Request attribute", value,
                        result.getRequest().getServletContext().getAttribute(name));
                // assertTrue("Request attribute", result.getRequest().getAttribute("aa")!=null);
            }
        };
    }

    // 判断request中是否含有某个属性
    public ResultMatcher hasAttribute(final String name) {
        return new ResultMatcher() {
            @Override
            public void match(MvcResult result) throws Exception {
                assertTrue("Request attribute",
                        result.getRequest().getAttribute(name) != null);
            }
        };
    }
}
