package com.kittycoder.api.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by shucheng on 2019/3/12 下午 17:39
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TaskSearch {

    @ApiModelProperty(value = "待办id")
    private String taskId;

    @ApiModelProperty(value = "待办名称（节点名称）")
    private String taskName;

    @ApiModelProperty(value = "审批人")
    private String assignee;

    @ApiModelProperty(value = "节点id")
    private String taskDefinitionKey;
}
