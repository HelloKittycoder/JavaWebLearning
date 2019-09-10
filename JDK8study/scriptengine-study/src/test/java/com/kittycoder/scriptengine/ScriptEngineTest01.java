package com.kittycoder.scriptengine;

import org.junit.Test;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineFactory;
import javax.script.ScriptEngineManager;
import java.util.Date;
import java.util.List;

/**
 * Created by shucheng on 2019-9-10 下午 21:27
 * 参考链接：https://blog.csdn.net/jianggujin/article/details/51046122
 */
public class ScriptEngineTest01 {

    // 查看JDK为我们提供了哪些可用的脚本引擎工厂
    @Test
    public void getScriptEngineFactory() throws Exception {
        ScriptEngineManager manager = new ScriptEngineManager();
        List<ScriptEngineFactory> factories = manager.getEngineFactories();
        for (ScriptEngineFactory factory: factories) {
            System.out.println(factory.getNames());
        }
    }

    // 执行js表达式
    @Test
    public void invokeExpression() throws Exception {
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("js");
        String js = "1 + 2";
        Integer result = (Integer) engine.eval(js);
        System.out.println(result);
    }

    // 执行js函数
    @Test
    public void invokeFunction() throws Exception {
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("js");
        String js = "function welcom() {return 'welcome'}";
        engine.eval(js);
        Invocable invocable = (Invocable) engine;
        String result = (String) invocable.invokeFunction("welcom");
        System.out.println(result);
    }

    // 执行带参的js函数
    @Test
    public void invokeFunctionWithParam() throws Exception {
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("js");
        String js = "function welcom(name){return 'welcome ' + name;}";
        engine.eval(js);
        Invocable invocable = (Invocable) engine;
        String result = (String) invocable.invokeFunction("welcom", "zhangsan");
        System.out.println(result);
    }

    // 将java对象注入到js代码中运行
    @Test
    public void inject() throws Exception {
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("js");
        Date date = new Date();
        System.out.println(date.getTime());
        engine.put("date", date);
        String js = "function getTime(){return date.getTime();}";
        engine.eval(js);
        Invocable invocable = (Invocable) engine;
        Long result = (Long) invocable.invokeFunction("getTime");
        System.out.println(result);
    }

    // 通过线程启动js函数
    @Test
    public void runThread() throws Exception {
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("js");
        engine.put("out", System.out);
        String js= "var obj=new Object();obj.run=function(){out.println('thread...')}";
        engine.eval(js);
        Object obj = engine.get("obj");
        Invocable inv = (Invocable) engine;
        Runnable r = inv.getInterface(obj, Runnable.class);
        Thread t = new Thread(r);
        t.start();
    }
}
