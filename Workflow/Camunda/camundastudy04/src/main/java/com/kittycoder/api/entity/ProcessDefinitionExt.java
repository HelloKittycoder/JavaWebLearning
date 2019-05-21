package com.kittycoder.api.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by shucheng on 2019/3/11 下午 22:14
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProcessDefinitionExt {
    private String id;
    private String key;
    private String name;
    private int version;
    private String category;
    private String deploymentId;
    private String resourceName;
    private Integer historyLevel;
    private String diagramResourceName;
    private String tenantId;
}
