package com.im.protocol.packet.request;

import com.im.protocol.base.Packet;
import com.im.protocol.enums.Command;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 登录请求数据包继承自 Packet，然后定义了三个字段，
 * 分别是用户 ID，用户名，密码，
 * 这里最为重要的就是覆盖了父类的 getCommand() 方法，
 * 值为常量 LOGIN_REQUEST
 * 登录请求
 * @author huangfu
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequestPacket extends Packet {
    private String userId;
    private String userName;
    private String password;
    @Override
    public Byte getCommand() {
        return Command.LOGIN_REQUEST.getCommandCode();
    }

    @Override
    public String toString() {
        return "LoginRequestPacket{" +
                "userId='" + userId + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
