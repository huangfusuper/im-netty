package com.im.server.handler;

import com.im.model.UserSession;
import com.im.protocol.packet.request.LoginRequestPacket;
import com.im.protocol.packet.response.LoginResponsePacket;
import com.im.utils.LoginUtil;
import com.im.utils.UserSessionUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @program: im-netty->LoginRequestHandler
 * @description: 登录的消息处理器
 * @author: huangfu
 * @date: 2019/12/3 15:40
 **/
public class LoginRequestHandler extends SimpleChannelInboundHandler<LoginRequestPacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LoginRequestPacket loginRequestPacket) throws Exception {
        System.out.println(System.currentTimeMillis()+"-----------收到客户端的登录请求-----------" );

        LoginResponsePacket loginResponsePacket = new LoginResponsePacket();
        loginResponsePacket.setVersion(loginRequestPacket.getVersion());
        loginResponsePacket.setUserName(loginRequestPacket.getUserName());
        loginResponsePacket.setUserId(loginRequestPacket.getUserId());
        //登录校验
        if (valid(loginRequestPacket)) {
            System.out.println("-------------登录成功-----------------" );
            loginResponsePacket.setSuccess(true);
            UserSessionUtil.bindSession(new UserSession(loginRequestPacket.getUserId(),loginRequestPacket.getUserName()),ctx.channel());
        }else{
            loginResponsePacket.setReason("账号密码校验失败");
            loginResponsePacket.setSuccess(false);
        }
        //编码
        //登录响应
        ctx.channel().writeAndFlush(loginResponsePacket);
    }
    private boolean valid(LoginRequestPacket loginRequestPacket) {
        return true;
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) {
        UserSessionUtil.unBindSession(ctx.channel());
    }

}
