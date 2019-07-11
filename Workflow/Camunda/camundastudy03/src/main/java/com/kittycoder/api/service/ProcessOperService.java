package com.kittycoder.api.service;

/**
 * Created by shucheng on 2019-7-10 上午 10:25
 */
public interface ProcessOperService {

    /**
     * 跳转到指定节点
     * @param processInstanceId 流程实例id
     * @param activityId 目标节点taskDefinitionKey
     * @param currentActivityId 当前节点taskDefinitionKey
     */
    void turnNode(String processInstanceId, String activityId, String currentActivityId);

    /**
     * 跳转到指定连线
     * @param processInstanceId 流程实例id
     * @param sequenceId 目标连线taskDefinitionKey
     * @param currentActivityId 当前节点taskDefinitionKey
     */
    void turnLine(String processInstanceId, String sequenceId, String currentActivityId);
}
