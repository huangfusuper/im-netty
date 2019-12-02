package com.im.server.strategy;

import com.im.protocol.base.Packet;
import com.im.protocol.packet.LoginRequestPacket;
import com.im.protocol.packet.LoginResponsePacket;
import com.im.protocol.serializer.JsonSerializer;
import com.im.utils.PacketCodeCUtil;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;

/**
 * @program: im-netty->LoginStrategy
 * @description: 登录策略
 * @author: huangfu
 * @date: 2019/12/2 17:31
 **/
public class LoginStrategy implements Strategy{
    @Override
    public ByteBuf calculateSend(Packet packet, ChannelHandlerContext ctx) {
        LoginRequestPacket loginRequestPacket = (LoginRequestPacket)packet;

        LoginResponsePacket loginResponsePacket = new LoginResponsePacket();
        loginResponsePacket.setVersion(loginRequestPacket.getVersion());
        //登录校验
        if (valid(loginRequestPacket)) {
            loginResponsePacket.setSuccess(true);
        }else{
            loginResponsePacket.setReason("账号密码校验失败");
            loginResponsePacket.setSuccess(false);
        }

        //编码
        ByteBuf encode = PacketCodeCUtil.encode(loginResponsePacket, new JsonSerializer( ));
        return encode;
    }
    private boolean valid(LoginRequestPacket loginRequestPacket) {
        return true;
    }
}
