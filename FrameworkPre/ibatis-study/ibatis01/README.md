### ibatis环境搭建
[官方文档 cn](https://ibatis.apache.org/docs/java/pdf/iBATIS-SqlMaps-2_cn.pdf)  
[官方文档 en](https://ibatis.apache.org/docs/java/pdf/iBATIS-SqlMaps-2_en.pdf)

##### 标签使用（测试类见[LogDaoTest.java](https://github.com/HelloKittycoder/JavaWebLearning/blob/master/FrameworkPre/ibatis-study/ibatis01/src/test/java/com/bjsxt/dao/LogDaoTest.java)）
1. isNotEmpty标签（isNotNull类似）和prepend标签结合使用  
（1） 作用：比如为prepend="and"，如果isNotEmpty成立，则在标签内容的前面追加and；  
如果isNotEmpty不成立，则不产生标签里的内容，同时prepend也没效果  
（2） 使用示例：
  ```xml
    <select id="findByAttr" parameterClass="logPo" resultClass="logPo">
        select * from log
        where 1=1
        <isNotEmpty property="id" prepend="and">
            id = #id#
        </isNotEmpty>
        <isNotEmpty property="accOut" prepend="and">
            accout like '%$accOut$%'
        </isNotEmpty>
        <isNotEmpty property="accIn" prepend="and">
            accin like '%$accIn$%'
        </isNotEmpty>
    </select>
  ```
2. dynamic标签和prepend标签结合使用
（1） 作用：如果dynamic标签中无内容，则不生成prepend中的内容；  
如果有内容，则去掉开头的and（第一个有prepend的and），同时在开头加上一个where  
（比直接使用&lt;isNotEmpty&gt;，&lt;isNotNull&gt;少写where 1=1）  
（2） 使用示例：  
  ```xml
    <select id="findByAttrWithDynamic1" parameterClass="logPo" resultClass="logPo">
        select * from log
        <dynamic prepend="where">
            <isNotEmpty property="id" prepend="and">
                id = #id#
            </isNotEmpty>
            <isNotEmpty property="accOut" prepend="and">
                accout like '%$accOut$%'
            </isNotEmpty>
            <isNotEmpty property="accIn" prepend="and">
                accin like '%$accIn$%'
            </isNotEmpty>
        </dynamic>
    </select>
  ```