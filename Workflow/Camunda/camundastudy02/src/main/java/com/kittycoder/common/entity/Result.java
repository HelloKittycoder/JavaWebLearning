package com.kittycoder.common.entity;

import java.io.Serializable;

/**
 * Created by shucheng on 2019/3/8 下午 18:01
 */
public class Result implements Serializable {

    private static final long serialVersionUID = 6288374846131788743L;

    private String message;
    private String resultCode;
    private Object data;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
