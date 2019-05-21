package com.kittycoder.api.controller;

import org.camunda.bpm.engine.HistoryService;
import org.camunda.bpm.engine.RepositoryService;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.task.Task;

import javax.annotation.Resource;

/**
 * Created by shucheng on 2019/3/8 下午 17:53
 */
public class BaseController {

    @Resource
    protected RepositoryService repositoryService;
    @Resource
    protected RuntimeService runtimeService;
    @Resource
    protected TaskService taskService;
    @Resource
    protected HistoryService historyService;

    // 根据待办id查询待办实体
    public Task findTaskById(String taskId) {
        return taskService.createTaskQuery().taskId(taskId).singleResult();
    }
}
