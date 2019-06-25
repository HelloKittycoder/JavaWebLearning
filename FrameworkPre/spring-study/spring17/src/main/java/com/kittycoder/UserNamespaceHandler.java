package com.kittycoder;

import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

/**
 * Created by shucheng on 2019-6-18 下午 22:54
 */
public class UserNamespaceHandler extends NamespaceHandlerSupport {
    @Override
    public void init() {
        registerBeanDefinitionParser("user", new UserDefinitionParser());
    }
}
