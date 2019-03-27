### &lt;resultMap&gt;标签（续）
3. 使用&lt;resultMap&gt;标签实现关联单个对象（n+1方式）  
（1） n+1查询方式：先查询出某个表的全部信息，根据这个表的信息查询另一个表的信息  
（2） 与业务装配的区别：在service里写的代码，由mybatis完成装配  
（3） 实现步骤：  
① 在Student实体类中包含了一个Teacher对象  
```java
public class Student {
    private int id;
    private String name;
    private int age;
    private int tid;
    private Teacher teacher;
}
```
    
② 在TeacherMapper中提供一个查询  
```xml
<select id="selectById" parameterType="int" resultType="teacher">
    select * from teacher where id=#{0}
</select>
```
③ 在StudentMapper中  
&lt;association&gt; 装配一个对象时使用  
property 对象在类中的属性名  
select 通过哪个查询查到这个对象的信息  
column 把当前表的哪个列的值作为参数传递给另一个查询
使用n+1方式时：如果列名和属性名相同，该列可以不配置，这时使用的是AutoMapping特性；
如果列被作为参数传到另一个查询中，该列不写的话，是不会被装配进去的  
（可以试下只写association标签，这时tid均为设置值）
```xml
<resultMap id="mymap" type="student">
    <id column="id" property="id"/>
    <result column="name" property="name"/>
    <result column="age" property="age"/>
    <result column="tid" property="tid"/>
    <!-- 如果关联一个对象，使用association；关联集合时，使用collection -->
    <association property="teacher" select="com.bjsxt.mapper.TeacherMapper.selectById" column="tid"></association>
</resultMap>

<select id="selectAll" resultMap="mymap">
    select * from student
</select>
```
