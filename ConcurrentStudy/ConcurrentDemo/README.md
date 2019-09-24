### 并发编程Demo
#### 基础部分
ch01 线程安全 ThreadSafetyTest.java  
ch02 多个线程多个锁 MultiThreadTest.java  
ch03 对象锁的同步和异步问题 SyncAsyncTest.java
ch04 脏读 DirtyReadTest.java  
ch05 synchronized的一些其他概念  
&#8195;&#8195;synchronized的重入（不同实例方法之间） SyncDubbo1.java  
&#8195;&#8195;synchronized的重入（子类父类方法之间） SyncDubbo2.java  
&#8195;&#8195;synchronized异常 SyncException.java  
ch06 synchronized的一些使用问题  
&#8195;&#8195;锁对象的改变问题 ChangeLock.java  
&#8195;&#8195;死锁问题 DeadLock.java  
&#8195;&#8195;同一对象属性的修改不会影响锁的情况 ModifyLock.java  
&#8195;&#8195;synchronized可以锁的范围 ObjectLock.java  
&#8195;&#8195;减小锁粒度 Optimize.java  
&#8195;&#8195;String常量池的缓存功能 StringLock.java  
ch07 volatile关键字  
&#8195;&#8195;多个线程之间变量共享 RunThread.java  
&#8195;&#8195;volatile不具备原子性 VolatileNoAtomic.java  
&#8195;&#8195;多个addAndGet操作是非原子性的 AtomicUse.java  
ch08 线程之间的通信  
&#8195;&#8195;使用volatile关键字让变量对其他线程可见 ListAdd1.java  
&#8195;&#8195;使用wait、notify方法 ListAdd2.java  
&#8195;&#8195;使用CountDownLatch可以解决notify发通知时不释放锁的问题 ListAdd3.java  
ch09 使用wait/notify模拟BlockingQueue MyQueue.java  
ch10 测试threadLocal ConnThreadLocal.java  
ch11 单例模型  
&#8195;&#8195;双重校验锁 DoubleCheckSingleton.java  
&#8195;&#8195;静态内部类 Singleton.java  

#### 中级篇
ch12 多线程使用Vector的示例 Tickets  
ch13 并发类容器  
&#8195;&#8195;ConcurrentMap使用 UseConcurrentMap.java  
&#8195;&#8195;CopyOnWrite使用 UseCopyOnWrite.java  
&#8195;&#8195;ConcurrentLinkedQueue、ArrayBlockingQueue、LinkedBlockingQueue、SynchronousQueue UseQueue.java  
&#8195;&#8195;基于优先级的阻塞队列 UsePriorityBlockingQueue.java  
&#8195;&#8195;带有延迟时间的队列 WangBa.java、Wangmin.java  
ch14 Future模式  
ch15 MasterWorker模式  