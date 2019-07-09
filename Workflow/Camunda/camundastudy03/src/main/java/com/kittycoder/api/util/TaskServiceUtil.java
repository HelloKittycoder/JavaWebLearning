package com.kittycoder.api.util;

import org.camunda.bpm.engine.history.HistoricTaskInstance;
import org.camunda.bpm.engine.task.Task;

/**
 * Created by shucheng on 2019-7-9 下午 13:24
 */
public class TaskServiceUtil extends ServiceUtil {

    // 根据待办id获取历史任务实例
    public static HistoricTaskInstance getHistoricTaskInstanceByTaskId(String taskId) {
        return getHistoricTaskInstanceQuery().taskId(taskId).singleResult();
    }

    // 根据待办id获取运行中的待办
    public static Task getTaskByTaskId(String taskId) {
        return getTaskQuery().taskId(taskId).singleResult();
    }
}
