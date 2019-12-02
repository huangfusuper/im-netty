package com.im.client;


import com.im.protocol.packet.MessageRequestPacket;
import com.im.protocol.serializer.JsonSerializer;
import com.im.utils.LoginUtil;
import com.im.utils.PacketCodeCUtil;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

/**
 * @program: im-netty->Client
 * @description: TODO
 * @author: huangfu
 * @date: 2019/12/2 14:01
 **/
public class Client {
    private static final int MAX_RETRY = 5;
    private static final String HOST = "127.0.0.1";
    private static final int PORT = 8888;


    public static void main(String[] args) {
        Bootstrap bootstrap = new Bootstrap();
        NioEventLoopGroup loopGroup = new NioEventLoopGroup();
        bootstrap.group(loopGroup)
                .channel(NioSocketChannel.class)
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS,5000)
                .option(ChannelOption.SO_KEEPALIVE,true)
                .option(ChannelOption.TCP_NODELAY,true)
                .handler(new ClientInitializer());

        connect(bootstrap, HOST, PORT, MAX_RETRY);

    }

    private static void connect(Bootstrap bootstrap, String host, int port, int retry) {
        bootstrap.connect(host, port).addListener(future -> {
            if (future.isSuccess()) {
                System.out.println(System.currentTimeMillis() + ": 连接成功!");
                Channel channel = ((ChannelFuture) future).channel( );
                startConsoleThread(channel);
            } else if (retry == 0) {
                System.err.println("重试次数已用完，放弃连接！");
            } else {
                // 第几次重连
                int order = (MAX_RETRY - retry) + 1;
                // 本次重连的间隔
                int delay = 1 << order;
                System.err.println(System.currentTimeMillis() + ": 连接失败，第" + order + "次重连……");
                bootstrap.config().group().schedule(() -> connect(bootstrap, host, port, retry - 1), delay, TimeUnit
                        .SECONDS);
            }
        });
    }

    private static void startConsoleThread(Channel channel){
        new Thread(() ->{
            while (!Thread.interrupted()){
                if (LoginUtil.hasLogin(channel)) {
                    System.out.println("输入消息发送到客户端..." );
                    Scanner sc = new Scanner(System.in);
                    String line = sc.nextLine( );

                    MessageRequestPacket packet = new MessageRequestPacket();
                    packet.setMessage(line);

                    ByteBuf byteBuf = PacketCodeCUtil.encode(packet, new JsonSerializer( ));
                    channel.writeAndFlush(byteBuf);
                }
            }
        }).start();
    }
}
