package com.kittycoder.api.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * Created by shucheng on 2019/3/11 下午 23:09
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DeploymentExt {

    private String id;

    private String name;

    private Date deploymentTime;
}
