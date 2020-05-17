package com.kittycoder.autoconfigure;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Created by shucheng on 2020/2/13 14:53
 */
@ConfigurationProperties("kittycoder")
public class HelloProperties {

    private String prefix;
    private String suffix;

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }
}
