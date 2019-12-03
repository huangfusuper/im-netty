package com.im.protocol.packet.response;

import com.im.protocol.base.Packet;
import com.im.protocol.enums.Command;
import lombok.Data;

/**
 * @program: im-netty->MessageResponsePacket
 * @description: 消息响应实体
 * @author: huangfu
 * @date: 2019/12/2 16:15
 **/
@Data
public class MessageResponsePacket extends Packet {
    private String message;
    @Override
    public Byte getCommand() {
        return Command.MESSAGE_RESPONSE.getCommandCode();
    }
}
