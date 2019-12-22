package com.im.command.impl;

import com.im.command.ConsoleCommand;
import io.netty.channel.Channel;

import java.util.Scanner;

/**
 * 创建群聊的命令
 * @author huangfu
 */
public class ConsoleCommandManager  implements ConsoleCommand {
    @Override
    public void exec(Scanner scanner, Channel channel) {
        System.out.println("【请输入你要进行的操作】:");
        String command = scanner.nextLine();

        ConsoleCommand consoleCommand = consoleCommandMap.get(command);

        if(null != consoleCommand){
            consoleCommand.exec(scanner,channel);
        }else{
            System.out.println(String.format("【命令】:%s，无法识别",command));
        }
    }
}
