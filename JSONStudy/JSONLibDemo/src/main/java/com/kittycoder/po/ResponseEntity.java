package com.kittycoder.po;

/**
 * Created by shucheng on 2020/9/11 17:27
 */
public class ResponseEntity<T> {

    private String code;
    private String message;
    private T t;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getT() {
        return t;
    }

    public void setT(T t) {
        this.t = t;
    }
}
