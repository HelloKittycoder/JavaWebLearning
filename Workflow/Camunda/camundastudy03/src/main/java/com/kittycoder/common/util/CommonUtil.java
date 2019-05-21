package com.kittycoder.common.util;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;

/**
 * Created by shucheng on 2019/3/12 上午 10:04
 */
public class CommonUtil {

    /**
     * 判断字符串是否为json字符串
     * @param str
     * @return
     */
    public static boolean isJSONValid(String str) {
        // 如果是空字符串，直接返回false
        if (StringUtils.isBlank(str)) {
            return false;
        }

        // 先判断是否为json对象
        try {
            JSONObject.parseObject(str);
        } catch (JSONException e) {
            // 如果不是json对象，再判断是否为json数组
            try {
                JSONObject.parseArray(str);
            } catch (JSONException e1) {
                return false;
            }
        }
        return true;
    }
}
