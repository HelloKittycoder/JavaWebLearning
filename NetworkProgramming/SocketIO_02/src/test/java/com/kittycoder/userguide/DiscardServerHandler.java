package com.kittycoder.userguide;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

/**
 * Created by shucheng on 2019-9-28 下午 19:30
 */
public class DiscardServerHandler extends ChannelHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        // 在服务端打印收到的信息
        /*ByteBuf in = (ByteBuf) msg;
        try {
            while (in.isReadable()) {
                // 在控制台打印请求到的数据
                *//*System.out.print(in.readByte());
                System.out.flush();*//*
            }
        } finally {
            ReferenceCountUtil.release(msg);
        }*/

        // 在客户端进行数据回显
        /*ctx.write(msg);
        ctx.flush();*/
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
