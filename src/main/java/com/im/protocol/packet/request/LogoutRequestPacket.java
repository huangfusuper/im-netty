package com.im.protocol.packet.request;

import com.im.protocol.base.Packet;
import com.im.protocol.enums.Command;
import lombok.*;

/**
 * 推出请求的包
 * @author huangfu
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = true)
public class LogoutRequestPacket extends Packet {
    private String userId;
    @Override
    public Byte getCommand() {
        return Command.LOGOUT_REQUEST.getCommandCode();
    }
}
