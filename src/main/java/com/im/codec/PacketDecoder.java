package com.im.codec;

import com.im.protocol.serializer.JsonSerializer;
import com.im.utils.PacketCodeCUtil;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * @program: im-netty->PacketDecoder
 * @description: 消息解码器 前置，但凡都是先解码做处理在编码发送
 * @author: huangfu
 * @date: 2019/12/3 15:50
 **/
public class PacketDecoder extends ByteToMessageDecoder {
    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        list.add(PacketCodeCUtil.decode(byteBuf,new JsonSerializer()));
    }
}
