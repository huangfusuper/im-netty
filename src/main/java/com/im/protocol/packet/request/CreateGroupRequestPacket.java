package com.im.protocol.packet.request;

import com.im.protocol.base.Packet;
import com.im.protocol.enums.Command;
import lombok.*;

import java.util.List;

/**
 * 拉去群聊的请求类
 * @author huangfu
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CreateGroupRequestPacket extends Packet {
    @Override
    public Byte getCommand() {
        return Command.CREATE_GROUP_REQUEST.getCommandCode();
    }
    private List<String> users;
}
