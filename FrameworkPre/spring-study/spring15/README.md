### scope属性
1. &lt;bean&gt;的属性  
2. 作用：控制对象有效范围（单例、多例等）  
3. &lt;bean/&gt;标签对应的对象默认是单例的  
无论获取多少次，都是同一个对象  
4. scope可取值  
4.1 singleton 默认值，单例  
4.2 prototype 多例，每次获取重新实例化  
4.3 request 每次请求，重新实例化  
4.4 session 每个会话对象内，对象是单例的  
4.5 application 在application对象内是单例的  
4.6 global session 是spring推出的一个对象，依赖于spring-webmvc-portlet，类似于session

### 单例设计模式
1. 作用：在应用程序中保证最多只能有一个实例  
2. 好处：  
2.1 提升运行效率  
2.2 实现数据共享。例如：application对象  
3. 懒汉式  
3.1 对象只有被调用时才去创建  
3.2 示例代码：  
```java
public class SingletonLazy {
    // 由于对象需要被静态方法调用，所以把方法设置为static
    // 由于对象是static，必须要设置访问权限修饰符为private；因为如果是public可以直接访问对象，不走访问入口
    private static SingletonLazy singletonLazy;
    /**
     * 构造方法：方法名和类名相同，无返回值
     * 将构造方法私有化，使其他类不能直接实例化这个对象
     *
     * 对外提供访问入口
     */
    private SingletonLazy() {}

    /**
     * 实例方法必须通过对象调用
     * 设置方法为静态方法
     * @return
     */
    public static SingletonLazy getInstance() {
        // 添加逻辑：如果实例化过，直接返回
        if (singletonLazy == null) {
            /**
             * 多线程访问下，可能出现if同时成立的情况，添加锁
             */
            synchronized (SingletonLazy.class) {
                // 双重验证
                if (singletonLazy == null) {
                    singletonLazy = new SingletonLazy();
                }
            }
        }
        return singletonLazy;
    }
}
```
3.3 由于添加了锁，所以导致效率低
4. 饿汉式  
4.1 解决了懒汉式中多线程访问时给对象加锁导致的效率低的问题  
4.2 示例代码：  
```java
public class SingletonHungery {
    // 在类加载时进行实例化
    private static SingletonHungery singletonHungery = new SingletonHungery();
    private SingletonHungery() {}
    public static SingletonHungery getInstance() {
        return singletonHungery;
    }
}
```