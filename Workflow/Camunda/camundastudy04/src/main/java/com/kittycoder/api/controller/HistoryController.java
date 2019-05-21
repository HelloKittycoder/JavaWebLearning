package com.kittycoder.api.controller;

import com.kittycoder.api.entity.HisActivitySearch;
import com.kittycoder.api.entity.HisTaskExt;
import com.kittycoder.api.entity.TaskSearch;
import com.kittycoder.common.entity.Result;
import com.kittycoder.common.enums.ResultEnum;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.camunda.bpm.engine.history.HistoricActivityInstance;
import org.camunda.bpm.engine.history.HistoricActivityInstanceQuery;
import org.camunda.bpm.engine.history.HistoricTaskInstance;
import org.camunda.bpm.engine.history.HistoricTaskInstanceQuery;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shucheng on 2019/3/12 下午 22:46
 */
@RestController
@RequestMapping("/history")
public class HistoryController extends BaseController {

    // 查询历史待办信息
    @ApiOperation(value = "查询历史待办信息", notes = "")
    @GetMapping("/findHisTasks")
    public Result findHisTask(TaskSearch taskSearch) {
        Result result = new Result();
        HistoricTaskInstanceQuery htip = historyService.createHistoricTaskInstanceQuery();
        // taskId
        if (StringUtils.isNotBlank(taskSearch.getTaskId())) {
            htip.taskId(taskSearch.getTaskId());
        }
        // taskName
        if (StringUtils.isNotBlank(taskSearch.getTaskName())) {
            htip.taskName(taskSearch.getTaskName());
        }
        // taskDefinitionKey
        if (StringUtils.isNotBlank(taskSearch.getTaskDefinitionKey())) {
            htip.taskDefinitionKey(taskSearch.getTaskDefinitionKey());
        }
        // assignee
        if (StringUtils.isNotBlank(taskSearch.getAssignee())) {
            htip.taskAssignee(taskSearch.getAssignee());
        }
        try {
            List<HistoricTaskInstance> taskList = htip.list();
            // 重新封装
            List<HisTaskExt> resultList = new ArrayList<HisTaskExt>();
            taskList.forEach(t -> {
                HisTaskExt ht = new HisTaskExt();
                BeanUtils.copyProperties(t, ht);
                resultList.add(ht);
            });
            result.setData(resultList);
            result.setResultCode(ResultEnum.SUCCESS.getValue());
        } catch (Exception e)  {
            result.setResultCode(ResultEnum.FAIL.getValue());
            e.printStackTrace();
        }
        return result;
    }

    // 查询历史待办信息
    @ApiOperation(value = "查询节点运行历史信息", notes = "")
    @GetMapping("/findHisActivity")
    public Result findHisActivity(HisActivitySearch hisActivitySearch) {
        Result result = new Result();
        HistoricActivityInstanceQuery haiq = historyService.createHistoricActivityInstanceQuery();
        // activityInstanceId
        if (StringUtils.isNotBlank(hisActivitySearch.getActivityInstanceId())) {
            haiq.activityInstanceId(hisActivitySearch.getActivityInstanceId());
        }
        // processInstanceId
        if (StringUtils.isNotBlank(hisActivitySearch.getProcessInstanceId())) {
            haiq.processInstanceId(hisActivitySearch.getProcessInstanceId());
        }
        // processDefinitionId
        if (StringUtils.isNotBlank(hisActivitySearch.getProcessDefinitionId())) {
            haiq.processDefinitionId(hisActivitySearch.getProcessDefinitionId());
        }
        // executionId
        if (StringUtils.isNotBlank(hisActivitySearch.getExecutionId())) {
            haiq.executionId(hisActivitySearch.getExecutionId());
        }
        // activityId
        if (StringUtils.isNotBlank(hisActivitySearch.getActivityId())) {
            haiq.activityId(hisActivitySearch.getActivityId());
        }
        // activityName
        if (StringUtils.isNotBlank(hisActivitySearch.getActivityName())) {
            haiq.activityName(hisActivitySearch.getActivityName());
        }
        // activityType
        if (StringUtils.isNotBlank(hisActivitySearch.getActivityType())) {
            haiq.activityType(hisActivitySearch.getActivityType());
        }
        // taskAssignee
        if (StringUtils.isNotBlank(hisActivitySearch.getTaskAssignee())) {
            haiq.taskAssignee(hisActivitySearch.getTaskAssignee());
        }
        try {
            List<HistoricActivityInstance> activityInstanceList = haiq.list();
            result.setData(activityInstanceList);
            result.setResultCode(ResultEnum.SUCCESS.getValue());
        } catch (Exception e)  {
            result.setResultCode(ResultEnum.FAIL.getValue());
            e.printStackTrace();
        }
        return result;
    }
}
