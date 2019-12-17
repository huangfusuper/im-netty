package com.im.server.handler;

import com.im.model.UserSession;
import com.im.protocol.packet.request.MessageRequestPacket;
import com.im.protocol.packet.response.MessageResponsePacket;
import com.im.utils.UserSessionUtil;
import io.netty.channel.Channel;
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
        UserSession userSession = UserSessionUtil.getUserSession(ctx.channel());
        MessageResponsePacket messageResponsePacket = new MessageResponsePacket();
        messageResponsePacket.setUserId(userSession.getUserId());
        messageResponsePacket.setUserName(userSession.getUserName());
        messageResponsePacket.setMessage("服务端客户回复【"+messageRequestPacket.getMessage()+"】");

        Channel channel = UserSessionUtil.getChannel(messageRequestPacket.getUserId());

        if (channel!=null && UserSessionUtil.hasLogin(channel)) {
            channel.writeAndFlush(messageResponsePacket);
        }else{
            System.out.println(String.format("{}【不在线】",messageRequestPacket.getUserId()));
        }
        //编码
        ctx.channel().writeAndFlush(messageResponsePacket);
    }
}
