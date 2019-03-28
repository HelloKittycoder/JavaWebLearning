### &lt;resultMap&gt;标签关联集合对象
1. n+1方式和联合查询方式  
A. n+1方式  
（1） 在Teacher中添加List&lt;Student&gt;  
```java
public class Teacher {
    private int id;
    private String name;
    private List<Student> list;
}
```
（2） 在StudentMapper.xml中添加通过tid查询  
```xml
<select id="selectByTid" parameterType="int" resultType="student">
    select * from student where tid=#{0}
</select>
```
（3） 在TeacherMapper.xml中添加查询全部  
&lt;collection/&gt; 当属性是集合类型时使用的标签
```xml
<resultMap id="mymap" type="teacher">
    <id column="id" property="id"/>
    <result column="name" property="name"/>
    <collection property="list" column="id"
         select="com.bjsxt.mapper.StudentMapper.selectByTid"/>
</resultMap>

<select id="selectAll" resultMap="mymap">
    select * from teacher
</select>
```
B. 联合查询方式  
（1） 在TeacherMapper.xml中添加：
说明：mybatis可以通过主键判断对象是否被加载过，不需要担心创建重复Teacher  
```xml
<resultMap id="mymap1" type="teacher">
    <id column="tid" property="id"/>
    <result column="tname" property="name"/>
    <collection property="list" ofType="student">
        <id column="sid" property="id"/>
        <result column="sname" property="name"/>
        <result column="age" property="age"/>
        <result column="tid" property="tid"/>
    </collection>
</resultMap>

<select id="selectAll1" resultMap="mymap1">
    select t.id tid,
        t.name tname,
        s.id sid,
        s.name sname,
        age
    from teacher t
    left join student s
    on t.id = s.tid
</select>
```