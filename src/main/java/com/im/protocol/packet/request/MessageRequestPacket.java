package com.im.protocol.packet.request;

import com.im.protocol.base.Packet;
import com.im.protocol.enums.Command;
import lombok.*;

/**
 * @program: im-netty->MessageRequestPacket
 * @description: 消息实体
 * @author: huangfu
 * @date: 2019/12/2 16:11
 **/
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MessageRequestPacket extends Packet {
    private String userId;
    private String message;
    @Override
    public Byte getCommand() {
        return Command.MESSAGE_REQUEST.getCommandCode();
    }
}
