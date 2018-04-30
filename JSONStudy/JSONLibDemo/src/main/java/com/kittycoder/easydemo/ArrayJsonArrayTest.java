package com.kittycoder.easydemo;

import net.sf.json.JSONArray;

/**
 * Created by shucheng on 2018/4/30.
 * 07 JSONArray与数组互转
 */
public class ArrayJsonArrayTest {
    public static void main(String[] args) {
        // Java数组转JSONArray
        boolean[] boolArray = new boolean[]{true, false, true};
        JSONArray jsonArray = JSONArray.fromObject(boolArray);
        System.out.println(jsonArray);

        // JSONArray转Java数组
        Object[] obj = jsonArray.toArray();
        for (Object o : obj) {
            System.out.print(o + " ");
        }
    }
}
