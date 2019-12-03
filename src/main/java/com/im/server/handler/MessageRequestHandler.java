package com.im.server.handler;

import com.im.protocol.packet.request.MessageRequestPacket;
import com.im.protocol.packet.response.MessageResponsePacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @program: im-netty->MessageHandler
 * @description: 消息请求处理器
 * @author: huangfu
 * @date: 2019/12/3 15:47
 **/
public class MessageRequestHandler extends SimpleChannelInboundHandler<MessageRequestPacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageRequestPacket messageRequestPacket) throws Exception {
        //处理消息
        System.out.println(System.currentTimeMillis()+":收到客户端消息:"+messageRequestPacket.getMessage());
        MessageResponsePacket messageResponsePacket = new MessageResponsePacket();
        messageResponsePacket.setMessage("服务端客户回复【"+messageRequestPacket.getMessage()+"】");
        //编码
        ctx.channel().writeAndFlush(messageResponsePacket);
    }
}
