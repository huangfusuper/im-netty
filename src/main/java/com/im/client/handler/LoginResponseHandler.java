package com.im.client.handler;

import com.im.model.UserSession;
import com.im.protocol.packet.request.LoginRequestPacket;
import com.im.protocol.packet.response.LoginResponsePacket;
import com.im.utils.LoginUtil;
import com.im.utils.UserSessionUtil;
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
    /*@Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("====【客户端发起登陆请求】====" );
        LoginRequestPacket loginResponsePacket = new LoginRequestPacket();
        loginResponsePacket.setUserId(UUID.randomUUID( ).toString());
        loginResponsePacket.setUserName("huangFuSuper");
        loginResponsePacket.setPassword("HUANGFUPassword");
        ctx.channel().writeAndFlush(loginResponsePacket);
    }*/

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, LoginResponsePacket loginResponsePacket) throws Exception {
        System.out.println(loginResponsePacket);
        if(loginResponsePacket.isSuccess()){
            System.out.println(String.format("----【登陆成功】,userId:%s,UserName:%s----",loginResponsePacket.getUserId(),loginResponsePacket.getUserName() ) );
            //客户端通道的登录成功
            UserSessionUtil.bindSession(new UserSession(loginResponsePacket.getUserId(),loginResponsePacket.getUserName()),channelHandlerContext.channel());
        }else{
            System.out.println("----【登陆失败】："+loginResponsePacket.getReason()+"----" );
        }
    }
}
