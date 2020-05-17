package com.bjsxt.pojo;

import java.util.Map;

/**
 * Created by shucheng on 2019-3-28 下午 18:35
 * log搜索条件
 */
public class LogSearch {

    private Double money;
    private Map<String, String> params;

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    public Map<String, String> getParams() {
        return params;
    }

    public void setParams(Map<String, String> params) {
        this.params = params;
    }
}
