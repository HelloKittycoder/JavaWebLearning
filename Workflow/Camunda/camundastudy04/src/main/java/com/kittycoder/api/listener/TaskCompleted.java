package com.kittycoder.api.listener;

import org.camunda.bpm.engine.delegate.DelegateTask;
import org.camunda.bpm.engine.delegate.TaskListener;
import org.springframework.stereotype.Component;

/**
 * Created by shucheng on 2019-5-20 下午 14:44
 */
@Component
public class TaskCompleted implements TaskListener {

    @Override
    public void notify(DelegateTask delegateTask) {
        /*ExecutionEntity execution = (ExecutionEntity) delegateTask.getExecution();
        ActivityImpl activity = execution.getActivity();
        System.out.println(activity);
        boolean isValid = (boolean) ((MultiInstanceActivityBehavior)activity.getFlowScope().getActivityBehavior()).getCompletionConditionExpression().getValue(delegateTask);
        JuelExpression expr = (JuelExpression) ((MultiInstanceActivityBehavior) activity.getFlowScope().getActivityBehavior()).getCompletionConditionExpression();
        System.out.println(isValid);*/

        System.out.println("待办已完成");
        System.out.println(delegateTask);
    }
}
