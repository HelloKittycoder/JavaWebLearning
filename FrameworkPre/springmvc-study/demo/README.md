### SpringMVC简介
1. SpringMVC中重要组件  
1.1 DispatcherServlet：前端控制器，接收所有请求（如果配置/ 不包含jsp）  
1.2 HandlerMapping：判断请求格式的，判断希望执行哪个具体的方法  
1.3 HandlerAdapter：负责调用具体的方法  
1.4 ViewResolver：视图解析器。解析结果，准备跳转到具体的物理视图  
2. SpringMVC运行原理图  
用户->DispatcherServlet->HandlerMapping->HandlerAdapter  
->Controller->ViewResolver->用户