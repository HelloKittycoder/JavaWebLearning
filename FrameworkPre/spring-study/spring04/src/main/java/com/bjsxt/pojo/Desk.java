package com.bjsxt.pojo;

/**
 * Created by shucheng on 2019-5-7 下午 20:38
 */
public class Desk {
    private int id;
    private double price;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Desk{" +
                "id=" + id +
                ", price=" + price +
                '}';
    }
}
