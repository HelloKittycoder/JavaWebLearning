package com.kittycoder.api.controller;

import com.alibaba.fastjson.JSON;
import com.kittycoder.api.entity.TaskExt;
import com.kittycoder.api.entity.TaskSearch;
import com.kittycoder.common.entity.Result;
import com.kittycoder.common.enums.ResultEnum;
import com.kittycoder.common.util.CommonUtil;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.camunda.bpm.engine.task.Task;
import org.camunda.bpm.engine.task.TaskQuery;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;


/**
 * Created by shucheng on 2019/3/12 下午 16:41
 */
@RestController
@RequestMapping("/task")
public class TaskController extends BaseController {

    /**
     * 查询待办信息
     * @param taskSearch
     * @return
     */
    @ApiOperation(value = "查询待办信息", notes = "")
    @GetMapping("/findTasks")
    public Result findTasks(TaskSearch taskSearch) {
        Result result = new Result();
        try {
            TaskQuery tq = taskService.createTaskQuery();
            // taskId
            if (StringUtils.isNotBlank(taskSearch.getTaskId())) {
                tq.taskId(taskSearch.getTaskId());
            }
            // taskName
            if (StringUtils.isNotBlank(taskSearch.getTaskName())) {
                tq.taskName(taskSearch.getTaskName());
            }
            // taskDefinitionKey
            if (StringUtils.isNotBlank(taskSearch.getTaskDefinitionKey())) {
                tq.taskDefinitionKey(taskSearch.getTaskDefinitionKey());
            }
            // assignee
            if (StringUtils.isNotBlank(taskSearch.getAssignee())) {
                tq.taskAssignee(taskSearch.getAssignee());
            }
            List<Task> taskList = tq.list();
            // 重新封装
            List<TaskExt> resultList = new ArrayList<TaskExt>();
            taskList.forEach(t -> {
                TaskExt te = new TaskExt();
                BeanUtils.copyProperties(t, te);
                resultList.add(te);
            });
            result.setData(resultList);
            result.setResultCode(ResultEnum.SUCCESS.getValue());
        } catch (Exception e) {
            result.setResultCode(ResultEnum.FAIL.getValue());
            e.printStackTrace();
        }
        return result;
    }

    // 通过待办id批量查询流程变量
    @ApiOperation(value = "通过待办id批量查询流程变量", notes = "")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "taskId", value = "待办id", required = true),
            @ApiImplicitParam(name = "taskVarNames", value = "多个流程变量名称用逗号分隔", required = true)
    })
    @GetMapping("/queryTaskVariables")
    public Result queryTaskVariables(String taskId, String taskVarNames) {
        Result result = new Result();
        try {
            List<String> varNameList = new ArrayList<String>();
            if (StringUtils.isNotBlank(taskVarNames)) {
                varNameList = Arrays.asList(taskVarNames.split(","));
            }
            Map<String, Object> map = taskService.getVariables(taskId, varNameList);
            result.setData(map);
            result.setResultCode(ResultEnum.SUCCESS.getValue());
        } catch (Exception e) {
            result.setResultCode(ResultEnum.FAIL.getValue());
            e.printStackTrace();
        }
        return result;
    }

    // 通过待办id批量设置流程变量
    @ApiOperation(value = "通过待办id批量设置流程变量", notes = "")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "taskId", value = "待办id", required = true),
            @ApiImplicitParam(name = "variablesJson", value = "使用json存放要设置的键值对", required = true)
    })
    @GetMapping("/setTaskVariables")
    public Result setTaskVariables(String taskId, String variablesJson) {
        Result result = new Result();
        try {
            Map<String, Object> variables = new HashMap<String, Object>();
            if (StringUtils.isNotBlank(variablesJson)) {
                variables = JSON.parseObject(variablesJson, Map.class);
            }
            taskService.setVariables(taskId, variables);
            result.setResultCode(ResultEnum.SUCCESS.getValue());
        } catch (Exception e) {
            result.setResultCode(ResultEnum.FAIL.getValue());
            e.printStackTrace();
        }
        return result;
    }

    // 通过待办id批量删除流程变量
    @ApiOperation(value = "通过待办id批量删除流程变量", notes = "")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "taskId", value = "待办id", required = true),
            @ApiImplicitParam(name = "taskVarNames", value = "多个流程变量名称用逗号分隔", required = true)
    })
    @PostMapping("/removeTaskVariables")
    public Result removeTaskVariables(String taskId, String taskVarNames) {
        Result result = new Result();
        try {
            List<String> varNameList = new ArrayList<String>();
            if (StringUtils.isNotBlank(taskVarNames)) {
                varNameList = Arrays.asList(taskVarNames.split(","));
            }
            taskService.removeVariables(taskId, varNameList);
            result.setResultCode(ResultEnum.SUCCESS.getValue());
        } catch (Exception e) {
            result.setResultCode(ResultEnum.FAIL.getValue());
            e.printStackTrace();
        }
        return result;
    }

    // 加签
    @ApiOperation(value = "加签", notes = "")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "taskId", value = "待办id", required = true),
            @ApiImplicitParam(name = "assignees", value = "审批人有多个以逗号分隔", required = true)
    })
    @PostMapping("/appendTask")
    public Result appendTask(String taskId, String assignees) {
        Result result = new Result();
        if (StringUtils.isBlank(taskId)) {
            result.setResultCode(ResultEnum.FAIL.getValue());
        }
        if (StringUtils.isBlank(assignees)) {
            result.setResultCode(ResultEnum.FAIL.getValue());
        }
        try {
            String[] assigneeArr = assignees.split(",");
            for (String assignee : assigneeArr) {
                Task task = taskService.newTask();
                task.setParentTaskId(taskId);
                Task oldTask = findTaskById(taskId);
                task.setName(oldTask.getName() + "-加签任务");
                task.setAssignee(assignee);
                task.setDescription("加签任务");
                taskService.saveTask(task);
            }
            result.setResultCode(ResultEnum.SUCCESS.getValue());
        } catch (Exception e) {
            result.setResultCode(ResultEnum.FAIL.getValue());
            e.printStackTrace();
        }
        return result;
    }

    // 批量删除加签任务
    @ApiOperation(value = "批量删除加签任务", notes = "")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "taskIds", value = "待办id有多个以逗号分隔", required = true)
    })
    @PostMapping("/deleteTasks")
    public Result deleteTasks(String taskIds) {
        Result result = new Result();
        if (StringUtils.isBlank(taskIds)) {
            result.setResultCode(ResultEnum.FAIL.getValue());
            return result;
        }
        try {
            taskService.deleteTasks(Arrays.asList(taskIds.split(",")));
            result.setResultCode(ResultEnum.SUCCESS.getValue());
        } catch (Exception e) {
            result.setResultCode(ResultEnum.FAIL.getValue());
            e.printStackTrace();
        }
        return result;
    }

    // 完成待办任务
    @ApiOperation(value = "完成待办任务", notes = "")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "taskId", value = "待办id有多个以逗号分隔", required = true),
            @ApiImplicitParam(name = "variablesJson", value = "流程变量键值对")
    })
    @PostMapping("/completeTask")
    public Result completeTask(String taskId, String variablesJson) {
        Result result = new Result();
        if (StringUtils.isBlank(taskId)) {
            result.setResultCode(ResultEnum.FAIL.getValue());
            return result;
        }
        try {
            if (StringUtils.isNotBlank(variablesJson)) {
                // 如果传入了待办变量，则尝试进行转换
                Map<String, Object> variables = new HashMap<String, Object>();
                if (CommonUtil.isJSONValid(variablesJson)) {
                    variables = JSON.parseObject(variablesJson, Map.class);
                }
                taskService.complete(taskId, variables);
            } else {
                // 如果没传入待办变量，则直接完成待办
                taskService.complete(taskId);
            }
            result.setResultCode(ResultEnum.SUCCESS.getValue());
        } catch (Exception e) {
            result.setResultCode(ResultEnum.FAIL.getValue());
            e.printStackTrace();
        }
        return result;
    }
}
