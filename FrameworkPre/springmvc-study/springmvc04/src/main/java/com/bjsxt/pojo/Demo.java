package com.bjsxt.pojo;

import java.util.List;

/**
 * Created by shucheng on 2019-5-13 下午 22:04
 */
public class Demo {

    private People peo;
    private List<People> peoList;

    public People getPeo() {
        return peo;
    }

    public void setPeo(People peo) {
        this.peo = peo;
    }

    public List<People> getPeoList() {
        return peoList;
    }

    public void setPeoList(List<People> peoList) {
        this.peoList = peoList;
    }

    @Override
    public String toString() {
        return "Demo{" +
                "peo=" + peo +
                ", peoList=" + peoList +
                '}';
    }
}
