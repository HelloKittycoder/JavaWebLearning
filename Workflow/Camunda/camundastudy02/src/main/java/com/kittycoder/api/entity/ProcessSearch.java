package com.kittycoder.api.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by shucheng on 2019/3/13 上午 10:01
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProcessSearch {

    @ApiModelProperty(value = "流程实例id")
    private String processInstanceId;

    @ApiModelProperty(value = "流程定义key")
    private String processDefinitionKey;

    @ApiModelProperty(value = "流程定义id")
    private String processDefinitionId;

    @ApiModelProperty(value = "部署id")
    private String deploymentId;
}
