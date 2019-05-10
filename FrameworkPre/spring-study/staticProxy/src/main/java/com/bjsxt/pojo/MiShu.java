package com.bjsxt.pojo;

/**
 * Created by shucheng on 2019-5-10 上午 11:18
 * 秘书-代理对象
 */
public class MiShu implements Gongneng {
    private Laozong laozong = new Laozong("云云");

    @Override
    public void zhidingxiaomubiao() {
        System.out.println("约定时间");
        laozong.zhidingxiaomubiao();
        System.out.println("把访客信息备注");
    }

    @Override
    public void chifan() {
        System.out.println("约定时间");
        laozong.chifan();
        System.out.println("把访客信息备注");
    }
}
