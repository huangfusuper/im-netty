package com.im.protocol.enums;

import com.im.protocol.packet.request.CreateGroupRequestPacket;
import com.im.protocol.packet.request.LoginRequestPacket;
import com.im.protocol.packet.request.LogoutRequestPacket;
import com.im.protocol.packet.response.CreateGroupResponsePacket;
import com.im.protocol.packet.response.LoginResponsePacket;
import com.im.protocol.packet.request.MessageRequestPacket;
import com.im.protocol.base.Packet;
import com.im.protocol.packet.response.MessageResponsePacket;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 命令代码
 * @author huangfu
 */

public enum Command {
    LOGIN_REQUEST(Byte.parseByte("1"),LoginRequestPacket.class,"登录请求")
    ,LOGIN_RESPONSE(Byte.parseByte("2"), LoginResponsePacket.class,"登录响应")
    ,MESSAGE_REQUEST(Byte.parseByte("3"), MessageRequestPacket.class,"发送消息")
    ,MESSAGE_RESPONSE(Byte.parseByte("4"), MessageResponsePacket.class,"发消息对象")
    ,CREATE_GROUP_REQUEST(Byte.parseByte("5"), CreateGroupRequestPacket.class,"创建群聊")
    ,CREATE_GROUP_RESPONSE(Byte.parseByte("6"), CreateGroupResponsePacket.class,"回应创建群聊")
    ,LOGOUT_REQUEST(Byte.parseByte("7"), LogoutRequestPacket.class,"退出登录的请求")
    ,LOGOUT_RESPONSE(Byte.parseByte("8"),null,"退出登录的回应")
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
