package com.im.protocol;

import com.im.model.UserSession;
import io.netty.util.AttributeKey;

/**
 * @program: im-netty->Attributes
 * @description: 登录的标志位
 * @author: huangfu
 * @date: 2019/12/2 16:18
 **/
public interface Attributes {
    AttributeKey<Boolean> LOGIN = AttributeKey.newInstance("login");
    AttributeKey<UserSession> USER_SESSION = AttributeKey.newInstance("session");
}
