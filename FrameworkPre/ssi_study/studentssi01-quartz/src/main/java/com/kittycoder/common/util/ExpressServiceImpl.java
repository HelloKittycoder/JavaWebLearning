package com.kittycoder.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.expression.MapAccessor;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.ParserContext;
import org.springframework.expression.common.TemplateParserContext;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

/**
 * Created by shucheng on 2020/5/17 23:45
 */
public class ExpressServiceImpl {

    private static final Logger log = LoggerFactory.getLogger(ExpressServiceImpl.class);

    private ExpressionParser spelExpressionParser;

    private ParserContext parserContext;
    // 表达式解析上下文
    private StandardEvaluationContext evaluationContext;

    public static enum ExpressType {
        /**
         * ${}表达式格式
         */
        TYPE_FIRST,
        /**
         * #{}表达式格式
         */
        TYPE_SECOND
    }

    private static final String PRE_TYPE_1 = "${";
    private static final String PRE_TYPE_2 = "#{";
    private static final String SUF_STR = "}";

    private ExpressServiceImpl(String pre, String suf) {
        spelExpressionParser = new SpelExpressionParser();
        log.debug("表达式前缀={},表达式后缀={}", pre, suf);
        evaluationContext = new StandardEvaluationContext();
        // 增加map解析方案
        evaluationContext.addPropertyAccessor(new MapAccessor());
        parserContext = new TemplateParserContext(pre, suf);
    }

    /**
     *
     * <p>
     * 创建表达式处理服务对象 默认为创建#{}格式表达式 通过ExpressType指定表达式格式，现有两种${}和#{}
     * </p>
     *
     *
     * @param type
     *            表达式格式类型
     * @return 表达式解析对象
     */
    public static ExpressServiceImpl createExpressService(ExpressType type) {
        if (type == ExpressType.TYPE_FIRST) {
            log.debug("生成表达式，表达式前缀={}", PRE_TYPE_1);
            return new ExpressServiceImpl(PRE_TYPE_1, SUF_STR);
        } else {
            return new ExpressServiceImpl(PRE_TYPE_2, SUF_STR);
        }

    }

    public Object expressParse(String express, Object data) throws Exception {
        log.debug("解析表达式信息={}", express);
        Expression expression = spelExpressionParser.parseExpression(express, this.parserContext);

        return expression.getValue(evaluationContext, data);
    }
}
