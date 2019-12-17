package com.im.protocol.packet.response;

import com.im.protocol.base.Packet;
import com.im.protocol.enums.Command;
import lombok.*;

/**
 * @author huangfu
 */
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class LoginResponsePacket extends Packet {
    /**
     * 是否登录成功
     */
    private boolean success;
    /**
     * 登录信息
     */
    private String reason;

    private String userId;

    private String userName;

    @Override
    public Byte getCommand() {
        return Command.LOGIN_RESPONSE.getCommandCode();
    }
}