package com.im.protocol.packet.response;

import com.im.protocol.base.Packet;
import com.im.protocol.enums.Command;
import lombok.*;

/**
 * @program: im-netty->MessageResponsePacket
 * @description: 消息响应实体
 * @author: huangfu
 * @date: 2019/12/2 16:15
 **/
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class MessageResponsePacket extends Packet {
    private String message;
    @Override
    public Byte getCommand() {
        return Command.MESSAGE_RESPONSE.getCommandCode();
    }
}
