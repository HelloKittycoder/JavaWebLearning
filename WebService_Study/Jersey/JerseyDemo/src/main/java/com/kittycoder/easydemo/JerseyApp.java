package com.kittycoder.easydemo;

import com.sun.jersey.api.core.PackagesResourceConfig;
import com.sun.jersey.spi.container.servlet.ServletContainer;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

/**
 * Created by shucheng on 2018/5/19.
 * 参考：https://blog.csdn.net/lsh009/article/details/53583640
 * https://www.cnblogs.com/huangyin/p/5888821.html
 * https://blog.csdn.net/guduyishuai/article/details/55094671
 */
public class JerseyApp {

    // private final Log log = LogFactory.getLog(getClass());

    public static void main(String[] args) throws Exception {
        final Server server = new Server(9999);
        ServletHolder sh = new ServletHolder(ServletContainer.class);
        sh.setInitParameter(ServletContainer.RESOURCE_CONFIG_CLASS, PackagesResourceConfig.class.getCanonicalName());
        sh.setInitParameter(PackagesResourceConfig.PROPERTY_PACKAGES, "com.kittycoder.easydemo");
        ServletContextHandler context = new ServletContextHandler(server, null);
        context.addServlet(sh, "/*");
        server.start();
        server.join();
        Runtime.getRuntime().addShutdownHook(new Thread(){
            public void run() {
                try {
                    server.stop();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
