spring学习
spring-framework 4.1.6

1. Test：单元测试
2. Core Container：核心容器。Spring启动最基本的条件  
a. Beans：Spring负责创建类对象并管理对象  
b. Core：核心类  
c. Context：上下文参数。获取外部资源或者管理注解  
d. SpEL：expression.jar

3. AOP：实现aop功能需要的依赖
4. Aspects：切面AOP依赖的包

5. Data Access/Integration：spring封装数据访问层相关内容  
a. JDBC：Spring对JDBC封装后的代码  
b. ORM：封装了持久层框架的代码  
c. transactions：对应spring-tx.jar，声明式事务使用

6. Web：需要spring完成web相关功能时需要
a. 如：由tomcat加载spring配置文件时需要有spring-web包
