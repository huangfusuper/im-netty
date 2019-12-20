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
        //根据通道获取用户
        UserSession userSession = UserSessionUtil.getUserSession(ctx.channel());
        //构造消息回应对象
        MessageResponsePacket messageResponsePacket = new MessageResponsePacket();
        //将发送者的信息放进回应对象
        messageResponsePacket.setUserId(userSession.getUserId());
        messageResponsePacket.setUserName(userSession.getUserName());
        //将发送的消息放入回应对象
        messageResponsePacket.setMessage(messageRequestPacket.getMessage());
        //这个id是标识你要发送给谁 拿到那个人的通道对象
        Channel channel = UserSessionUtil.getChannel(messageRequestPacket.getUserId());

        if (channel!=null && UserSessionUtil.hasLogin(channel)) {
            //将消息发送到那个人上
            channel.writeAndFlush(messageResponsePacket);
        }else{
            System.out.println(String.format("%s【不在线】",messageRequestPacket.getUserId()));
        }
        //编码 其实这个不需要了 因为服务器不需要回应
        //ctx.channel().writeAndFlush(messageResponsePacket)
    }
}
