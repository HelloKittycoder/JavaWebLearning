package com.bjsxt.pojo;

/**
 * Create by Administrator on 2019/5/11
 * 懒汉式单例
 */
public class SingletonLazy {
    // 由于对象需要被静态方法调用，所以把方法设置为static
    // 由于对象是static，必须要设置访问权限修饰符为private；因为如果是public可以直接访问对象，不走访问入口
    private static SingletonLazy singletonLazy;
    /**
     * 构造方法：方法名和类名相同，无返回值
     * 将构造方法私有化，使其他类不能直接实例化这个对象
     *
     * 对外提供访问入口
     */
    private SingletonLazy() {}

    /**
     * 实例方法必须通过对象调用
     * 设置方法为静态方法
     * @return
     */
    public static SingletonLazy getInstance() {
        // 添加逻辑：如果实例化过，直接返回
        if (singletonLazy == null) {
            /**
             * 多线程访问下，可能出现if同时成立的情况，添加锁
             */
            synchronized (SingletonLazy.class) {
                // 双重验证
                if (singletonLazy == null) {
                    singletonLazy = new SingletonLazy();
                }
            }
        }
        return singletonLazy;
    }
}
