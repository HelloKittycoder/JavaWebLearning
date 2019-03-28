### 使用AutoMapping结合别名实现多表查询
1. 只能使用多表联合查询方式（用不了n+1方式，而且只能关联单个对象）  
2. 要求：查询出的列名和属性名相同  
3. 实现方式  
点号在sql中是关键字符，两侧添加反单引号  
```xml
<select id="selectAll" resultType="student">
    select s.id id,
        s.name name,
        age,
        s.tid,
        t.id `teacher.id`,
        t.name `teacher.name`
    from student s
    left join teacher t
    on s.tid = t.id
</select>
```