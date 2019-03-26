package com.bjsxt.util;

import javax.servlet.http.HttpServletRequest;

/**
 * Create by Administrator on 2019/3/25
 */
public class WebUtil {

    public static String getPath(HttpServletRequest req, String originalPath) {
        return req.getContextPath() + originalPath;
    }
}
