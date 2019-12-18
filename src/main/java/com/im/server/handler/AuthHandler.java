package com.im.server.handler;

import com.im.utils.LoginUtil;
import com.im.utils.UserSessionUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @program: im-netty->AuthHandler
 * @description: 作身份验证的类，在通道有数据的时候，就会进去做验证是否已经登录，没有登录直接强行关闭通道
 * @author: huangfu
 * @date: 2019/12/17 10:56
 **/
public class AuthHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if(!UserSessionUtil.hasLogin(ctx.channel())){
            System.out.println("--------------登录失败，强制关闭通道----------------" );
            ctx.channel().close().sync();
        }else{
            System.out.println("--------登录成功-----" );
            //拔掉这个处理器
            ctx.pipeline().remove(this);
            super.channelRead(ctx, msg);
        }
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) {
        if (LoginUtil.hasLogin(ctx.channel())) {
            System.out.println("当前连接登录验证完毕，无需再次验证, AuthHandler 被移除");
        } else {
            System.out.println("无登录验证，强制关闭连接!");
        }
    }
}
