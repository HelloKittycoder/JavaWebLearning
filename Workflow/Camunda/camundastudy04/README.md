04 springboot整合camunda  
监听器配置
全局监听器：以待办完成为例（给所有UserTask的complete的操作前添加全局监听，做一些特殊处理）  
主要关注以下几个类：  
ProgressLoggingSupportParseListenerPlugin 插件配置  
ProgressLoggingSupportParseListener 解析流程图上的监听器  
TaskCompleted 监听器具体实现

参考链接：  
[stackoverflow上关于camunda全局监听器的解决方法](https://stackoverflow.com/questions/40427487/camunda-engine-execute-custom-code-on-new-deployment)  
[官方Bpmn Parse Listener示例](https://github.com/camunda/camunda-bpm-examples/tree/master/process-engine-plugin/bpmn-parse-listener)