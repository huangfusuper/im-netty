package com.im.utils;

import com.im.protocol.Attributes;
import io.netty.channel.Channel;
import io.netty.util.Attribute;

/**
 * @program: im-netty->LoginUtil
 * @description: 登录工具类
 * @author: huangfu
 * @date: 2019/12/2 16:19
 **/
public class LoginUtil {
    public static void markAsLogin(Channel channel) {
        channel.attr(Attributes.LOGIN).set(true);
    }

    public static boolean hasLogin(Channel channel) {
        Attribute<Boolean> loginAttr = channel.attr(Attributes.LOGIN);

        return loginAttr.get() != null;
    }
}
