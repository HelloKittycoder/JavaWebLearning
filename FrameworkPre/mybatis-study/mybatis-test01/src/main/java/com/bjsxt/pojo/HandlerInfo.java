package com.bjsxt.pojo;

/**
 * Created by shucheng on 2020/6/5 22:34
 */
public class HandlerInfo {

    private String id;
    private String name;
    private String sex;
    private String age;

    private RelatedInfo relatedInfo;

    public HandlerInfo() {
    }

    public HandlerInfo(String id, String name, String sex, String age) {
        this.id = id;
        this.name = name;
        this.sex = sex;
        this.age = age;
    }

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

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public RelatedInfo getRelatedInfo() {
        return relatedInfo;
    }

    public void setRelatedInfo(RelatedInfo relatedInfo) {
        this.relatedInfo = relatedInfo;
    }

    @Override
    public String toString() {
        return "HandlerInfo{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", sage='" + age + '\'' +
                ", relatedInfo=" + relatedInfo +
                '}';
    }
}
