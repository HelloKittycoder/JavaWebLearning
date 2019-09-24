package com.kittycoder.part02.ch13;

/**
 * Created by shucheng on 2019-9-24 上午 8:57
 */
public class Task implements Comparable<Task> {

    private int id;
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int compareTo(Task task) {
        return this.id > task.id ? 1 : (this.id < task.id ? -1 : 0);
    }

    @Override
    public String toString() {
        return this.id + "," + this.name;
    }
}
