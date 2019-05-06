package com.bjsxt.util;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by shucheng on 2019-5-5 下午 23:44
 */
public class WebUtil {

    public static String getPath(HttpServletRequest req, String originalPath) {
        return req.getContextPath() + originalPath;
    }
}
