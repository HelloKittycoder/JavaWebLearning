package com.kittycoder.api.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * Created by shucheng on 2019/3/12 下午 17:42
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TaskExt {
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
     * 待办创建时间
     */
    private Date createTime;
}
