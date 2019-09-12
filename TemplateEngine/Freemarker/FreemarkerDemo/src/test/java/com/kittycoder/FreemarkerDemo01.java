package com.kittycoder;

import com.alibaba.fastjson.JSON;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.junit.Test;

import java.io.StringReader;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by shucheng on 2019-9-12 下午 17:04
 */
public class FreemarkerDemo01 {

    // 替换字符串
    @Test
    public void test() throws Exception {
        Map<String, String> map = new HashMap<String, String>();
        map.put("username", "lisi");
        String templateString = "欢迎${username}登录！";
        StringWriter result = new StringWriter();
        Template t = new Template("template", new StringReader(templateString), new Configuration(Configuration.VERSION_2_3_23));
        t.process(map, result);
        System.out.println(result.toString());
    }

    // 替换模板中的占位符，返回字符串
    // https://blog.csdn.net/weixin_38429587/article/details/89477373
    @Test
    public void test2() throws Exception {
        // 参考链接：http://www.028888.net/archives/2016_05_1016.html
        // new Configuration方法已经过时
        // 设置Configuration
        Configuration configuration = new Configuration(Configuration.VERSION_2_3_23);
        configuration.setDefaultEncoding("UTF-8");
        // 根据classloader加载模板文件
        configuration.setClassLoaderForTemplateLoading(ClassLoader.getSystemClassLoader(), "/");

        // 生成模板对象
        String templateFileName = "option.ftl";
        Template template = configuration.getTemplate(templateFileName);
        StringWriter stringWriter = new StringWriter();

        Map<String, Object> map = new HashMap<String, Object>();
        String[] xAxisData = new String[]{"衬衫", "羊毛衫", "雪纺衫",
                "裤子", "鞋子","袜子"};
        map.put("xAxisData", JSON.toJSONString(xAxisData));
        int[] seriesData = new int[]{5, 20, 36, 10, 10, 20};
        map.put("seriesData", JSON.toJSONString(seriesData));

        template.process(map, stringWriter);
        System.out.println(stringWriter.getBuffer().toString());
    }
}
