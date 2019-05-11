### scope属性
1. &lt;bean&gt;的属性  
2. 作用：控制对象有效范围（单例、多例等）  
3. &lt;bean/&gt;标签对应的对象默认是单例的  
无论获取多少次，都是同一个对象  
4. scope可取值  
4.1 singleton 默认值，单例  
4.2 prototype 多例，每次获取重新实例化  
4.3 request 每次请求，重新实例化  
4.4 session 每个会话对象内，对象是单例的  
4.5 application 在application对象内是单例的  
4.6 global session 是spring推出的一个对象，依赖于spring-webmvc-portlet，类似于session