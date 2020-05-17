package com.kittycoder.quartz.po;

import com.kittycoder.common.constant.ScheduleConstants;

import java.io.Serializable;

/**
 * Created by shucheng on 2020/4/21 0:16
 */
public class SysJob implements Serializable {

    private static final long serialVersionUID = 3245023530082754563L;
    private Long jobId; // 任务序号
    private String jobName; // 任务名称
    private String jobGroup; // 任务组名
    private String methodName; // 任务方法
    private String methodParams; // 方法参数
    private String cronExpression; // cron执行表达式
    private String misfirePolicy = ScheduleConstants.MISFIRE_DEFAULT; // cron计划策略
    private String concurrent; // 是否并发执行（0允许 1禁止，默认是禁止）
    private String status; // 任务状态（0正常 1暂停）

    public Long getJobId() {
        return jobId;
    }

    public void setJobId(Long jobId) {
        this.jobId = jobId;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getJobGroup() {
        return jobGroup;
    }

    public void setJobGroup(String jobGroup) {
        this.jobGroup = jobGroup;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public String getMethodParams() {
        return methodParams;
    }

    public void setMethodParams(String methodParams) {
        this.methodParams = methodParams;
    }

    public String getCronExpression() {
        return cronExpression;
    }

    public void setCronExpression(String cronExpression) {
        this.cronExpression = cronExpression;
    }

    public String getMisfirePolicy() {
        return misfirePolicy;
    }

    public void setMisfirePolicy(String misfirePolicy) {
        this.misfirePolicy = misfirePolicy;
    }

    public String getConcurrent() {
        return concurrent;
    }

    public void setConcurrent(String concurrent) {
        this.concurrent = concurrent;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
