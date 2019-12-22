package com.im.command.impl;

import com.im.command.ConsoleCommand;
import com.im.protocol.packet.request.LoginRequestPacket;
import io.netty.channel.Channel;

import java.util.Scanner;

/**
 * 登录米命令
 * @author huangfu
 */
public class LoginCommand implements ConsoleCommand {
    @Override
    public void exec(Scanner scanner, Channel channel) {
        LoginRequestPacket loginRequestPacket = new LoginRequestPacket();
        System.out.println("请输入您的用户id:" );
        String userId = scanner.nextLine( );
        System.out.println("请输入您的用户名:" );
        String userName = scanner.nextLine( );
        loginRequestPacket.setUserName(userName);
        loginRequestPacket.setUserId(userId);
        //发送登录请求
        channel.writeAndFlush(loginRequestPacket);
    }
}
