package com.im.server.strategy;

import com.im.protocol.base.Packet;
import com.im.protocol.packet.MessageRequestPacket;
import com.im.protocol.packet.MessageResponsePacket;
import com.im.protocol.serializer.JsonSerializer;
import com.im.utils.PacketCodeCUtil;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;

/**
 * @program: im-netty->MessageStrategy
 * @description: 消息策略
 * @author: huangfu
 * @date: 2019/12/2 17:41
 **/
public class MessageStrategy implements Strategy {
    @Override
    public ByteBuf calculateSend(Packet packet, ChannelHandlerContext ctx) {
        //处理消息
        MessageRequestPacket messageRequestPacket = ((MessageRequestPacket)packet);
        System.out.println(System.currentTimeMillis()+":收到客户端消息:"+messageRequestPacket.getMessage());
        MessageResponsePacket messageResponsePacket = new MessageResponsePacket();
        messageResponsePacket.setMessage("服务端客户回复【"+messageRequestPacket.getMessage()+"】");
        //编码
        ByteBuf encode = PacketCodeCUtil.encode(messageResponsePacket, new JsonSerializer( ));
        return encode;
    }
}
