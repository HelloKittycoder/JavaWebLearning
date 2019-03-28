### &lt;resultMap&gt;标签关联单个对象
1. n+1方式和联合查询方式
A. n+1方式  
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
简化写法：  
```xml
<resultMap id="mymap" type="student">
    <result column="tid" property="tid"/>
    <!-- 如果关联一个对象，使用association；关联集合时，使用collection -->
    <association property="teacher" select="com.bjsxt.mapper.TeacherMapper.selectById" column="tid"></association>
</resultMap>

<select id="selectAll" resultMap="mymap">
    select * from student
</select>
```
B. 联合查询方式  
（1） 只需要编写一个sql，在StudentMapper中添加下面语句：
说明：  
① &lt;association/&gt; 只要装配一个对象就用这个标签  
② 此时把&lt;association/&gt;当成小的&lt;resultMap/&gt;看待  
③ javaType属性：&lt;association/&gt;装配完后返回一个什么类型的对象，取值是一个类（或类的别名）  
```xml
<resultMap id="mymap1" type="student">
    <id column="sid" property="id"/>
    <result column="sname" property="name"/>
    <result column="age" property="age"/>
    <result column="tid" property="tid"/>
    <association property="teacher" javaType="teacher">
        <id column="tid" property="id"/>
        <result column="tname" property="name"/>
    </association>
</resultMap>

<select id="selectAll1" resultMap="mymap1">
    select s.id sid,
        s.name sname,
        age,
        t.id tid,
        t.name tname
    from student s
    left join teacher t
    on s.tid = t.id
</select>
```
2. n+1方式和联合查询方式对比  
（1）n+1：需求不确定时  
（2）联合查询：需求中确定查询时两个表一定都查询  
3. n+1名称由来  
（1） 举例：学生表中有3条数据，现在需求是查询所有学生信息及授课老师信息  
（2） 需要执行的sql命令  
a. 查询全部学生信息：select * from 学生  
b. 执行3遍 select * from 老师 where id = 学生的外键  
（3） 使用多条sql命令查询量表数据时，如果希望把需要的数据都查询出来，  
需要执行n+1条sql才能把所有数据查询出来
（4） 缺点：效率低；  
优点：如果有的时候不需要查询学生时同时查询老师，只需要执行一个  
select * from student  
（5） 适用场景：有的时候需要查询学生同时查询老师，有的时候只需要查询学生  
（6） 如何解决n+1查询带来的效率低的问题：
a. 默认带的前提：每次都是两个都查询  
b. 使用两表联合查询