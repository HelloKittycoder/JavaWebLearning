### 自动注入
1. 在Spring配置文件中对象名和ref="id"的id名相同时，使用自动注入，可以不用配置&lt;property/&gt;  
2. 两种配置办法  
2.1 在&lt;bean&gt;中通过autowire=""配置，只对这个&lt;bean&gt;生效  
2.2 在&lt;beans&gt;中通过default-autowire=""配置，表示当前文件中所有&lt;bean&gt;都使用全局配置内容；  
另外，个别&lt;bean&gt;不想用全局配置的话，另外写autowire属性值就行，不写用的就是全局配置的值  
3. autowire=""可取值  
3.1 default：默认值，根据全局default-autowire=""值，默认全局和局部都没有配置情况下，相当于no  
3.2 no：不自动注入  
3.3 byName：通过名称自动注入。在Spring容器中找类的id  
3.4 byType：根据类型注入  
此时，Spring容器中不可以出现两个相同类型的&lt;bean&gt;  
3.5 constructor：根据构造方法注入  
（1） 提供对应参数的构造方法（构造方法参数中包含注入对象）  
（2） 底层使用byName，构造方法参数名和其他&lt;bean&gt;的id相同