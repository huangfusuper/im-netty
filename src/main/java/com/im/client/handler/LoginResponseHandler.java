package com.im.client.handler;

import com.im.protocol.packet.request.LoginRequestPacket;
import com.im.protocol.packet.response.LoginResponsePacket;
import com.im.utils.LoginUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.UUID;

/**
 * @program: im-netty->LoginResponseHandler
 * @description: 登录响应处理类
 * @author: huangfu
 * @date: 2019/12/3 16:05
 **/
public class LoginResponseHandler extends SimpleChannelInboundHandler<LoginResponsePacket> {
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("====【客户端发起登陆请求】====" );
        LoginRequestPacket loginResponsePacket = new LoginRequestPacket();
        loginResponsePacket.setUserId(UUID.randomUUID( ).toString());
        loginResponsePacket.setUserName("huangFuSuper");
        loginResponsePacket.setPassword("HUANGFUPassword");
        ctx.channel().writeAndFlush(loginResponsePacket);
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, LoginResponsePacket loginResponsePacket) throws Exception {
        if(loginResponsePacket.isSuccess()){
            System.out.println("----【登陆成功】----" );
            LoginUtil.markAsLogin(channelHandlerContext.channel());
        }else{
            System.out.println("----【登陆失败】："+loginResponsePacket.getReason()+"----" );
        }
    }
}
