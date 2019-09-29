package com.kittycoder.runtime;

import java.io.Serializable;

/**
 * Created by shucheng on 2019-9-29 下午 16:03
 */
public class Request implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;
    private String name;
    private String requestMessage;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRequestMessage() {
        return requestMessage;
    }

    public void setRequestMessage(String requestMessage) {
        this.requestMessage = requestMessage;
    }
}
