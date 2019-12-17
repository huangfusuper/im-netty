package com.im.utils;

import com.im.model.UserSession;

import com.im.protocol.Attributes;
import io.netty.channel.Channel;
import java.util.HashMap;
import java.util.Map;

/**
 * 操作用户的工具列
 * @author huangfu
 */
public class UserSessionUtil {
    /**
     * 这个是userId 对照  channel 对象的载体
     */
    private static final  Map<String, Channel> userIdChannelMap = new HashMap<String,Channel>(10);

    /**
     * 将用户id与管道绑定
     * @param session
     * @param channel
     */
    public static void bindSession(UserSession session,Channel channel){
        userIdChannelMap.put(session.getUserId(),channel);
        channel.attr(Attributes.USER_SESSION).set(session);
    }

    /**
     * 将管道与用户解绑
     * @param channel
     */
    public static void unBindSession(Channel channel){
        userIdChannelMap.remove(getUserSession(channel).getUserId());
        channel.attr(Attributes.USER_SESSION).set(null);
    }

    /**
     * 根据管道获取用户
     * @param channel
     * @return
     */
    public static UserSession getUserSession(Channel channel){
        return channel.attr(Attributes.USER_SESSION).get();
    }

    /**
     * 根据用户获取管道
     * @param userId
     * @return
     */
    public static Channel getChannel(String userId){
        return userIdChannelMap.get(userId);
    }

    /**
     * 判断是否登录
     * @param channel
     * @return
     */
    public static boolean hasLogin(Channel channel){
        return channel.hasAttr(Attributes.USER_SESSION);
    }
}
