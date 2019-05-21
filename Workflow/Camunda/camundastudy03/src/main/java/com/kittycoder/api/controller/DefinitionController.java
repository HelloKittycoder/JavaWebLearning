package com.kittycoder.api.controller;

import com.kittycoder.common.entity.Result;
import com.kittycoder.common.enums.ResultEnum;
import com.kittycoder.common.util.CamundaUtil;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.camunda.bpm.engine.impl.bpmn.deployer.BpmnDeployer;
import org.camunda.bpm.engine.repository.DeploymentWithDefinitions;
import org.camunda.bpm.engine.repository.ProcessDefinition;
import org.camunda.bpm.engine.repository.ProcessDefinitionQuery;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URLEncoder;

/**
 * Created by shucheng on 2019-5-21 下午 20:29
 * 流程定义controller
 */
@RestController
@RequestMapping("/definition")
public class DefinitionController extends BaseController {

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

    @ApiOperation(value = "下载流程定义文件")
    @RequestMapping(value = "/downloadProcessDefinitionFile.do", method = RequestMethod.GET, produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public ResponseEntity<byte[]> downloadProcessDefinitionFile(String processDefinitionId) {

        InputStream is = null;
        byte[] body = null;
        HttpHeaders headers = new HttpHeaders();
        try {
            // 将inputStream转换为byte[]
            is = repositoryService.getProcessModel(processDefinitionId);
            body = new byte[is.available()];
            is.read(body);

            // 查询流程定义名称，用作文件名
            ProcessDefinitionQuery pdq = repositoryService.createProcessDefinitionQuery();
            pdq.processDefinitionId(processDefinitionId);
            ProcessDefinition pd = pdq.singleResult();
            String fileName = pd.getResourceName();
            headers.add("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            IOUtils.closeQuietly(is);
        }
        return new ResponseEntity<byte[]>(body, headers, HttpStatus.CREATED);
    }
}
