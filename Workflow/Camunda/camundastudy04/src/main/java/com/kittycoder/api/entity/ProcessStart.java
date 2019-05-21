package com.kittycoder.api.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by shucheng on 2019/3/12 上午 9:58
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProcessStart {

    @ApiModelProperty(value = "流程定义id")
    private String processDefinitionId;

    @ApiModelProperty(value = "流程定义key")
    private String processDefinitionKey;

    @ApiModelProperty(value = "业务key")
    private String businessKey;

    @ApiModelProperty(value = "其他变量JSON")
    private String variables;
}
