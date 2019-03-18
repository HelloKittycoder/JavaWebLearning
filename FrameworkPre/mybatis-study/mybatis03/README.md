### parameterType属性
1. 在XXXMapper.xml中&lt;select&gt;&lt;delete&gt;等标签的parameterType可以控制参数类型  
2. SqlSession的selectList、selectOne和selectMap的第二个参数都表示方法的参数  
```java
  Flower flower = session.selectOne("a.b.selectById", 2);
  System.out.println(flower);
```
1） 在Mapper.xml中可以通过#{}获取参数  
  （1） parameterType控制参数类型  
  （2） #{}获取参数内容  
      a. 使用索引，从0开始#{0}表示第一个参数；也可以使用#{param1}表示第一个参数  
      b. 如果只有一个参数（基本数据类型或String），mybatis对#{}里面内容没有要求只要写内容即可  
      c. 如果参数是对象 #{属性名}  
      d. 如果参数是map #{key}  

   ```xml
   <select id="selectById" resultType="com.bjsxt.pojo.Flower">
        select * from flower where id = #{0}
    </select>
   ```
3. #{}和${}的区别  
1） #{}获取参数的内容支持索引获取，param1获取指定位置参数，并且sql使用?占位符  
2） 字符串拼接不使用?，默认找${内容}内容的get/set方法，如果写数字，就是一个数字
4. 如果xml文件中出现"<"，">"，双引号灯特殊字符时可以使用xml文件转义标签（xml自身的）  
```
<![CDATA[ 内容 ]]>
```
5. mybatis中实现mysql分页写法  
1）?不允许在关键字前后进行数学运算，需要在代码中计算完成后传递到mapper.xml中  
2）在java代码中计算
```java
  Map<String, Object> map = new HashMap<>();
  // 显示几个
  int pageSize = 2;
  // 第几页
  int pageNumber = 2;
  map.put("pageStart", (pageNumber-1)*pageSize);
  map.put("pageSize", pageSize);
  List<Flower> flowerList = session.selectList("a.b.page", map);
  System.out.println(flowerList);
```
3) 在mapper.xml中的代码
  ```xml
  <select id="page" parameterType="map" resultType="com.bjsxt.pojo.Flower">
      select * from flower limit #{pageStart},#{pageSize}
  </select>
  ```
