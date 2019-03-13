package com.kittycoder.api.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * Created by shucheng on 2019/3/13 上午 9:10
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class HisTaskExt {
    /**
     * 待办id
     */
    private String id;
    /**
     * 执行分支id
     */
    private String executionId;
    /**
     * 待办名称
     */
    private String name;
    /**
     * 描述
     */
    private String description;
    /**
     * 审批人
     */
    private String assignee;
    /**
     * 节点id
     */
    private String taskDefinitionKey;
    /**
     * 流程实例id
     */
    private String processInstanceId;
    /**
     * 待办开始时间
     */
    private Date startTime;
    /**
     * 待办结束时间
     */
    private Date endTime;
    /**
     * 活动id
     */
    private String activityInstanceId;
}
