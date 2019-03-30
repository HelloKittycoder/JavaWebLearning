### mybatis注解
1. 注解：为了简化mapper.xml配置文件  
（如果涉及动态sql依然使用mapper.xml）
2. mapper.xml和注解可以共存  
3. 使用注解时，mybatis.xml中&lt;mappers&gt;使用
&lt;package/&gt; &lt;mapper class=""/&gt; &lt;mapper resource=""/&gt;其中一个都行
4. 实现查询    
```java
@Select("select * from teacher")
List<Teacher> selectAll();
```
5. 实现新增  
```java
@Insert("insert into teacher values(default,#{name})")
int insertTeacher(Teacher teacher);
```
6. 实现修改  
```java
@Update("update teacher set name=#{name} where id=#{id}")
int updateTeacher(Teacher teacher);
```
7. 实现删除  
```java
@Delete("delete from teacher where id=#{0}")
int deleteById(int id);
```
8. 使用注解实现&lt;resultMap&gt;功能
（1） 以n+1为例  
（2） 在StudentMapper接口中添加查询  
```java
@Select("select * from student where tid=#{0}")
List<Student> selectByTid(int tid);
```
（3） 在TeacherMapper接口添加  
a. @Results()相当于&lt;resultMap&gt;  
b. @Result()相当于&lt;id/&gt;或&lt;result/&gt;  
（@Result(id=true)相当于&lt;id/&gt;）  
c. @Many()相当于&lt;collection&gt;  
d. @One()相当于&lt;association&gt;  
```java
@Results(value = {
        @Result(id = true, property = "id", column = "id"),
        @Result(property = "name", column = "name"),
        @Result(property = "list", column = "id",
                many = @Many(select = "com.bjsxt.mapper.StudentMapper.selectByTid"))
})
@Select("select * from teacher")
List<Teacher> selectTeacher();
```