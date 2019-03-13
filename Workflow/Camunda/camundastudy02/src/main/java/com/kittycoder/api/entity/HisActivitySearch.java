package com.kittycoder.api.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by shucheng on 2019/3/13 上午 9:44
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class HisActivitySearch {

    @ApiModelProperty(value = "活动实例id")
    private String activityInstanceId;

    @ApiModelProperty(value = "流程实例id")
    private String processInstanceId;

    @ApiModelProperty(value = "流程定义id")
    private String processDefinitionId;

    @ApiModelProperty(value = "执行分支id")
    private String executionId;

    @ApiModelProperty(value = "活动id")
    private String activityId;

    @ApiModelProperty(value = "活动名称")
    private String activityName;

    @ApiModelProperty(value = "活动类型")
    private String activityType;

    @ApiModelProperty(value = "审批人")
    private String taskAssignee;

    /*@ApiModelProperty(value = "是否完成")
    private boolean isFinished;

    @ApiModelProperty(value = "是否未完成")
    private boolean isUnfinished;

    @ApiModelProperty(value = "是否被取消")
    private boolean isCanceled;*/
}
