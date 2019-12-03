package com.im.codec;

import com.im.protocol.base.Packet;
import com.im.protocol.serializer.JsonSerializer;
import com.im.utils.PacketCodeCUtil;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * @program: im-netty->PacketEncoder
 * @description: 消息编码器
 * @author: huangfu
 * @date: 2019/12/3 15:52
 **/
public class PacketEncoder extends MessageToByteEncoder<Packet> {
    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, Packet packet, ByteBuf byteBuf) throws Exception {
        PacketCodeCUtil.encode(byteBuf,packet,new JsonSerializer());
    }
}
