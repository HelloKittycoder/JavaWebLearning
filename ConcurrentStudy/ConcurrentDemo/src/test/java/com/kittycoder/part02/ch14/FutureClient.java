package com.kittycoder.part02.ch14;

/**
 * Created by shucheng on 2019-9-24 上午 10:24
 */
public class FutureClient {

    public Data request(final String queryStr) {
        // 1 我想要一个代理对象（Data接口的实现类）先返回给发送请求的客户端，告诉它请求已经接收到，可以做其他的事情
        final FutureData futureData = new FutureData();
        // 2 启动一个新的线程，去加载真实的数据，传递给这个代理对象
        new Thread(new Runnable() {
            @Override
            public void run() {
                // 3 这个新的线程可以去慢慢的加载真实对象，然后传递给代理对象
                RealData realData = new RealData(queryStr);
                futureData.setRealData(realData);
            }
        }).start();

        return futureData;
    }
}
