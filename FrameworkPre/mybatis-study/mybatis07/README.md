### 动态sql
a. 根据不同的条件执行不同的sql语句，成为动态sql  
b. mybatis中的动态sql：需要在mapper.xml中添加逻辑判断  

##### 标签使用（测试类见[LoggerMapperTest.java](https://github.com/HelloKittycoder/JavaWebLearning/blob/master/FrameworkPre/mybatis-study/mybatis07/src/test/java/com/bjsxt/mapper/LoggerMapperTest.java)）
1. if标签  
  ```xml
    <select id="testIf" resultType="log">
        select * from log where 1=1
        <!-- ognl表达式，直接写key或对象的属性，不需要添加任何特殊符号 -->
        <if test="accIn!=null and accIn!=''">
            and accin=#{accIn}
        </if>
        <if test="accOut!=null and accOut!=''">
            and accout=#{accOut}
        </if>
    </select>
  ```
2. where标签  
（1） 作用：如果where标签中无内容，则不生成where关键字；  
如果有内容，则去掉开头的and，去掉最后一个逗号，同时在开头加上一个where  
（比直接使用&lt;if&gt;少写where 1=1）  
（2） 使用示例：  
  ```xml
    <select id="testWhere" resultType="log">
        select * from log
        <where>
            <if test="accIn!=null and accIn!=''">
                and accin=#{accIn}
            </if>
            <if test="accOut!=null and accOut!=''">
                and accout=#{accOut}
            </if>
        </where>
    </select>
  ```
3. choose,when,otherwise标签  
（1） 作用：只要有一个成立，其他都不执行（相当于java里的switch）  
（2） 使用示例：  
如果accIn和accOut都不是null或''，生成的sql只有where accin=?
  ```xml
    <select id="testChoose" resultType="log">
        select * from log
        <where>
            <choose>
                <when test="accIn!=null and accIn!=''">
                    and accin=#{accIn}
                </when>
                <when test="accOut!=null and accOut!=''">
                    and accout=#{accOut}
                </when>
            </choose>
        </where>
    </select>
  ```
4. set标签  
（1） 作用：如果set标签中无内容，则不生成set关键字；  
如果有内容，则去掉最后一个逗号，同时在开头加上一个set  
（2） 使用示例：  
写id=#{id}的目的：防止<set>里面没有内容，mybatis不生成set关键字；  
如果修改中没有set从句，会造成sql语法错误
  ```xml
    <update id="testSet" parameterType="log">
        update log
        <set>
            id=#{id},
            <if test="accIn!=null and accIn!=''">
                accin=#{accIn},
            </if>
            <if test="accOut!=null and accOut!=''">
                accout=#{accOut},
            </if>
        </set>
        where id=#{id}
    </update>
  ```
5. trim标签  
（1） 各属性的含义：  

属性  | 含义
------------- | -------------
prefix  | 在前面添加内容
prefixOverrides  | 去掉前面添加内容
suffix  | 在后面添加内容
suffixOverrides  | 去掉后面内容

（2） 执行顺序：先去掉内容后添加内容  
（3） 使用示例：  
  ```xml
    <update id="testTrim2" parameterType="log">
        update log
        <!-- 去掉末尾的逗号，然后在开头加上set -->
        <trim prefix="set" suffixOverrides=",">
            a=a,
        </trim>
        where id=100
    </update>
  ```
6. bind标签  
（1） 作用：给参数重新赋值  
使用场景：模糊查询，在原内容前或后添加内容  
（2） 使用示例：  
  ```xml
    <select id="testBind" parameterType="log" resultType="log">
        <bind name="accIn" value="'%'+accIn+'%'"/>
        select * from log where accin like #{accIn}
    </select>
  ```
7. foreach标签  
（1） 作用：  
① 循环参数内容  
② 在内容的前后添加内容  
③ 在内容之间添加分隔符  
（2）使用场景：in查询，批量新增（mybatis中foreach效率比较低）  
如果希望批量新增：  
a. sql语句（mysql）  
  ```sql
    insert into log values
    (default,1,2,3),(default,2,3,4),(default,3,4,5)
  ```
b. openSession()需要指定（最好指定下，虽然不指定不会报错）  
  ```java
  // 底层用的是JDBC的PepareStatement.addBatch();
    factory.openSession(ExecutorType.BATCH);
  ```
（3） 使用示例：  

属性  | 含义
------------- | -------------
collection  | 要遍历的集合
item  | 迭代变量，#{迭代变量名}获取内容
open  | 循环后左侧添加的内容
close  | 循环后右侧添加的内容
separator  | 每次循环时，元素之间的分隔符
  ```xml
    <select id="testForEach1" parameterType="list" resultType="log">
        select * from log where id in
        <foreach collection="list" item="abc" open="(" close=")" separator=",">
            #{abc}
        </foreach>
    </select>
  ```
8. sql,include标签  
使用示例：  
（1）如果某些sql片段希望复用，可以用&lt;sql&gt;定义这个片段  
  ```xml
    <sql id="commonColumns">
        id, accout, accin, money
    </sql>
  ```
（2）然后在&lt;select&gt;&lt;insert&gt;&lt;update&gt;&lt;delete&gt;等标签中  
使用&lt;include&gt;进行引用  
  ```xml
    <select id="testInclude" resultType="log">
        select <include refid="commonColumns"></include> from log
    </select>
  ```
