### Spring中常用注解
1. @Component 创建类对象，相当于配置&lt;bean/&gt;  
2. @Service 与@Component功能相同（写在ServiceImpl类上）  
3. @Repository 与@Component功能相同（写在数据访问层类上）  
该注解在hibernate中会用到，因为mybatis中不用写dao层实现类  
4. @Controller @Component功能相同（写在控制器类上）  
5. @Resource（不需要写对象的get/set）  
5.1 java中的注解  
5.2 默认按照byName注入，如果没有名称对象，按照byType注入  
建议：对象名称和spring容器中的对象名相同  
6. @Autowired（不需要写对象的get/set）  
6.1 spring的注解  
6.2 默认按照byType注入  
7. @Value 获取properties文件中内容  
8. @Pointcut 定义切点  
9. @Aspect 定义切面类  
10. @Before  前置通知  
11. @After 后置通知  
12. @AfterReturning 后置通知，必须切点正确执行  
13. @AfterThrowing 异常通知  
14. @Around 环绕通知
