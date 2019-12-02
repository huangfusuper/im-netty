package com.im.client;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;

/**
 * @program: im-netty->LoginInitializer
 * @description: 登录服务初始化器
 * @author: huangfu
 * @date: 2019/12/2 13:44
 **/
public class ClientInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        socketChannel.pipeline().addLast(new ClientHandler());
    }
}
