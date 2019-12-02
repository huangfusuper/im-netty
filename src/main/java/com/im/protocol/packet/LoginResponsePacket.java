package com.im.protocol.packet;

import com.im.protocol.base.Packet;
import com.im.protocol.enums.Command;
import lombok.Data;

/**
 * @author huangfu
 */
@Data
public class LoginResponsePacket extends Packet {
    /**
     * 是否登录成功
     */
    private boolean success;
    /**
     * 登录信息
     */
    private String reason;


    @Override
    public Byte getCommand() {
        return Command.LOGIN_RESPONSE.getCommandCode();
    }
}