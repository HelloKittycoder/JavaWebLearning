package com.kittycoder.quartz.util;

import com.kittycoder.quartz.po.SysJob;
import org.quartz.JobExecutionContext;

/**
 * Created by shucheng on 2020/5/16 12:04
 * 定时任务处理（允许并发执行）
 */
public class QuartzJobExecution extends AbstractQuartzJob {
    @Override
    protected void doExecute(JobExecutionContext context, SysJob sysJob) throws Exception {
        System.out.println("context" + context + "sysJob" + sysJob + "调用QuartzJobExecution");
    }
}
