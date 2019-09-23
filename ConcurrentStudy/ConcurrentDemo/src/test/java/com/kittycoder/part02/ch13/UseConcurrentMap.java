package com.kittycoder.part02.ch13;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Created by shucheng on 2019-9-23 上午 8:51
 * ConcurrentMap的使用
 */
public class UseConcurrentMap {

    public static void main(String[] args) {
        ConcurrentMap<String, Object> cm = new ConcurrentHashMap<>();
        cm.put("k1", "v1");
        cm.put("k2", "v2");
        cm.put("k3", "v3");
        cm.putIfAbsent("k4", "vvv");
        // System.out.println(cm.get("k2"));
        // System.out.println(cm.size());

        for (Map.Entry<String, Object> me : cm.entrySet()) {
            System.out.println("key:" + me.getKey() + ",value:" + me.getValue());
        }
    }
}
