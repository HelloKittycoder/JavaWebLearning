package com.kittycoder.api.util;

import com.kittycoder.CamundastudyApplicationTests;
import org.camunda.bpm.engine.impl.pvm.process.ActivityImpl;
import org.camunda.bpm.engine.repository.ProcessDefinition;
import org.junit.Test;

import java.util.List;

public class ProcessDiagramUtilTest extends CamundastudyApplicationTests {

    @Test
    public void findProcessDefinitionEntityByTaskId() {
        String taskId = "9b3bb3f2-97fa-11e9-85a9-00ff05c8d50b";
        ProcessDefinition pd = ProcessDiagramUtil.findProcessDefinitionEntityByTaskId(taskId);
        System.out.println(pd);
    }

    @Test
    public void findActivityImpl() {
        String taskId = "9b3bb3f2-97fa-11e9-85a9-00ff05c8d50b";
        ActivityImpl activityImpl = ProcessDiagramUtil.findActivityImpl(taskId, "end");
        System.out.println(activityImpl);
    }

    @Test
    public void findBackActivity() {
        String taskId = "0030ed46-97d7-11e9-94c6-02422b4a0087";
        List<ActivityImpl> list = ProcessDiagramUtil.findBackActivity(taskId);
        System.out.println(list);
    }
}