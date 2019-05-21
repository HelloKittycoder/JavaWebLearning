package com.kittycoder.common.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import org.apache.commons.lang3.StringUtils;
import org.camunda.bpm.engine.impl.bpmn.deployer.BpmnDeployer;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by shucheng on 2019/3/11 下午 20:52
 */
public class CamundaUtil {

    /**
     * 判断是否为给定resourceName的后缀
     * @param str
     * @return
     */
    public static boolean checkResourceSuffix(String str) {
        for (String suffix : BpmnDeployer.BPMN_RESOURCE_SUFFIXES) {
            if (StringUtils.endsWith(str, suffix)) {
                return true;
            }
        }
        return false;
    }

    /**
     * map转换为json数据
     * @param map
     * @return
     */
    public static String mapToJson(Map<String, Object> map) {
        return JSON.toJSONString(map);
    }

    public static void main(String[] args) {
        /*Map<String, Object> map = new HashMap<String, Object>();
        map.put("users", Arrays.asList(new String[]{"user1","user2","user3"}));
        System.out.println(mapToJson(map));*/
        /*List<String> list = new ArrayList<String>();
        list.add("111");
        list.add("222");
        list.add("333");
        System.out.println(list);*/

        // String jsonStr = "[\"111\",\"222\",\"333\"]";
        /*String jsonStr = "";
        List<String> list = JSON.parseObject(jsonStr, new TypeReference<List<String>>(){});
        System.out.println(list);*/
        // String str = "111,222,333";
        /*String str = "111";
        List<String> strList = Arrays.asList(str.split(","));
        System.out.println(strList);*/
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("myvar", "我的变量");
        System.out.println(JSON.toJSONString(map));
    }
}
