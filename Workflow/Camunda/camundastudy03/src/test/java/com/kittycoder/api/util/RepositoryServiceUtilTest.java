package com.kittycoder.api.util;

import com.kittycoder.CamundastudyApplicationTests;
import org.camunda.bpm.model.bpmn.instance.SequenceFlow;
import org.camunda.bpm.model.xml.instance.ModelElementInstance;
import org.junit.Test;

import java.util.List;
import java.util.Map;

public class RepositoryServiceUtilTest extends CamundastudyApplicationTests {

    @Test
    public void queryElementInfoById() {
        String processDefinitionId = "join-lsc:1:c3f7a40e-4466-11e9-a61f-00ff05c8d50b";
        String elementId = "Task_sequence";
        ModelElementInstance elementInstance = RepositoryServiceUtil.queryElementInfoById(processDefinitionId, elementId);
        System.out.println(elementInstance);
    }

    @Test
    public void queryElementInfoByType() throws Exception {
        // 获取流程图上的所有连线信息
        String processDefinitionId = "join-lsc:1:c3f7a40e-4466-11e9-a61f-00ff05c8d50b";
        List<Map<String, Object>> list = RepositoryServiceUtil.queryElementSimpleInfoByType(processDefinitionId, SequenceFlow.class);
        System.out.println(list);
    }

    @Test
    public void getValueByNodeAttr() {
        String processDefinitionId = "join-lsc:1:c3f7a40e-4466-11e9-a61f-00ff05c8d50b";
        String nodeId = "Task_manager";
        String attrName = "is_pass";

        Object value = RepositoryServiceUtil.getValueByNodeAttr(processDefinitionId, nodeId, attrName);
        System.out.println("=================" + value + "=================");
    }
}