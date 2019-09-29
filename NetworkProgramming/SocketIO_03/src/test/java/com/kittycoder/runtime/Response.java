package com.kittycoder.runtime;

import java.io.Serializable;

/**
 * Created by shucheng on 2019-9-29 下午 16:06
 */
public class Response implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;
    private String name;
    private String responseMessage;

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

    public String getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }
}
