package com.kittycoder.api.controller;

import com.kittycoder.api.util.RepositoryServiceUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Created by shucheng on 2019-5-21 下午 21:08
 * 用来测试工具类的Controller
 */
@RestController
@RequestMapping("/util")
public class ServiceUtilController {

    /**
     * 获取元素的简单信息
     * @param processDefinitionId
     * @param elementId
     */
    @ApiOperation(value = "queryElementSimpleInfoById", notes = "")
    @GetMapping("/queryElementSimpleInfo")
    public void queryElementSimpleInfo(String processDefinitionId, String elementId) {
        Map<String, Object> resultMap = RepositoryServiceUtil.queryElementSimpleInfoById(processDefinitionId, elementId);
        System.out.println(resultMap);
    }
}
