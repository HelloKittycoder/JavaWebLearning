package com.kittycoder.api.service.impl;

import com.kittycoder.api.service.ProcessOperService;
import com.kittycoder.api.util.RuntimeServiceUtil;
import org.springframework.stereotype.Service;

/**
 * Created by shucheng on 2019-7-10 上午 10:26
 */
@Service
public class ProcessOperServiceImpl implements ProcessOperService {

    @Override
    // @Transactional(rollbackFor = {Exception.class})
    public void turnNode(String processInstanceId, String activityId, String currentActivityId) {
        RuntimeServiceUtil.turnNode(processInstanceId, activityId, currentActivityId);
        /*System.out.println(1 / 0);
        throw new RuntimeException("计算错误");*/
        // 注释的部分Transactional和上面一小段，是用来制造运行时异常，让流程操作回滚的
    }

    @Override
    // @Transactional(rollbackFor = {Exception.class})
    public void turnLine(String processInstanceId, String sequenceId, String currentActivityId) {
        RuntimeServiceUtil.turnLine(processInstanceId, sequenceId, currentActivityId);
        /*System.out.println(1 / 0);
        throw new RuntimeException("计算错误");*/
    }
}
