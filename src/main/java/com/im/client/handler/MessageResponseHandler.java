package com.im.client.handler;

import com.im.protocol.packet.response.MessageResponsePacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @program: im-netty->MessageResponseHandler
 * @description: 消息响应处理类
 * @author: huangfu
 * @date: 2019/12/3 16:11
 **/
public class MessageResponseHandler extends SimpleChannelInboundHandler<MessageResponsePacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, MessageResponsePacket messageResponsePacket) throws Exception {
        System.out.println(String.format("----【服务端回应】：userId:%s--UserName%s:%s",messageResponsePacket.getUserId(),messageResponsePacket.getUserName(),messageResponsePacket.getMessage()) );
    }
}
