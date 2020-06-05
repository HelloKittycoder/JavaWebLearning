package com.bjsxt.pojo;

/**
 * Created by shucheng on 2020/6/5 22:34
 */
public class RelatedInfo {

    private String originalJson;
    private String className;
    private String teacherName;

    public RelatedInfo() {
    }

    public RelatedInfo(String originalJson, String className, String teacherName) {
        this.className = className;
        this.teacherName = teacherName;
    }

    public String getOriginalJson() {
        return originalJson;
    }

    public void setOriginalJson(String originalJson) {
        this.originalJson = originalJson;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    @Override
    public String toString() {
        return "RelatedInfo{" +
                "originalJson='" + originalJson + '\'' +
                ", className='" + className + '\'' +
                ", teacherName='" + teacherName + '\'' +
                '}';
    }
}
