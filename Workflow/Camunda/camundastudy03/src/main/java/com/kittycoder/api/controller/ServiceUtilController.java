package com.kittycoder.api.controller;

import com.kittycoder.api.service.ProcessOperService;
import com.kittycoder.api.util.ProcessDiagramUtil;
import com.kittycoder.api.util.RepositoryServiceUtil;
import io.swagger.annotations.ApiOperation;
import org.camunda.bpm.engine.impl.pvm.process.ActivityImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by shucheng on 2019-5-21 下午 21:08
 * 用来测试工具类的Controller
 */
@RestController
@RequestMapping("/util")
public class ServiceUtilController {

    @Resource
    private ProcessOperService processOperService;

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

    /**
     * 获取可以驳回的所有节点
     * http://localhost:8085/util/iterateBackActivity
     */
    @GetMapping("/iterateBackActivity")
    public void iterateBackActivity() {
        String taskId = "0030ed46-97d7-11e9-94c6-02422b4a0087";
        List<ActivityImpl> list = ProcessDiagramUtil.findBackActivity(taskId);
        System.out.println(list);
    }

    // 跳转节点
    // http://localhost:8085/util/turnNode
    @GetMapping("turnNode")
    public void turnNode(String processInstanceId, String activityId, String currentActivityId) {
        try {
            processOperService.turnNode(processInstanceId, activityId, currentActivityId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("==============turnNode==============");
    }
}
