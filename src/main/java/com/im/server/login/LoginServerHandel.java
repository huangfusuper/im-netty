package com.im.server.login;

import com.im.protocol.LoginRequestPacket;
import com.im.protocol.LoginResponsePacket;
import com.im.protocol.base.Packet;
import com.im.protocol.serializer.JsonSerializer;
import com.im.utils.PacketCodeCUtil;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @program: im-netty->LoginServerHandel
 * @description: 登录服务校验
 * @author: huangfu
 * @date: 2019/12/2 14:26
 **/
public class LoginServerHandel extends ChannelInboundHandlerAdapter {
    /**
     * 接收到客户端的信息后回调
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println(System.currentTimeMillis()+"开始登录" );
        ByteBuf byteBuf = (ByteBuf)msg;
        //解码
        Packet packet = PacketCodeCUtil.decode(byteBuf, new JsonSerializer( ));
        //判断是否有登陆的请求包
        if(packet instanceof LoginRequestPacket){
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
            ctx.channel().writeAndFlush(encode);
        }
    }

    private boolean valid(LoginRequestPacket loginRequestPacket) {
        return true;
    }
}
