### getMapper接口绑定和多参数传递
A. 接口绑定  
1. 作用：创建一个接口后，mybatis通过mapper.xml生成接口的实现类，通过调用接口  
就可以获取mapper.xml中编写的sql（后续mybatis和spring整合时用的是这种方案）

2. 实现步骤  
（1） 创建一个接口  
① 接口的全路径（包名+接口名）与mapper.xml中&lt;mapper&gt;namespace相同  
② 接口中方法名和mapper.xml标签的id属性相同
（2） 在mybatis.xml中使用&lt;package&gt;扫描接口和mapper.xml    

3. 代码实现步骤  
（1） 在mybatis.xml中&lt;mappers&gt;下使用&lt;package&gt;
  ```xml
  <mappers>
      <package name="com.bjsxt.mapper"/>
  </mappers>
  ```
（2） 在com.bjsxt.mapper下新建接口  
  ```java
  public interface LogMapper {
      List<Log> selAll();
  }
  ```
（3）在com.bjsxt.mapper新建一个LogMapper.xml  
① namespace必须和接口全限定路径（包名+类名）一致  
② id值必须和接口中方法名相同  
③ 如果接口中方法为多个参数，可以省略parameterType
  ```xml
  <mapper namespace="com.bjsxt.mapper.LogMapper">
      <select id="selAll" resultType="log">
          select * from log
      </select>
  </mapper>
  ```
B. 多参数实现办法  
方法一（基本用法）：  
1. 在接口中声明方法
  ```java
  List<Log> selByAccInAccOut(String accIn, String accOut);
  ```
2. 在mapper.xml中添加
  ```xml
  <select id="selByAccInAccOut" resultType="log">
      <!-- 参数上没写Param注解时 -->
      select * from log where accin=#{0} and accout=#{1}
      <!-- select * from log where accin=#{param1} and accout=#{param2} -->
  </select>
  ```
方法二（添加注解，用的比较多）：  
1. 在接口中声明方法
  ```java
  /**
   * mybatis把参数转换为map了，其中@Param("key")参数内容就是map的value
   * 这时形参取什么名称都不重要了，因为sql中是根据Param注解中的值来对应参数的值的
   * @param accIn
   * @param accOut
   * @return
   */
  List<Log> selByAccInAccOut(@Param("accIn") String accIn, @Param("accOut") String accOut);
  ```
2. 在mapper.xml中添加
  ```xml
  <select id="selByAccInAccOut" resultType="log"><!-- 参数上写了Param注解时 -->
      select * from log where accin=#{accIn} and accout=#{accOut}
  </select>
  ```
