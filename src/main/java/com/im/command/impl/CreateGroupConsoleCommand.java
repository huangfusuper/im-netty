package com.im.command.impl;

import com.im.protocol.packet.request.CreateGroupRequestPacket;
import com.im.command.ConsoleCommand;
import io.netty.channel.Channel;

import java.util.*;

/**
 * @author huangfu
 */
public class CreateGroupConsoleCommand implements ConsoleCommand {
    private final String USER_IDS = ",";
    @Override
    public void exec(Scanner scanner, Channel channel) {
        CreateGroupRequestPacket createGroupRequestPacket = new CreateGroupRequestPacket();
        System.out.print("【拉人群聊】输入 userId 列表，userId 之间英文逗号隔开：");
        String userStr = scanner.nextLine();
        List<String> strings = Arrays.asList(userStr.split(USER_IDS));
        Set<String> stringSet = new HashSet<String>(15);
        stringSet.addAll(strings);
        createGroupRequestPacket.setUsers(stringSet);
        channel.writeAndFlush(createGroupRequestPacket);
    }
}
