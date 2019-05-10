package com.bjsxt.cglib_dynamic.pojo;

import net.sf.cglib.proxy.Enhancer;

/**
 * Created by shucheng on 2019-5-10 下午 14:54
 */
public class Women {
    public static void main(String[] args) {
        Enhancer enhancer = new Enhancer();
        // 告诉cglib需要生成Laozong类的子类
        enhancer.setSuperclass(Laozong.class);
        // 设置实际处理的类为Mishu
        enhancer.setCallback(new Mishu());

        Laozong laozong = (Laozong) enhancer.create();
        laozong.chifan();
    }
}
