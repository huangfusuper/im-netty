package com.im.server;

import com.im.protocol.packet.LoginRequestPacket;
import com.im.protocol.base.Packet;
import com.im.protocol.packet.MessageRequestPacket;
import com.im.protocol.serializer.JsonSerializer;
import com.im.server.strategy.LoginStrategy;
import com.im.server.strategy.MessageStrategy;
import com.im.server.strategy.Strategy;
import com.im.utils.PacketCodeCUtil;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @program: im-netty->ServerHandel
 * @description: 登录服务校验
 * @author: huangfu
 * @date: 2019/12/2 14:26
 **/
public class ServerHandel extends ChannelInboundHandlerAdapter {
    /**
     * 接收到客户端的信息后回调
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf byteBuf = (ByteBuf)msg;
        //解码
        Packet packet = PacketCodeCUtil.decode(byteBuf, new JsonSerializer( ));
        Strategy strategy = null;
        //判断是否有登陆的请求包
        if(packet instanceof LoginRequestPacket){
            strategy = new LoginStrategy();
        }else if(packet instanceof MessageRequestPacket){
            strategy = new MessageStrategy();
        }
        ctx.channel().writeAndFlush(strategy.calculateSend(packet,ctx));
    }


}
