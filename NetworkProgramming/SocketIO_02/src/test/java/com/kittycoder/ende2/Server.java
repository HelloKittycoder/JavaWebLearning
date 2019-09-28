package com.kittycoder.ende2;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.FixedLengthFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;

/**
 * Created by shucheng on 2019-9-28 下午 22:44
 */
public class Server {

    public static void main(String[] args) throws Exception {
        // 1 创建2个线程，一个是负责接收客户端的连接，一个是负责进行数据传输的
        EventLoopGroup pGroup = new NioEventLoopGroup();
        EventLoopGroup cGroup = new NioEventLoopGroup();

        // 2 创建服务器辅助类
        ServerBootstrap b = new ServerBootstrap();
        b.group(pGroup, cGroup)
                .channel(NioServerSocketChannel.class)
                .option(ChannelOption.SO_BACKLOG, 1024)
                .option(ChannelOption.SO_SNDBUF, 32*1024)
                .option(ChannelOption.SO_RCVBUF, 32*1024)
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel sc) {
                        // 3 在这里配置具体数据接收方法的处理
                        // 设置定长字符串接收
                        sc.pipeline().addLast(new FixedLengthFrameDecoder(5));
                        // 设置字符串形式的节码
                        sc.pipeline().addLast(new StringDecoder());
                        sc.pipeline().addLast(new ServerHandler());

                        /* 还可以写成
                        sc.pipeline().addLast(new FixedLengthFrameDecoder(5),
                                new StringDecoder(),
                                new ServerHandler());*/
                    }
                });

        // 4 绑定连接
        ChannelFuture cf = b.bind(8765).sync();

        // 5 等待服务器监听端口关闭
        cf.channel().closeFuture().sync();
        pGroup.shutdownGracefully();
        cGroup.shutdownGracefully();
    }
}
