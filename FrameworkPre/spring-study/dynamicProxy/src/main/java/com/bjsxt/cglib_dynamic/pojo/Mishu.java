package com.bjsxt.cglib_dynamic.pojo;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * Created by shucheng on 2019-5-10 下午 14:45
 */
public class Mishu implements MethodInterceptor {
    /**
     * 代理方法拦截
     * @param o 生成的子类对象（代理对象）
     * @param method 代理的真实方法
     * @param objects 参数
     * @param methodProxy 子类生成的代理方法
     * @return
     * @throws Throwable
     */
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("预约时间");
        // invoke()调用子类重写的方法，invokeSuper()调用父类方法
        Object result = methodProxy.invokeSuper(o, objects);

        // Object result = method.invoke(o, objects); // 这种写法也会造成死循环
        // Object result = methodProxy.invoke(o, objects); // 子类调用子类的方法，造成死循环
        System.out.println("备注");
        return result;
    }
}
