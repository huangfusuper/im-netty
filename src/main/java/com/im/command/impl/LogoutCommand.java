package com.im.command.impl;

import com.im.command.ConsoleCommand;
import com.im.protocol.packet.request.LogoutRequestPacket;
import io.netty.channel.Channel;

import java.util.Scanner;

/**
 * 推出登录
 * @author huangfu
 */
public class LogoutCommand implements ConsoleCommand {
    @Override
    public void exec(Scanner scanner, Channel channel) {
        LogoutRequestPacket logoutRequestPacket = new LogoutRequestPacket();
        System.out.println("请输入你的用户id:");
        String userId = scanner.nextLine();
        logoutRequestPacket.setUserId(userId);
        channel.writeAndFlush(logoutRequestPacket);
    }
}
