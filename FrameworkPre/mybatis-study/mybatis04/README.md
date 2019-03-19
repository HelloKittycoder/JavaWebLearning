### 基础概念区分
a. 功能：从应用程序角度出发，软件具有哪些功能  
b. 业务：完成功能时的逻辑，对应service中的一个方法  
c. 事务：从数据库角度出发，完成业务时需要执行的sql集合，统称一个事务  
（事务回滚：如果在一个事务中某个sql执行失败，希望能回归到事务的原点，保证数据库数据的完整性和一致性）

1. mybatis中默认是关闭了JDBC的自动提交功能  
（1） 每一个SqlSession默认都是不自动提交事务  
（2） session.commit()提交事务  
（3） openSession(true);自动提交 setAutoCommit(true);  

2. mybatis底层是对JDBC的封装  
（1） JDBC中executeUpdate()执行新增，删除，修改的的sql，返回值int表示受影响的行数  
（2） mybatis中&lt;insert&gt;&lt;delete&gt;&lt;update&gt;标签没有resultType属性，认为返回值都是int  

3. openSession()时，mybatis会在创建SqlSession时同时创建一个Transaction（事务对象），同时autoCommit都为false  
如果出现异常，应该用session.rollback()回滚事务  

### mybatis中新增、修改、删除
1. 新增

（1） 在mapper.xml中提供insert标签，标签没有返回值类型
  ```xml
  <insert id="insertFlower" parameterType="flower">
      insert into flower(id, name) values(default, #{name})
  </insert>
  ```
（2） 通过session.insert()调用新增方法
  ```java
  int index = session.insert("a.b.insertFlower", flower);
  if (index > 0) {
    System.out.println("成功");
  } else {
    System.out.println("成功");
  }
  ```
