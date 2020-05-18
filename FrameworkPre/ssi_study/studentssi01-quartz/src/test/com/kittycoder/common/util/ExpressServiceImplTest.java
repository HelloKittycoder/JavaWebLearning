package com.kittycoder.common.util;

import com.kittycoder.quartz.util.ScheduleUtilsTest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.expression.BeanFactoryAccessor;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ParserContext;
import org.springframework.expression.common.TemplateParserContext;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import java.util.HashMap;

/**
 * Created by shucheng on 2020/5/18 8:31
 *
 * https://blog.csdn.net/tianlong1569/article/details/98509392
 * https://blog.csdn.net/pengyuanyuankuang/article/details/84301633
 * https://www.cnblogs.com/moonlightpoet/p/5541010.html
 */
public class ExpressServiceImplTest {

    private static final Logger log = LoggerFactory.getLogger(ScheduleUtilsTest.class);

    public String exec(String para) {
        return para;
    }

    public static void main(String[] args) {
        testExpressBean();
        // testExpressParse();
    }

    // 解析带有spring bean的表达式
    public static void testExpressBean() {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath*:applicationContext*.xml");
        System.out.println(context);

        BeanFactory beanFactory = context.getAutowireCapableBeanFactory();
        System.out.println(beanFactory);

        SpelExpressionParser spelExpressionParser = new SpelExpressionParser();
        StandardEvaluationContext evaluationContext = new StandardEvaluationContext();
        evaluationContext.addPropertyAccessor(new BeanFactoryAccessor());
        ParserContext parserContext = new TemplateParserContext("${", "}");
        // Expression expression = spelExpressionParser.parseExpression("#{studentService.findAll(null)}", parserContext);
        Expression expression = spelExpressionParser.parseExpression("${studentService.findById(2)}", parserContext);
        System.out.println("studentService="+expression.getValue(evaluationContext, beanFactory));
    }

    // 解析普通表达式
    public static void testExpressParse() {
        HashMap<String,String> map = new HashMap<>();
        map.put("name","12");
        map.put("mobile","13035353526");
        ExpressServiceImpl express = ExpressServiceImpl.createExpressService(null);
        try {
            System.out.println("mobile="+express.expressParse("#{new com.kittycoder.common.util.ExpressServiceImplTest().exec(mobile)}", map));
            System.out.println("name="+express.expressParse("#{new com.kittycoder.common.util.ExpressServiceImplTest().exec(name)}", map));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
