package com.kittycoder.easydemo;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

/**
 * Created by shucheng on 2020/6/10 18:26
 */
public class StringUtilsTest {

    // 查找字符串出现的位置
    @Test
    public void testLastOrdinalIndexOf() {
        String str = "com.kittycoder.StudentMapper.selectAllStudent"; // mybatis中带全路径的sqlId
        // 查找倒数第二个“.”出现的位置
        System.out.println(getSimpleSqlId(str)); // StudentMapper.selectAllStudent
        System.out.println(getSimpleSqlId2(str));
    }

    // 获取简单的sqlId（使用工具类）
    public static String getSimpleSqlId(String orginalSqlId) {
        if (StringUtils.isNotBlank(orginalSqlId)) {
            // 获取倒数第二个“.”
            int dotPos = StringUtils.lastOrdinalIndexOf(orginalSqlId, ".", 2);
            if (dotPos == -1) {
                return "";
            } else {
                return orginalSqlId.substring(dotPos + 1);
            }
        }
        return "";
    }

    // 自己简单写的：获取倒数第二个“.”
    public static String getSimpleSqlId2(String orginalSqlId) {
        int lastIndex = orginalSqlId.lastIndexOf(".");
        int lastSecondIndex = orginalSqlId.lastIndexOf(".", lastIndex - 1);
        return orginalSqlId.substring(lastSecondIndex + 1);
    }
}
