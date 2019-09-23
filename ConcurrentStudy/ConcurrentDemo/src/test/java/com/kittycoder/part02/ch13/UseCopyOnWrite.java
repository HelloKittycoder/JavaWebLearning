package com.kittycoder.part02.ch13;

import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * Created by shucheng on 2019-9-23 上午 9:08
 * CopyOnWrite（写时复制）的使用demo，适用于读多写少的场景
 * 写的时候会加锁，是基于对象副本来进行写操作，等写完后把原有引用指向这个对象副本
 */
public class UseCopyOnWrite {

    public static void main(String[] args) {
        CopyOnWriteArrayList<String> cwal = new CopyOnWriteArrayList<>();
        CopyOnWriteArraySet<String> cwas = new CopyOnWriteArraySet<>();
    }
}
