package com.kittycoder.common.enums;

/**
 * Created by shucheng on 2019/3/8 下午 18:11
 */
public enum  ResultEnum {
    SUCCESS("1", "成功"), FAIL("0", "失败");

    private String value;
    private String text;

    ResultEnum(String value, String text) {
        this.value = value;
        this.text = text;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
