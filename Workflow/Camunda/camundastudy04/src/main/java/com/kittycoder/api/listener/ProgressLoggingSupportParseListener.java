package com.kittycoder.api.listener;

import com.kittycoder.common.entity.SpringContext;
import org.camunda.bpm.engine.delegate.TaskListener;
import org.camunda.bpm.engine.impl.bpmn.behavior.UserTaskActivityBehavior;
import org.camunda.bpm.engine.impl.bpmn.parser.AbstractBpmnParseListener;
import org.camunda.bpm.engine.impl.pvm.delegate.ActivityBehavior;
import org.camunda.bpm.engine.impl.pvm.process.ActivityImpl;
import org.camunda.bpm.engine.impl.pvm.process.ScopeImpl;
import org.camunda.bpm.engine.impl.util.xml.Element;

/**
 * Created by shucheng on 2019-5-20 下午 14:41
 */
public class ProgressLoggingSupportParseListener extends AbstractBpmnParseListener {

    @Override
    public void parseUserTask(Element userTaskElement, ScopeImpl scope, ActivityImpl activity) {
        ActivityBehavior activityBehavior = activity.getActivityBehavior();
        if (activityBehavior instanceof UserTaskActivityBehavior) {
            UserTaskActivityBehavior userTaskActivityBehavior = (UserTaskActivityBehavior) activityBehavior;
            // 待办完成后的操作
            TaskCompleted tc = SpringContext.getBean(TaskCompleted.class);
            userTaskActivityBehavior
                    .getTaskDefinition()
                    .addTaskListener(TaskListener.EVENTNAME_COMPLETE, tc);
        }
    }

    @Override
    public void parseMultiInstanceLoopCharacteristics(Element activityElement, Element multiInstanceLoopCharacteristicsElement, ActivityImpl activity) {
        System.out.println("=============解析多实例节点start=============");
        System.out.println(activityElement);
        System.out.println(multiInstanceLoopCharacteristicsElement);
        System.out.println(activity);
        System.out.println("=============解析多实例节点end=============");
    }
}
