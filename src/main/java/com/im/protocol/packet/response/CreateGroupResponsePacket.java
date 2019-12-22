package com.im.protocol.packet.response;

import com.im.protocol.base.Packet;
import com.im.protocol.enums.Command;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 创建聊天室的回应
 * @author huangfu
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class CreateGroupResponsePacket extends Packet {
    private boolean isSuccess;
    private List<String> userNames;
    private String groupId;

    @Override
    public Byte getCommand() {
        return Command.CREATE_GROUP_RESPONSE.getCommandCode();
    }
}
