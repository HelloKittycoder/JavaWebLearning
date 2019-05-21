package com.kittycoder.api.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by shucheng on 2019/3/13 上午 10:13
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProcessInstanceExt {

    private String processDefinitionId;
    private String businessKey;
    private String rootProcessInstanceId;
    private boolean isSuspended;
    private String processInstanceId;
}
