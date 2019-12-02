package com.im.server.strategy;

import com.im.protocol.base.Packet;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;

/**
 * @program: im-netty->Strategy
 * @description: 策略基类
 * @author: huangfu
 * @date: 2019/12/2 17:22
 **/
public interface Strategy {
    /**
     * 算法
     * @param packet
     * @param ctx
     * @return
     */
    ByteBuf calculateSend(Packet packet, ChannelHandlerContext ctx);
}
