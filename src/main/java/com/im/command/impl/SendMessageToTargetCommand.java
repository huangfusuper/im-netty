package com.im.command.impl;

import com.im.command.ConsoleCommand;
import com.im.protocol.packet.request.MessageRequestPacket;
import io.netty.channel.Channel;

import java.util.Scanner;

/**
 * 发送消息到目标对象
 * @author huangfu
 */
public class SendMessageToTargetCommand implements ConsoleCommand {
    @Override
    public void exec(Scanner scanner, Channel channel) {
        System.out.println("请输入您要发送给谁(用户id)：" );
        String userId = scanner.nextLine();
        System.out.println("请输入您要发送的内容:" );
        String message = scanner.nextLine();
        channel.writeAndFlush(new MessageRequestPacket(userId,message));
    }
}
