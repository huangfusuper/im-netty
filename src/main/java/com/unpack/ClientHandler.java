package com.unpack;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.nio.charset.Charset;

/**
 * @program: im-netty->ClientHandler
 * @description: 客户端处理类
 * @author: huangfu
 * @date: 2019/12/3 17:14
 **/
public class ClientHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("客户端写出数据：" );
        ByteBuf byteBuf = getByteBuf(ctx);
        ctx.channel().writeAndFlush(byteBuf);

    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        for (int i = 0; i < 10000; i++) {
            ByteBuf byteBuf = (ByteBuf)msg;
            System.out.println("【客户端收到】"+byteBuf.toString(Charset.forName("utf-8")) );

        }
    }

    private ByteBuf getByteBuf(ChannelHandlerContext ctx){
        byte[] bytes = "皇甫科星，牛逼".getBytes(Charset.forName("utf-8"));
        ByteBuf buffer = ctx.alloc( ).buffer( );
        buffer.writeBytes(bytes);
        return buffer;
    }


}
