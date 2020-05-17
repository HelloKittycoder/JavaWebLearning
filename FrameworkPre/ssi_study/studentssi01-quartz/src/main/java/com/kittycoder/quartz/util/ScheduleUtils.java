package com.kittycoder.quartz.util;

import com.kittycoder.common.constant.ScheduleConstants;
import com.kittycoder.quartz.po.SysJob;
import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;

/**
 * Created by shucheng on 2020/5/17 5:16
 */
public class ScheduleUtils {

    private static final Logger log = LoggerFactory.getLogger(ScheduleUtils.class);

    /**
     * 得到quartz任务类
     * @param sysJob 执行计划
     * @return 具体执行任务类
     */
    private static Class<? extends Job> getQuartzJobClass(SysJob sysJob) {
        boolean isConcurrent = "0".equals(sysJob.getConcurrent());
        return isConcurrent ? QuartzJobExecution.class : QuartzDisallowConcurrentExecution.class;
    }

    /**
     * 获取触发器名
     */
    public static String getTriggerName(Long jobId) {
        return ScheduleConstants.TASK_CLASS_NAME + jobId;
    }

    /**
     * 获取任务名
     */
    public static String getJobName(Long jobId) {
        return ScheduleConstants.TASK_CLASS_NAME + jobId;
    }

    /**
     * 获取表达式触发器
     */
    public static CronTrigger getCronTrigger(Scheduler scheduler, Long jobId) {
        try {
            return (CronTrigger) scheduler.getTrigger(getTriggerName(jobId), null);
        } catch (SchedulerException e) {
            log.error("getCronTrigger异常：", e);
        }
        return null;
    }

    /**
     * 创建定时任务
     */
    public static void createSchedulerJob(Scheduler scheduler, SysJob job) throws SchedulerException, ParseException {
        Class<? extends Job> jobClass = getQuartzJobClass(job);
        // 任务名、任务组、任务执行类
        JobDetail jobDetail = new JobDetail(ScheduleConstants.TASK_CLASS_NAME + job.getJobId(), null, jobClass);
        // 触发器名，触发器组
        CronTrigger trigger = new CronTrigger(ScheduleConstants.TASK_CLASS_NAME + job.getJobId(), null);
        // 触发器表达式
        trigger.setCronExpression(job.getCronExpression());

        // 放入参数，运行时的方法可以获取
        jobDetail.getJobDataMap().put(ScheduleConstants.TASK_PROPERTIES, job);
        scheduler.scheduleJob(jobDetail, trigger);

        // 暂停任务
        if (ScheduleConstants.Status.PAUSE.getValue().equals(job.getStatus())) {
            pauseJob(scheduler, job.getJobId());
        }
    }

    /**
     * 更新定时任务
     * @param scheduler
     * @param job
     */
    public static void updateSchedulerJob(Scheduler scheduler, SysJob job) throws SchedulerException, ParseException {
        String jobName = getJobName(job.getJobId());

        // 判断是否存在
        if (checkExists(scheduler, jobName, null)) {
            // 先移除，然后做更新操作
            scheduler.deleteJob(jobName, null);
        }

        createSchedulerJob(scheduler, job);

        // 暂停任务
        if (ScheduleConstants.Status.PAUSE.getValue().equals(job.getStatus())) {
            pauseJob(scheduler, job.getJobId());
        }
    }

    /**
     * 立即执行任务
     * @param scheduler
     * @param job
     */
    public static void run(Scheduler scheduler, SysJob job) throws SchedulerException {
        // 参数
        JobDataMap dataMap = new JobDataMap();
        dataMap.put(ScheduleConstants.TASK_PROPERTIES, job);

        scheduler.triggerJob(getJobName(job.getJobId()), null, dataMap);
    }

    /**
     * 暂停任务
     */
    public static void pauseJob(Scheduler scheduler, Long jobId) throws SchedulerException {
        scheduler.pauseJob(getJobName(jobId), null);
    }

    /**
     * 恢复任务
     */
    public static void resumeJob(Scheduler scheduler, Long jobId) throws SchedulerException {
        scheduler.resumeJob(getJobName(jobId), null);
    }

    /**
     * 删除定时任务
     */
    public static void deleteScheduleJob(Scheduler scheduler, Long jobId) throws SchedulerException {
        scheduler.deleteJob(getJobName(jobId), null);
    }

    /**
     * 校验job是否存在
     * @param scheduler 调度器
     * @param jobName 任务名
     * @param groupName 任务组
     * @return
     * @throws SchedulerException
     */
    public static boolean checkExists(Scheduler scheduler, String jobName, String groupName) throws SchedulerException {
        String[] jobNames = scheduler.getJobNames(groupName);
        for (String jname : jobNames) {
            if (jname.equals(jobName)) {
                return true;
            }
        }
        return false;
    }
}
