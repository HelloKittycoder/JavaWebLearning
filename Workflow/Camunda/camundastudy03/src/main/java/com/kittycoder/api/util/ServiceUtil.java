package com.kittycoder.api.util;

import org.camunda.bpm.engine.HistoryService;
import org.camunda.bpm.engine.RepositoryService;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.history.HistoricTaskInstanceQuery;
import org.camunda.bpm.engine.history.HistoricVariableInstanceQuery;
import org.camunda.bpm.engine.repository.ProcessDefinitionQuery;
import org.camunda.bpm.engine.runtime.ProcessInstanceQuery;
import org.camunda.bpm.engine.task.TaskQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * Created by shucheng on 2019-4-18 上午 8:27
 * 封装一些常用service的工具方法
 * 封装思想参考：https://www.cnblogs.com/dmh-blog/p/6800820.html
 */
@Component
public class ServiceUtil {

    @Autowired
    private RepositoryService repositoryService;
    @Autowired
    private RuntimeService runtimeService;
    @Autowired
    private TaskService taskService;
    @Autowired
    private HistoryService historyService;
    public static ServiceUtil serviceUtil;

    @PostConstruct
    public void init() {
        serviceUtil = this;
    }

    // --------------------------------获取Query对象start------------------------------------
    // 获取ProcessDefinitionQuery
    public static ProcessDefinitionQuery getProcessDefinitionQuery() {
        return serviceUtil.repositoryService.createProcessDefinitionQuery();
    }

    // 获取HistoricTaskInstanceQuery
    public static HistoricTaskInstanceQuery getHistoricTaskInstanceQuery() {
        return serviceUtil.historyService.createHistoricTaskInstanceQuery();
    }

    // 获取HistoricVariableInstanceQuery
    public static HistoricVariableInstanceQuery getHistoricVariableInstanceQuery() {
        return serviceUtil.historyService.createHistoricVariableInstanceQuery();
    }

    // 获取ProcessInstanceQuery
    public static ProcessInstanceQuery getProcessInstanceQuery() {
        return serviceUtil.runtimeService.createProcessInstanceQuery();
    }

    // 获取TaskQuery
    public static TaskQuery getTaskQuery() {
        return serviceUtil.taskService.createTaskQuery();
    }
    // --------------------------------获取Query对象end------------------------------------

    public static RepositoryService getRepositoryService() {
        return serviceUtil.repositoryService;
    }

    public static RuntimeService getRuntimeService() {
        return serviceUtil.runtimeService;
    }

    public static TaskService getTaskService() {
        return serviceUtil.taskService;
    }

    public static HistoryService getHistoryService() {
        return serviceUtil.historyService;
    }
}
