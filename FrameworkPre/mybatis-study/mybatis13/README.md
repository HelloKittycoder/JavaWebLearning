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