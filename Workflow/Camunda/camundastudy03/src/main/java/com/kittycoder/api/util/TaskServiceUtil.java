package com.kittycoder.api.util;

import org.camunda.bpm.engine.history.HistoricTaskInstance;
import org.camunda.bpm.engine.impl.pvm.process.ActivityImpl;
import org.camunda.bpm.engine.runtime.ProcessInstance;
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

    // 根据待办id获取流程实例
    public static ProcessInstance findProcessInstanceByTaskId(String taskId) {
        String processInstanceId = getTaskByTaskId(taskId).getProcessInstanceId();
        return getProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();
    }

    // 获取活动节点的属性
    public static Object getActivityProperty(ActivityImpl activity, String propertyName) {
        return activity.getProperty(propertyName);
    }

    // 判断节点是否为指定类型
    public static boolean isSpecificType(ActivityImpl activity, String type) {
        return type.equals(getActivityProperty(activity, "type"));
    }

    // 判断节点是否为userTask
    public static boolean isUserTask(ActivityImpl activity) {
        return isSpecificType(activity, "userTask");
    }

    // 判断节点是否为multiInstanceBody
    public static boolean isMultiInstanceBody(ActivityImpl activity) {
        return isSpecificType(activity, "multiInstanceBody");
    }

    // 判断节点是否为排他网关
    public static boolean isExclusiveGateway(ActivityImpl activity) {
        return isSpecificType(activity, "exclusiveGateway");
    }
}
