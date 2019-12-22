package com.im.server.handler;

import com.im.model.UserSession;
import com.im.protocol.packet.request.CreateGroupRequestPacket;
import com.im.protocol.packet.response.CreateGroupResponsePacket;
import com.im.utils.UserSessionUtil;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * 新建聊天组
 * @author huangfu
 */
public class CreateGroupRequestHandler extends SimpleChannelInboundHandler<CreateGroupRequestPacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, CreateGroupRequestPacket msg) throws Exception {
        List<String> userNameList = new ArrayList<String>(15);

        ChannelGroup channels = new DefaultChannelGroup(ctx.executor());
        List<String> users = msg.getUsers();
        users.forEach(userId ->{
            Channel userChannel = UserSessionUtil.getChannel(userId);
            if(userChannel!=null){
                channels.add(userChannel);
                UserSession userSession = UserSessionUtil.getUserSession(userChannel);
                userNameList.add(userSession.getUserName());
            }

        });
        String groupId = UUID.randomUUID().toString().replace("-", "");
        UserSessionUtil.bindChannelGroup(groupId,channels);
        CreateGroupResponsePacket createGroupResponsePacket = new CreateGroupResponsePacket();
        createGroupResponsePacket.setGroupId(groupId);
        createGroupResponsePacket.setSuccess(true);
        createGroupResponsePacket.setUserNames(userNameList);
        channels.writeAndFlush(createGroupResponsePacket);
    }
}
