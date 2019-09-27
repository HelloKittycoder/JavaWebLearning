package com.kittycoder.helloworld;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * Created by shucheng on 2019-9-27 下午 13:51
 */
public class Client {

    public static void main(String[] args) throws Exception {

        EventLoopGroup group = new NioEventLoopGroup();
        Bootstrap b = new Bootstrap();
        b.group(group)
        .channel(NioSocketChannel.class)
        .handler(new ChannelInitializer<SocketChannel>() {
            @Override
            protected void initChannel(SocketChannel sc) throws Exception {
                sc.pipeline().addLast(new ClientHandler());
            }
        });

        ChannelFuture cf1 = b.connect("127.0.0.1", 8765).sync();
        // ChannelFuture cf2 = b.connect("127.0.0.1", 8764).sync();
        System.out.println("client connected...");

        // 发送消息
        Thread.sleep(1000);
        cf1.channel().writeAndFlush(Unpooled.copiedBuffer("777".getBytes()));
        cf1.channel().writeAndFlush(Unpooled.copiedBuffer("666".getBytes()));
        // cf2.channel().writeAndFlush(Unpooled.copiedBuffer("888".getBytes()));
        Thread.sleep(2000);
        cf1.channel().writeAndFlush(Unpooled.copiedBuffer("888".getBytes()));
        // cf2.channel().writeAndFlush(Unpooled.copiedBuffer("666".getBytes()));

        cf1.channel().closeFuture().sync();
        // cf2.channel().closeFuture().sync();
        group.shutdownGracefully();
    }
}
