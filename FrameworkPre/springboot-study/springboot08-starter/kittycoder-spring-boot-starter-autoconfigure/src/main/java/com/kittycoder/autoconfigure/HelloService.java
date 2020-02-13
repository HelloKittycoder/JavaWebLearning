package com.kittycoder.autoconfigure;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by shucheng on 2020/2/13 14:53
 */
public class HelloService {

    private HelloProperties helloProperties;

    public void setHelloProperties(HelloProperties helloProperties) {
        this.helloProperties = helloProperties;
    }

    public String sayHello(String name) {
        return helloProperties.getPrefix() + "-" + name + "-" +  helloProperties.getSuffix();
    }
}
