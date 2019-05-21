package com.kittycoder.api.util;

import org.camunda.bpm.model.bpmn.BpmnModelInstance;
import org.camunda.bpm.model.bpmn.instance.LoopCharacteristics;
import org.camunda.bpm.model.bpmn.instance.MultiInstanceLoopCharacteristics;
import org.camunda.bpm.model.bpmn.instance.SequenceFlow;
import org.camunda.bpm.model.bpmn.instance.UserTask;
import org.camunda.bpm.model.bpmn.instance.camunda.CamundaProperties;
import org.camunda.bpm.model.bpmn.instance.camunda.CamundaProperty;
import org.camunda.bpm.model.xml.instance.ModelElementInstance;

import java.util.*;

/**
 * Created by shucheng on 2019-5-21 下午 19:53
 */
public class RepositoryServiceUtil extends ServiceUtil {

    // --------------------------------流程图信息start------------------------------------
    public static BpmnModelInstance getBpmnModelInstance(String processDefinitionId) {
        return getRepositoryService().getBpmnModelInstance(processDefinitionId);
    }

    /**
     * 根据元素id（流程图上元素id）获取相应的bpmn对象
     * @param processDefinitionId 流程定义id
     * @param elementId 元素id
     * @param <T>
     * @return
     */
    public static <T extends ModelElementInstance> T queryElementInfoById(String processDefinitionId, String elementId) {
        BpmnModelInstance bpmnModelInstance = getBpmnModelInstance(processDefinitionId);
        return bpmnModelInstance.getModelElementById(elementId);
    }

    /**
     * 根据元素类型（SequenceFlow，StartEvent，EndEvent）获取相应的bpmn对象（可能会有多个）
     * @param processDefinitionId 流程定义id
     * @param referencingClass 元素类型
     * @param <T>
     * @return
     */
    public static <T extends ModelElementInstance> Collection<T> queryElementInfoByType(String processDefinitionId, Class<T> referencingClass) {
        BpmnModelInstance bpmnModelInstance = getBpmnModelInstance(processDefinitionId);
        return bpmnModelInstance.getModelElementsByType(referencingClass);
    }

    /**
     * 根据元素id获取元素的简单信息
     * @param processDefinitionId
     * @param elementId
     * @return
     */
    public static Map<String, Object> queryElementSimpleInfoById(String processDefinitionId, String elementId) {
        ModelElementInstance elementInstance = queryElementInfoById(processDefinitionId, elementId);
        return handleModelElementInstance(elementInstance);
    }

    /**
     * 根据元素类型获取元素的简单信息
     * @param processDefinitionId
     * @param referencingClass
     * @param <T>
     * @return
     */
    public static <T extends ModelElementInstance> List<Map<String, Object>> queryElementSimpleInfoByType(String processDefinitionId, Class<T> referencingClass) {
        List<Map<String, Object>> list = new ArrayList<>();
        Collection<T> collection = queryElementInfoByType(processDefinitionId, referencingClass);
        for (T elementInstance : collection) {
            list.add(handleModelElementInstance(elementInstance));
        }
        return list;
    }

    /**
     * 处理单个ModelElementInstance中的信息
     * @param elementInstance
     * @param <T>
     * @return
     */
    public static <T extends ModelElementInstance> Map<String, Object> handleModelElementInstance(T elementInstance) {
        Map<String, Object> resultMap = new HashMap<>();
        if (elementInstance instanceof SequenceFlow) {
            SequenceFlow sequenceFlow = (SequenceFlow) elementInstance;
            resultMap.put("sequenceId", sequenceFlow.getId());
            // 开始节点名称
            resultMap.put("startNodeName", sequenceFlow.getSource().getName());
            // 结束节点名称
            resultMap.put("endNodeName", sequenceFlow.getTarget().getName());
        } else if (elementInstance instanceof UserTask) {
            UserTask userTask = (UserTask) elementInstance;
            // 任务节点的定义key
            resultMap.put("taskDefinitionKey", userTask.getId());
            // 任务节点的定义名称
            resultMap.put("taskDefinitionName", userTask.getName());
            // 审批人
            resultMap.put("assignee", userTask.getCamundaAssignee());
            LoopCharacteristics loopCharacteristics = userTask.getLoopCharacteristics();
            if (loopCharacteristics != null) {
                if (loopCharacteristics instanceof MultiInstanceLoopCharacteristics) {
                    MultiInstanceLoopCharacteristics multiLoop = (MultiInstanceLoopCharacteristics) loopCharacteristics;
                    // 是否为多人节点
                    resultMap.put("isMulti", true);
                    // 遍历集合表达式
                    resultMap.put("collection", multiLoop.getCamundaCollection());
                    // 完成条件
                    resultMap.put("completionCondition", multiLoop.getCompletionCondition().getRawTextContent());
                } else {
                    // 是否为多人节点
                    resultMap.put("isMulti", false);
                }
            }
        }
        return resultMap;
    }
    // --------------------------------流程图信息end------------------------------------


    // --------------------------------自定义属性start------------------------------------
    // 获得指定节点上的自定义属性
    // https://groups.google.com/forum/#!topic/camunda-bpm-users/98PZ74CW46o
    public static Object getValueByNodeAttr(String processDefinitionId, String nodeId, String attrName) {
        BpmnModelInstance bpmnModelInstance = getBpmnModelInstance(processDefinitionId);
        Collection<UserTask> userTasks = bpmnModelInstance.getModelElementsByType(UserTask.class);
        for (UserTask userTask : userTasks) {
            if (userTask.getId().equals(nodeId)) {
                CamundaProperties camundaProperties = userTask.getExtensionElements().getElementsQuery().filterByType(CamundaProperties.class).singleResult();
                for (CamundaProperty camundaProperty : camundaProperties.getCamundaProperties()) {
                    if (camundaProperty.getCamundaName().equals(attrName)) {
                        return camundaProperty.getCamundaValue();
                    }
                }
            }
        }
        return null;
    }

    // 判断节点上是否有自定义属性
    public static Object hasNodeAttr(String processDefinitionId, String nodeId, String attrName) {
        Object value = getValueByNodeAttr(processDefinitionId, nodeId, attrName);
        return value != null;
    }
    // --------------------------------自定义属性end------------------------------------
}
