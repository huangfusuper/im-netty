package com.im.command;

import com.im.command.impl.CreateGroupConsoleCommand;
import com.im.command.impl.LogoutCommand;
import com.im.command.impl.SendMessageToTargetCommand;
import io.netty.channel.Channel;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * 命令的基类
 * @author huangfu
 */
public interface ConsoleCommand {
    Map<String,ConsoleCommand> consoleCommandMap = new HashMap<String,ConsoleCommand>(15){
        {
            put("createGroup",new CreateGroupConsoleCommand());
            put("sendToTarget",new SendMessageToTargetCommand());
            put("logout",new LogoutCommand());
        }
    };
    /**
     * 命令参数
     * @param scanner
     * @param channel
     */
    void exec(Scanner scanner, Channel channel);
}
