package com.kittycoder.api.util;

import org.camunda.bpm.engine.runtime.ProcessInstanceModificationBuilder;

/**
 * Created by shucheng on 2019-7-12 下午 15:54
 */
public class RuntimeServiceUtil extends ServiceUtil {

    public static ProcessInstanceModificationBuilder getProcessInstanceModificationByInstId(String processInstanceId) {
        return getRuntimeService().createProcessInstanceModification(processInstanceId);
    }

    /**
     * 跳转到指定节点
     * @param processInstanceId 流程实例id
     * @param activityId 目标节点taskDefinitionKey
     * @param currentActivityId 当前节点taskDefinitionKey
     */
    public static void turnNode(String processInstanceId, String activityId, String currentActivityId) {
        getProcessInstanceModificationByInstId(processInstanceId)
                .startBeforeActivity(activityId)
                .cancelAllForActivity(currentActivityId)
                .executeAsync(true, true);

        /**
         * 跳转到指定节点的源码分析
         * 1. 调用startBeforeActivity
         * ActivityBeforeInstantiationCmd	Task_manager（目标节点）
         * args（processInstanceId，activityId 目标节点taskDefinitionKey）
         * 将命令添加到operations中
         *
         * 2. 调用cancelAllForActivity
         * ActivityCancellationCmd	Task_projectLeader（当前节点）
         * args（processInstanceId，activityId 当前节点taskDefinitionKey）
         * 将命令添加到operations中
         *
         * 3. 执行命令
         * ModifyProcessInstanceAsyncCmd
         */
    }

    /**
     * 跳转到指定连线
     * @param processInstanceId 流程实例id
     * @param sequenceId 目标连线taskDefinitionKey
     * @param currentActivityId 当前节点taskDefinitionKey
     */
    public static void turnLine(String processInstanceId, String sequenceId, String currentActivityId) {
        getProcessInstanceModificationByInstId(processInstanceId)
                .startTransition(sequenceId)
                .cancelAllForActivity(currentActivityId)
                .executeAsync(true, true);
    }
}
