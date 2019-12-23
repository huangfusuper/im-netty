package com.im.utils;

import com.im.model.UserSession;

import com.im.protocol.Attributes;
import io.netty.channel.Channel;
import io.netty.channel.group.ChannelGroup;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 操作用户的工具列
 * @author huangfu
 */
public class UserSessionUtil {
    /**
     * 每一个用户道济加了几个聊天组
     */
    private static final Map<String, List<String>> userGroupIds = new HashMap<String, List<String>>(15);
    /**
     * 这个是userId 对照  channel 对象的载体
     */
    private static final  Map<String, Channel> userIdChannelMap = new HashMap<String,Channel>(10);
    /**
     * 创建聊天组
     */
    private static final Map<String, ChannelGroup> channelGroupMap = new HashMap<>(15);
    /**
     * 记录用户添加的群组，或者创建的群组
     */
    public static void recordingUserGroup(String userId,String groupId){
        ChannelGroup channels = channelGroupMap.get(groupId);
        if(channels!=null){
            if (userGroupIds.get(userId) != null) {
                userGroupIds.get(userId).add(groupId);
            }else{
                List<String> groups = new ArrayList<>( );
                groups.add(groupId);
                userGroupIds.put(userId,groups);
            }
        }else{
            System.out.println("该聊天室不存在！！" );
        }
    }

    /**
     * 创建聊天室
     * @param groupId
     * @param channels
     */
    public static void bindChannelGroup(String groupId,ChannelGroup channels){
        channelGroupMap.put(groupId,channels);
    }
    /**
     * 获取聊天室
     */
    public static ChannelGroup getChannelGroup(String groupId){
        return channelGroupMap.get(groupId);
    }

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
