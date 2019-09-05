package com.bjsxt.pojo;

/**
 * Create by Administrator on 2019/3/20
 */
public class Log {

    private Integer id;
    private String accIn;
    private String accOut;
    private double money;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAccIn() {
        return accIn;
    }

    public void setAccIn(String accIn) {
        this.accIn = accIn;
    }

    public String getAccOut() {
        return accOut;
    }

    public void setAccOut(String accOut) {
        this.accOut = accOut;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    @Override
    public String toString() {
        return "Log{" +
                "id=" + id +
                ", accIn='" + accIn + '\'' +
                ", accOut='" + accOut + '\'' +
                ", money=" + money +
                '}';
    }
}
