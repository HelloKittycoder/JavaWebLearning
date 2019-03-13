package com.kittycoder.api.controller;

import com.alibaba.fastjson.JSON;
import com.kittycoder.api.entity.*;
import com.kittycoder.common.entity.Result;
import com.kittycoder.common.enums.ResultEnum;
import com.kittycoder.common.util.CamundaUtil;
import com.kittycoder.common.util.CommonUtil;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.camunda.bpm.engine.impl.bpmn.deployer.BpmnDeployer;
import org.camunda.bpm.engine.repository.Deployment;
import org.camunda.bpm.engine.repository.DeploymentQuery;
import org.camunda.bpm.engine.repository.DeploymentWithDefinitions;
import org.camunda.bpm.engine.repository.ProcessDefinition;
import org.camunda.bpm.engine.runtime.ActivityInstance;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.runtime.ProcessInstanceQuery;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.*;

/**
 * Created by shucheng on 2019/3/8 下午 17:49
 */
@RestController
@RequestMapping("/instance")
public class ProcessInstanceController extends BaseController {

    // 部署一个流程定义
    @ApiOperation(value = "部署一个流程定义", notes = "返回值：部署的流程定义实体")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "resourceName", value = "资源名称", required = true),
            @ApiImplicitParam(name = "deployName", value = "本次部署名称", required = true),
            @ApiImplicitParam(name = "filePath", value = "bpmn文件的绝对路径", required = true)
    })
    @PostMapping(value = "/deployProcessDefinition")
    public Result deployProcessDefinition(@RequestParam(value = "resourceName") String resourceName,
                                          @RequestParam(value = "deployName") String deployName,
                                          @RequestParam(value = "filePath") String filePath) {
        Result result = new Result();
        try {
            File file = new File(filePath);
            if (!file.exists()) {
                throw new Exception("文件不存在");
            }
            // 处理resourceName使之符合规范
            if (StringUtils.isBlank(resourceName) && CamundaUtil.checkResourceSuffix(filePath)) {
                resourceName = filePath;
            } else {
                if (!CamundaUtil.checkResourceSuffix(resourceName)) {
                    resourceName += "." + BpmnDeployer.BPMN_RESOURCE_SUFFIXES[1];
                }
            }
            // 部署流程定义
            InputStream inputStream = new FileInputStream(filePath);
            DeploymentWithDefinitions deployment = repositoryService.createDeployment()
                    .addInputStream(resourceName, inputStream)
                    .name(deployName)
                    .deployWithResult();
            ProcessDefinition pd = repositoryService.createProcessDefinitionQuery()
                    .deploymentId(deployment.getId())
                    .processDefinitionResourceName(resourceName)
                    .singleResult();
            result.setData(pd.getId());
            result.setResultCode(ResultEnum.SUCCESS.getValue());
        } catch (Exception e) {
            result.setResultCode(ResultEnum.FAIL.getValue());
            e.printStackTrace();
        }
        return result;
    }

    @ApiOperation(value = "查询正在运行的流程实例", notes = "")
    @GetMapping("/findProcessInstance")
    public Result findProcessInstance(ProcessSearch processSearch) {
        Result result = new Result();
        // 流程实例查询
        ProcessInstanceQuery processInstanceQuery = runtimeService.createProcessInstanceQuery();
        // processInstanceId
        if (StringUtils.isNotBlank(processSearch.getProcessInstanceId())) {
            processInstanceQuery.processInstanceId(processSearch.getProcessInstanceId());
        }
        // processDefinitionKey
        if (StringUtils.isNotBlank(processSearch.getProcessDefinitionKey())) {
            processInstanceQuery.processDefinitionKey(processSearch.getProcessDefinitionKey());
        }
        // processDefinitionId
        if (StringUtils.isNotBlank(processSearch.getProcessDefinitionId())) {
            processInstanceQuery.processDefinitionId(processSearch.getProcessDefinitionId());
        }
        // deploymentId
        if (StringUtils.isNotBlank(processSearch.getDeploymentId())) {
            processInstanceQuery.deploymentId(processSearch.getDeploymentId());
        }
        try {
            List<ProcessInstance> processInstanceList = processInstanceQuery.list();
            // 重新封装
            List<ProcessInstanceExt> resultList = new ArrayList<ProcessInstanceExt>();
            processInstanceList.forEach(t -> {
                ProcessInstanceExt pe = new ProcessInstanceExt();
                BeanUtils.copyProperties(t, pe);
                resultList.add(pe);
            });
            result.setData(resultList);
            result.setResultCode(ResultEnum.SUCCESS.getValue());
        } catch (Exception e) {
            result.setResultCode(ResultEnum.FAIL.getValue());
            e.printStackTrace();
        }
        processInstanceQuery.list();
        return result;
    }

    @ApiOperation(value = "发起一个流程实例", notes = "")
    @PostMapping("/startProcessInstance")
    public Result startProcessInstance(ProcessStart processStart) {
        Result result = new Result();
        try {
            // 解析其他变量
            Map<String, Object> variables = new HashMap<String, Object>();
            String varStr = processStart.getVariables();
            if (CommonUtil.isJSONValid(varStr)) {
                variables = JSON.parseObject(varStr, Map.class);
            }

            ProcessInstance instance = null;
            if (StringUtils.isNotBlank(processStart.getProcessDefinitionKey())) {
                // 方式一：通过流程定义key发起流程
                instance = runtimeService.startProcessInstanceByKey(processStart.getProcessDefinitionKey(), processStart.getBusinessKey(), variables);
            } else if (StringUtils.isNotBlank(processStart.getProcessDefinitionId())) {
                // 方式二：通过流程定义id发起流程
                instance = runtimeService.startProcessInstanceById(processStart.getProcessDefinitionId(), processStart.getBusinessKey(), variables);
            }
            // 设置流程实例id
            if (instance != null) {
                result.setData(instance.getProcessInstanceId());
                result.setResultCode(ResultEnum.SUCCESS.getValue());
            } else {
                result.setResultCode(ResultEnum.FAIL.getValue());
            }
        } catch (Exception e) {
            result.setResultCode(ResultEnum.FAIL.getValue());
            e.printStackTrace();
        }
        return result;
    }

    // 批量删除流程实例
    @ApiOperation(value = "批量删除流程实例", notes = "")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "processInstanceIds", value = "多个流程实例id用逗号分隔", required = true)
    })
    @PostMapping("/deleteProcessInstance")
    public Result deleteProcessInstances(String processInstanceIds) {
        Result result = new Result();
        try {
            // 实例id不为空的情况下，执行批量删除
            if (StringUtils.isNotBlank(processInstanceIds)) {
                List<String> processInstanceIdList = Arrays.asList(processInstanceIds.split(","));
                runtimeService.deleteProcessInstancesAsync(processInstanceIdList, "system delete");
            }
            result.setResultCode(ResultEnum.SUCCESS.getValue());
        } catch (Exception e) {
            result.setResultCode(ResultEnum.FAIL.getValue());
            e.printStackTrace();
        }
        return result;
    }

    // 根据流程定义key删除流程定义，可能会一次删除多个
    @ApiOperation(value = "根据流程定义key删除流程定义", notes = "")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "processDefinitionKey", value = "流程定义key", required = true)
    })
    @PostMapping("/deleteProcessDefinitionByKey")
    public Result deleteProcessDefinitionsByKey(String processDefinitionKey) {
        Result result = new Result();
        List<ProcessDefinition> pdqList = repositoryService.createProcessDefinitionQuery()
                .processDefinitionKey(processDefinitionKey).list();
        try {
            for (ProcessDefinition pdq : pdqList) {
                repositoryService.deleteProcessDefinition(pdq.getId(), true);
                System.out.println(pdq.getId() + "======" +pdq.getName() + "======已经被删除");
            }
            result.setResultCode(ResultEnum.SUCCESS.getValue());
        } catch (Exception e) {
            result.setResultCode(ResultEnum.FAIL.getValue());
            e.printStackTrace();
        }
        return result;
    }

    // 根据流程定义key查询流程定义
    @ApiOperation(value = "根据流程定义key查询流程定义", notes = "")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "processDefinitionKey", value = "流程定义key", required = true)
    })
    @GetMapping("/findProcessDefinitionsByKey")
    public Result findProcessDefinitionsByKey(String processDefinitionKey) {
        Result result = new Result();
        result.setResultCode(ResultEnum.SUCCESS.getValue());
        List<ProcessDefinition> pdqList = repositoryService.createProcessDefinitionQuery()
                .processDefinitionKey(processDefinitionKey).list();
        // 重新封装
        List<ProcessDefinitionExt> resultList = new ArrayList<ProcessDefinitionExt>();
        try {
            pdqList.forEach(t -> {
                ProcessDefinitionExt p = new ProcessDefinitionExt();
                BeanUtils.copyProperties(t, p);
                resultList.add(p);
            });
            result.setData(resultList);
            result.setResultCode(ResultEnum.SUCCESS.getValue());
        } catch (Exception e) {
            result.setResultCode(ResultEnum.FAIL.getValue());
            e.printStackTrace();
        }
        return result;
    }

    @ApiOperation(value = "查询所有流程部署", notes = "")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "部署id"),
            @ApiImplicitParam(name = "name", value = "部署名称")
    })
    @GetMapping("/findDeployments")
    public Result findDeployments(@RequestParam(required = false) String id,
                                  @RequestParam(required = false) String name) {
        Result result = new Result();
        DeploymentQuery dq = repositoryService.createDeploymentQuery();
        // 设置查询条件
        // 部署id
        if (StringUtils.isNotBlank(id)) {
            dq.deploymentId(id);
        }
        // 部署名称
        if (StringUtils.isNotBlank(name)) {
            dq.deploymentName(name);
        }
        List<Deployment> dList = dq.list();
        // 重新封装
        List<DeploymentExt> resultList = new ArrayList<DeploymentExt>();
        try {
            dList.forEach(t -> {
                DeploymentExt p = new DeploymentExt();
                BeanUtils.copyProperties(t, p);
                resultList.add(p);
            });
            result.setResultCode(ResultEnum.SUCCESS.getValue());
            result.setData(resultList);
        } catch (Exception e) {
            result.setResultCode(ResultEnum.FAIL.getValue());
            e.printStackTrace();
        }
        return result;
    }

    @ApiOperation(value = "根据部署id删除一个流程部署", notes = "")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "部署id")
    })
    @PostMapping("/deleteDeploymentById")
    public Result deleteDeploymentById(String id) {
        Result result = new Result();
        try {
            repositoryService.deleteDeployment(id, true);
            result.setResultCode(ResultEnum.SUCCESS.getValue());
        } catch (Exception e) {
            result.setResultCode(ResultEnum.FAIL.getValue());
            e.printStackTrace();
        }
        return result;
    }

    // 获取已经运行的流程节点情况
    @ApiOperation(value = "获取已经运行的流程节点情况", notes = "")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "processInstanceId", value = "流程实例id")
    })
    @GetMapping("/getActivityInstance")
    public Result getActivityInstance(String processInstanceId) {
        Result result = new Result();
        try {
            ActivityInstance activityInstance = runtimeService.getActivityInstance(processInstanceId);
            result.setData(activityInstance);
            result.setResultCode(ResultEnum.SUCCESS.getValue());
        } catch (Exception e) {
            result.setResultCode(ResultEnum.FAIL.getValue());
            e.printStackTrace();
        }
        return result;
    }
}
