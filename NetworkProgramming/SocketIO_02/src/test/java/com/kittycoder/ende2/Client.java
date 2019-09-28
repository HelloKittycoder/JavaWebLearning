package com.kittycoder.ende2;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.FixedLengthFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;

/**
 * Created by shucheng on 2019-9-28 下午 22:40
 * 简单说下，发送字母c的那一段：
 * 因为设置的长度为5，如果发送7个c，服务器只会拿前5个c，后面2个c直接丢弃掉；
 * 如果再补上3个空格，服务器会拆分成两次请求，前面5个c是一个，后面2个c加上3个空格是一个
 */
public class Client {

    public static void main(String[] args) throws Exception {
        EventLoopGroup group = new NioEventLoopGroup();

        Bootstrap b = new Bootstrap();
        b.group(group)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel sc) {
                        sc.pipeline().addLast(new FixedLengthFrameDecoder(5));
                        sc.pipeline().addLast(new StringDecoder());
                        sc.pipeline().addLast(new ClientHandler());
                    }
                });

        ChannelFuture cf = b.connect("127.0.0.1", 8765).sync();

        cf.channel().writeAndFlush(Unpooled.wrappedBuffer("aaaaabbbbb".getBytes()));
        cf.channel().writeAndFlush(Unpooled.copiedBuffer("ccccccc".getBytes()));
        // cf.channel().writeAndFlush(Unpooled.copiedBuffer("ccccccc   ".getBytes()));

        // 等待客户端端口关闭
        cf.channel().closeFuture().sync();
        group.shutdownGracefully();
    }
}
