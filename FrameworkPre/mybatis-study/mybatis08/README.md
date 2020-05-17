### 缓存
1. 应用程序和数据库交互的过程是一个相对比较耗时的过程  
2. 缓存存在的意义：让应用程序减少对数据库的访问，提升程序运行效率
3. mybatis中SqlSession缓存（一级缓存）默认开启  
（1） 同一个SqlSession对象调用同一个&lt;select&gt;时，只有第一次会访问数据库，第一次  
之后把查询结果缓存到SqlSession缓存区（内存）中  
注：
① 同一个&lt;select&gt;：缓存的是statement对象（在mybatis中一个&lt;select&gt;对应一个statement对象）  
② 同一个SqlSession对象：有效范围必须是同一个SqlSession对象  
4. 缓存流程  
（1） 先去缓存区找是否存在statement  
（2） 缓存区返回结果  
（3） 如果没有缓存statement对象，去数据库获取数据  
（4） 数据库返回查询结果  
（5） 把查询结果放到对应的缓存区中  
5. SqlSessionFactory缓存
（1） 又叫：二级缓存  
（2） 有效范围：同一个factory内哪个SqlSession都可以获取  
（3） 何时用二级缓存：当数据频繁被使用，很少被修改  
（4） 使用步骤：    
① 在mapper.xml中添加
  ```xml
  <cache readOnly="true"></cache>
  ```
② 如果不写readOnly="true" 需要把实体类序列化  
（5） 当SqlSession对象close()时或commit()时，会把SqlSession缓存的数据  
刷（flush）到SqlSessionFactory缓存区中