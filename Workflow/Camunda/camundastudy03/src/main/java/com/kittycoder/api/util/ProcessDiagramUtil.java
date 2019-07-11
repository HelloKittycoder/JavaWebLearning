package com.kittycoder.api.util;

import org.apache.commons.lang3.StringUtils;
import org.camunda.bpm.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.camunda.bpm.engine.impl.pvm.PvmTransition;
import org.camunda.bpm.engine.impl.pvm.process.ActivityImpl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by shucheng on 2019-7-9 上午 11:11
 * 流程图解析工具类
 */
public class ProcessDiagramUtil extends ServiceUtil {

    /**
     * 根据任务id和节点id获取活动节点
     * @param taskId
     * @param activityId 节点的taskDefinitionKey
     *                   如果为null或""，则查询当前活动节点
     *                   如果为“end”，则查询结束节点
     * @return
     */
    public static ActivityImpl findActivityImpl(String taskId, String activityId) {
        // 获取流程定义
        ProcessDefinitionEntity pd = findProcessDefinitionEntityByTaskId(taskId);

        // 获取当前活动节点id
        if (StringUtils.isEmpty(activityId)) {
            activityId = TaskServiceUtil.getTaskByTaskId(taskId).getTaskDefinitionKey();
        }

        // 根据流程定义，获取该流程实例的结束节点
        if ("end".equals(activityId)) {
            for (ActivityImpl activity : pd.getActivities()) {
                List<PvmTransition> outgoingTransitions = activity.getOutgoingTransitions();
                if (outgoingTransitions.isEmpty()) {
                    return activity;
                }
            }
        }

        ActivityImpl activityImpl = pd.findActivity(activityId);
        if (activityImpl.isMultiInstance() && activityImpl instanceof ActivityImpl) {
            return (ActivityImpl) activityImpl.getFlowScope();
        }
        return activityImpl;
    }

    /**
     * 根据任务id获取流程定义
     * @param taskId
     * @return
     */
    public static ProcessDefinitionEntity findProcessDefinitionEntityByTaskId(String taskId) {
        String processDefinitionId = TaskServiceUtil.getTaskByTaskId(taskId).getProcessDefinitionId();
        return (ProcessDefinitionEntity) getRepositoryService().getProcessDefinition(processDefinitionId);
    }

    /**
     * 根据taskId查询该流程实例中所有可以驳回的节点集合（只取出UserTask类型的节点）
     * 即获取当前task对应节点之前的所有节点集合
     * @param taskId
     * @return
     */
    public static List<ActivityImpl> findBackActivity(String taskId) {
        ActivityImpl currActivity = findActivityImpl(taskId, null); // 获取当前taskId对应的activity
        List<ActivityImpl> resultList = iterateBackActivity(currActivity, new ArrayList<>(), new ArrayList<>());
        Collections.reverse(resultList);
        return resultList;
    }

    /**
     * 迭代循环流程树结构，查询当前节点可驳回的任务节点（真正递归操作的部分）
     * https://blog.csdn.net/x9x9x9x9/article/details/9200329
     * @param currActivity
     * @param rtnList
     * @param tempList
     * @return
     */
    public static List<ActivityImpl> iterateBackActivity(ActivityImpl currActivity, List<ActivityImpl> rtnList,
                                                          List<ActivityImpl> tempList) {
        // 当前节点的所有流入连线
        List<PvmTransition> incomingTransitions = currActivity.getIncomingTransitions();
        for (PvmTransition pvmTransition : incomingTransitions) {
            ActivityImpl activityImpl = (ActivityImpl) pvmTransition.getSource();
            if (!tempList.contains(activityImpl)) {
                if (TaskServiceUtil.isUserTask(activityImpl)) {
                    rtnList.add(activityImpl);
                } else if (TaskServiceUtil.isMultiInstanceBody(activityImpl)) {
                    for (ActivityImpl instanceBodyActivity : activityImpl.getActivities()) {
                        if (TaskServiceUtil.isUserTask(instanceBodyActivity)) {
                            // 加完一个直接跳出循环
                            rtnList.add(instanceBodyActivity);
                            break;
                        }
                    }
                } else {
                }
                tempList.add(activityImpl);
                iterateBackActivity(activityImpl, rtnList, tempList);
            }
        }

        return rtnList;
    }
}
