package com.kittycoder.quartz.util;

import com.kittycoder.common.constant.ScheduleConstants;
import com.kittycoder.quartz.po.SysJob;
import org.quartz.Scheduler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by shucheng on 2020/5/17 12:08
 * 说明：单元测试中无法正常使用任务调度（原因未知），这里直接在main方法里调的
 */
public class ScheduleUtilsTest {

    private static final Logger log = LoggerFactory.getLogger(ScheduleUtilsTest.class);

    public static void main(String[] args) throws Exception {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath*:applicationContext*.xml");
        System.out.println(context);
        Scheduler scheduler = (Scheduler) context.getBean("schedulerFactoryBean");
        /*System.out.println(scheduler);*/

        SysJob job = getDefaultSysJob();
        initScheduler(scheduler, job);

        // 转为单次执行任务
        log.debug("临时触发一次任务start");
        ScheduleUtils.run(scheduler, job);
        log.debug("临时触发一次任务end");


        log.debug("5秒后转为定时任务");
        Thread.sleep(5000);
        ScheduleUtils.resumeJob(scheduler, job.getJobId());

        log.debug("5秒后将暂停执行任务...");
        Thread.sleep(5000);

        log.debug("暂停执行任务3秒start");
        ScheduleUtils.pauseJob(scheduler, job.getJobId());
        Thread.sleep(3000);
        log.debug("暂停执行任务3秒end");

        ScheduleUtils.resumeJob(scheduler, job.getJobId());

        log.debug("5秒后将删除该任务...");
        Thread.sleep(5000);
        log.debug("删除任务start");
        ScheduleUtils.deleteScheduleJob(scheduler, job.getJobId());
        log.debug("删除任务end");
    }

    // 创建Scheduler并启动
    public static void initScheduler(Scheduler scheduler, SysJob job) throws Exception {
        // 如果不存在触发器，则创建
        if (ScheduleUtils.getCronTrigger(scheduler, job.getJobId()) == null) {
            ScheduleUtils.createSchedulerJob(scheduler, job);
        } else {
            ScheduleUtils.updateSchedulerJob(scheduler, job);
        }
        scheduler.start();
    }

    public static SysJob getDefaultSysJob() {
        SysJob job = new SysJob();
        job.setJobId(1L);
        job.setStatus(ScheduleConstants.Status.PAUSE.getValue()); // 暂停
        job.setJobGroup("测试");
        job.setCronExpression("0/5 * * * * ?"); // 每隔1秒执行一次
        return job;
    }
}
