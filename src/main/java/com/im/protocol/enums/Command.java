package com.im.protocol.enums;

import com.im.protocol.LoginRequestPacket;
import com.im.protocol.LoginResponsePacket;
import com.im.protocol.base.Packet;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * 命令代码
 * @author huangfu
 */

public enum Command {
    LOGIN_REQUEST(Byte.parseByte("1"),LoginRequestPacket.class,"登录请求"),
    LOGIN_RESPONSE(Byte.parseByte("2"), LoginResponsePacket.class,"登录响应")
    ;
    private byte commandCode;
    private Class<? extends Packet> packet;
    private String commandDescription;

    Command(byte commandCode, Class<? extends Packet> packet, String commandDescription) {
        this.commandCode = commandCode;
        this.packet = packet;
        this.commandDescription = commandDescription;
    }

    public byte getCommandCode() {
        return commandCode;
    }

    public Class<? extends Packet> getPacket() {
        return packet;
    }

    public String getCommandDescription() {
        return commandDescription;
    }

    public static Class<? extends Packet> match(byte commandCode){

        List<Command> collect = Arrays.asList(Command.values()).stream().
                filter(command -> command.getCommandCode() == commandCode).
                collect(Collectors.toList());
        if(collect != null && collect.size()>0){
            return collect.get(0).getPacket();
        }
        return null;
    }
}
