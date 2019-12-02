package com.im.client;

import com.im.protocol.packet.LoginRequestPacket;
import com.im.protocol.packet.LoginResponsePacket;
import com.im.protocol.base.Packet;
import com.im.protocol.packet.MessageResponsePacket;
import com.im.protocol.serializer.JsonSerializer;
import com.im.utils.LoginUtil;
import com.im.utils.PacketCodeCUtil;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.UUID;

/**
 * @program: im-netty->ClientHandler
 * @description: 处理器
 * @author: huangfu
 * @date: 2019/12/2 13:57
 **/
public class ClientHandler extends ChannelInboundHandlerAdapter {
    /**
     * 当客户端与服务端简历好连接会回调此方法
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println(System.currentTimeMillis() + ": 客户端开始登录");

        // 创建登录对象
        LoginRequestPacket loginRequestPacket = new LoginRequestPacket();
        loginRequestPacket.setUserId(UUID.randomUUID().toString());
        loginRequestPacket.setUserName("flash");
        loginRequestPacket.setPassword("pwd");
        //编码
        ByteBuf encode = PacketCodeCUtil.encode(loginRequestPacket, new JsonSerializer( ));
        ctx.channel().writeAndFlush(encode);
    }

    /**
     * 客户端接收服务端的信息
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf byteBuf =(ByteBuf)msg;
        Packet packet = PacketCodeCUtil.decode(byteBuf, new JsonSerializer( ));

        if(packet instanceof LoginResponsePacket){
            LoginResponsePacket loginResponsePacket = (LoginResponsePacket)packet;

            if(loginResponsePacket.isSuccess()){
                System.out.println("客户端登录成功" );
                LoginUtil.markAsLogin(ctx.channel());
            }else{
                System.out.println("客户端登录失败"+loginResponsePacket.getReason() );
            }
        }else if(packet instanceof MessageResponsePacket){
            MessageResponsePacket messageResponsePacket = (MessageResponsePacket)packet;
            System.out.println(System.currentTimeMillis() + ": 收到服务端的消息: " + messageResponsePacket.getMessage());
        }
    }
}
