package com.kittycoder.quartz.util;

import com.kittycoder.quartz.po.SysJob;
import org.quartz.JobExecutionContext;
import org.quartz.StatefulJob;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by shucheng on 2020/5/16 12:14
 * 定时任务处理（禁止并发执行）
 * https://blog.csdn.net/ffggnfgf/article/details/82734070
 */
public class QuartzDisallowConcurrentExecution extends AbstractQuartzJob implements StatefulJob {

    private static final Logger log = LoggerFactory.getLogger(QuartzDisallowConcurrentExecution.class);

    @Override
    protected void doExecute(JobExecutionContext context, SysJob sysJob) throws Exception {
        log.debug("context=={}**sysJob=={}", context, sysJob);
    }
}
