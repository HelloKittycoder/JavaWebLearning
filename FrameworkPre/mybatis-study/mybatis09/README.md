### mybatis实现多表查询
1. 实现多表查询方式  
（1） 业务装配：对两个表编写单表查询语句，在业务（service）把查询的两个结果进行关联
（2） 使用AutoMapping特性，在实现量表联合查询时，通过别名完成映射  
（3） 使用mybatis的&lt;resultMap&gt;标签进行实现
2. 多表查询时，类中包含另一个类的对象的分类  
（1） 单个对象
（2） 集合对象

### &lt;resultMap&gt;标签
1. &lt;resultMap&gt;标签写在mapper.xml中，由程序员控制sql查询结果与实体类的映射关系  
（1） 默认mybatis使用Auto Mapping特性  
（2） 使用&lt;resultMap&gt;标签时，&lt;select&gt;标签不写resultType属性，而是使用  
resultMap属性引用&lt;resultMap&gt;标签  
2. 使用resultMap实现单表映射关系
mapper.xml代码
```xml
    <resultMap id="mymap" type="teacher">
        <!-- 主键使用id标签配置映射关系 -->
        <id column="id" property="id1"/>
        <!-- 其他列使用result标签配置映射关系 -->
        <result column="name" property="name1"/>
    </resultMap>
    
    <select id="selectAll" resultMap="mymap">
        select * from teacher
    </select>
```